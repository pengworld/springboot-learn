package com.peng.demo.service.impl;

import com.peng.demo.dao.SeckillMapper;
import com.peng.demo.dao.SeckillOrderMapper;
import com.peng.demo.domain.entity.Seckill;
import com.peng.demo.domain.entity.SeckillOrder;
import com.peng.demo.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SeckillServiceImpl implements SeckillService {

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
}
