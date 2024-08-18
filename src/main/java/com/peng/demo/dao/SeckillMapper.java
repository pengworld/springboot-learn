package com.peng.demo.dao;

import com.peng.demo.domain.entity.Seckill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SeckillMapper {

    //减库存
    int reduceStock(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    //查所有秒杀商品
    List<Seckill> findAll();

    //根据秒杀商品ID查询秒杀商品的数据
    Seckill findById(long seckillId);

}
