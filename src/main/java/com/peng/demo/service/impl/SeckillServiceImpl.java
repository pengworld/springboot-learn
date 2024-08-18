package com.peng.demo.service.impl;

import com.peng.demo.constenum.SeckillStatEnum;
import com.peng.demo.dao.SeckillMapper;
import com.peng.demo.dao.SeckillOrderMapper;
import com.peng.demo.domain.dto.Exposer;
import com.peng.demo.domain.dto.SeckillExecution;
import com.peng.demo.domain.entity.Seckill;
import com.peng.demo.domain.entity.SeckillOrder;
import com.peng.demo.handler.exception.seckillexception.RepeatKillException;
import com.peng.demo.handler.exception.seckillexception.SeckillCloseException;
import com.peng.demo.handler.exception.seckillexception.SeckillException;
import com.peng.demo.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

    //设置盐值字符串 MD5
    private final String salt = "salr-peng";

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Override
    public List<Seckill> findAll() {
        return seckillMapper.findAll();
    }

    @Override
    public SeckillOrder findById(long seckillId) {
        return seckillOrderMapper.findById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillMapper.findById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }

        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //获取系统时间
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }

        //转换ID MD5
        String md5 = getMD5(seckillId);

        return new Exposer(true, md5, seckillId);
    }

    //生成MD5值
    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, BigDecimal money, long userPhone, String md5) {

        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckillId md5 error");
        }

        //执行秒杀逻辑  1.减库存 2.存储秒杀订单
        Date nowTime = new Date();

        try {
            int insertCount = seckillOrderMapper.insertOrder(seckillId, money, userPhone);
            //唯一性:seckillId userPhone  保证每个用户只能秒杀一件商品   重复秒杀失败 插入数为0
            if (insertCount <= 0) {
                throw new RepeatKillException("seckill repeated");
            } else {
                //减库存
                int updateCount = seckillMapper.reduceStock(seckillId, nowTime);
                if (updateCount <= 0) {
                    //减库存失败 商品已经秒杀完
                    throw new SeckillException("seckill is closed");
                } else {
                    SeckillOrder seckillOrder = seckillOrderMapper.findById(seckillId);
                    return new SeckillExecution(seckillId,
                            SeckillStatEnum.SUCCESS.getState(), SeckillStatEnum.SUCCESS.getStateInfo(),
                            seckillOrder);
                }
            }
        } catch (SeckillCloseException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            //所有编译期异常，转换为运行期异常
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }

    }
}
