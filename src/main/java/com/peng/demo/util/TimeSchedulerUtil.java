package com.peng.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@EnableScheduling
public class TimeSchedulerUtil {

    public static void main(String[] args) {
        scheduled1();
    }

    public static void timeTest() {
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                System.out.println("task run" + new Date());
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 10, 3000);
    }


    public static void scheduledExecutorService() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        //参数： 任务体 首次执行的延迟时间 任务执行间隔 间隔时间单位
        service.scheduleAtFixedRate(
                () -> System.out.println("task ScheduledExecutorService" + new Date()),
                0,
                3,
                TimeUnit.SECONDS);


    }

    @Scheduled(cron = "*/5 * * * * ?")
    public static void scheduleService() {
        log.info("======>>>>>使用cron表达式 {}", System.currentTimeMillis());
    }

    @Scheduled(fixedRate = 3000)
    public static void scheduled1() {
        log.info("=====>>>>>使用fixedRate {}", System.currentTimeMillis());
    }

    @Scheduled(fixedDelay = 5000)
    public static void scheduled2() {
        log.info("======>>>>fixedDelay {}",System.currentTimeMillis());
    }

    //主类上使用@EnableScheduling注解开启对定时任务 三个定时任务都已经执行 @Scheduled注解 并且在同一个线程中串行执行
    //可以在xml配置文件中添加线程池配置  @Configuration 表明是一个配置类  @EnableAsync 开启异步时间的支持  在定时任务类或者方法上添加@Async
}
