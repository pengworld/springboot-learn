package com.peng.demo.common;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class AsyncDeal {

    //自定义defaultThreadPoolExecutor线程池
    @Bean(name = "defaultThreadPoolExecutor", destroyMethod = "shutdown")
    public ThreadPoolExecutor systemCheckPoolExecutorService() {
        return new ThreadPoolExecutor(
                3,
                10,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10000),
                //设置线程前缀 使用hutool
                new ThreadFactoryBuilder().setNamePrefix("default-executor-thread-%d").build(),
                (r, executor) -> log.error("system pool is full!")
        );
    }

    @Test
    @Async("defaultThreadPoolExecutor")
    public void sendMsg() {
        log.info("线程：{}，进入发送短信服务中...",Thread.currentThread().getName());

        try {
            log.info("模拟调用短信接口，业务耗时时间...");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("发送短信结束...");
    }
}
