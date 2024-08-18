package com.peng.demo.service;

import com.peng.demo.domain.entity.Seckill;

import java.util.List;

public interface SeckillService {

    List<Seckill> findAll();


    Seckill findById(long seckillId);


}
