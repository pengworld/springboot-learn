package com.peng.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.peng.demo.config.RedisConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class TestServiceImpl {

    @Autowired
    private RedisConfig redisConfig;

    public static final String SCORE_RANK = "score_rank";

    @Test
    public void batchAdd() {
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            DefaultTypedTuple<Object> tuple = new DefaultTypedTuple<>("张三" + i, 1D + i);
            tuples.add(tuple);
        }
        System.out.println("循环时间：" + (System.currentTimeMillis() - start));
        long num = redisConfig.redisTemplate().opsForZSet().add(SCORE_RANK, tuples);
        System.out.println("批量新增时间：" + (System.currentTimeMillis() - start));
        System.out.println("受影响行数：" + num);
    }

    /**
     * 获取排行列表
     */
    public void list() {
        Set<Object> range = redisConfig.redisTemplate().opsForZSet().reverseRange(SCORE_RANK, 0, 10);
        System.out.println("获取到的排行列表：" + JSON.toJSONString(range));

        Set<ZSetOperations.TypedTuple<Object>> rangeWithSources = redisConfig.redisTemplate()
                .opsForZSet().reverseRangeWithScores(SCORE_RANK, 0, 10);
        System.out.println("获取到的排行和分数列表：" + JSON.toJSONString(rangeWithSources));
    }

    @Test
    public void add() {
        redisConfig.redisTemplate().opsForZSet().add(SCORE_RANK, "李四", 8899);
    }

    @Test
    public void find() {
        long rankNum = redisConfig.redisTemplate().opsForZSet().reverseRank(SCORE_RANK, "李四");
        System.out.println("李四的个人排名：" + rankNum);

        Double score = redisConfig.redisTemplate().opsForZSet().score(SCORE_RANK, "李四");
        System.out.println("李四的分数：" + score);
    }
}
