package com.peng.demo.dao;

import com.peng.demo.domain.entity.SeckillOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SeckillOrderMapper {

    //插入购买订单明细
    int insertOrder(@Param("seckillId") long seckillId, @Param("money") BigDecimal money, @Param("userPhone") long userPhone);

    //根据秒杀商品ID查询订单明细数据并得到对应秒杀商品的数据
    SeckillOrder findById(long seckillId);
}
