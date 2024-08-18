package com.peng.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀订单表实体类（seckill_order）
 * */
public class SeckillOrder implements Serializable {

    /**
     * 秒杀到的商品ID
     * */
    private long seckillId;
    /**
     * 支付金额
     * */
    private BigDecimal money;
    /**
     * 秒杀用户手机号
     * */
    private long userPhone;
    /**
     * 创建时间
     * */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 订单状态  -1:无效  0:成功  1:已付款
     * */
    private boolean status;
    /**
     * 秒杀商品
     * */
    private Seckill seckill;
}
