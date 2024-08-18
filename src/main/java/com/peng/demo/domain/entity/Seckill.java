package com.peng.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀商品表实体类（seckill）
 * */
public class Seckill implements Serializable {

    /**
     * 商品ID
     * */
    private long seckillId;
    /**
     * 商品标题
     * */
    private String title;
    /**
     * 商品图片
     * */
    private String image;
    /**
     * 商品原价格
     * */
    private BigDecimal price;
    /**
     * 商品秒杀价格
     * */
    private BigDecimal costPrice;
    /**
     * 创建时间
     * */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 秒杀开始时间
     * */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    /**
     * 秒杀结束时间
     * */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    /**
     * 剩余库存数量
     * */
    private long stockCount;
}
