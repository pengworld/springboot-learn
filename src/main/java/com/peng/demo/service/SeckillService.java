package com.peng.demo.service;

import com.peng.demo.domain.dto.Exposer;
import com.peng.demo.domain.dto.SeckillExecution;
import com.peng.demo.domain.entity.Seckill;
import com.peng.demo.domain.entity.SeckillOrder;

import java.math.BigDecimal;
import java.util.List;

public interface SeckillService {

    /**
     * 获取所有的秒杀商品
     * */
    List<Seckill> findAll();

    /**
     * 根据秒杀物品id 获取一条商品秒杀订单信息
     */
    SeckillOrder findById(long seckillId);

    /**
     * 秒杀开始时输出暴露秒杀的地址 否者输出系统时间和秒杀时间
     * */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * */
    SeckillExecution executeSeckill(long seckillId, BigDecimal money, long userPhone, String md5);
}
