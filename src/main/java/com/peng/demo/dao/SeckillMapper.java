package com.peng.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface SeckillMapper {

    //减库存
    int reduceStock(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);




}
