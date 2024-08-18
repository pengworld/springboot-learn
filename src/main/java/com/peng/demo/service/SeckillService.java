package com.peng.demo.service;

import com.peng.demo.domain.entity.Seckill;
import com.peng.demo.domain.entity.SeckillOrder;

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

}
