package com.peng.demo.dao;

import com.peng.demo.domain.entity.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface SeckillMapper {

    //减库存
    int reduceStock(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    //查所有秒杀商品
    List<Seckill> findAll();

}
