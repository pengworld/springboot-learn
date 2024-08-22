package com.peng.demo.util.quartutil;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;

public class QuartzManager {

    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "JOB_GROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "TRIGGER_GROUP_NAME";


    /**
     * 添加一个定时任务  任务名    任务类    corn表达式触发时间
     */
    @SuppressWarnings("rawtypes")
    public static void addJob(String jobName, Class cls, String time) {

        try {
            Scheduler scheduler = schedulerFactory.getScheduler();

            JobDetailImpl jobDetail = new JobDetailImpl(jobName, JOB_GROUP_NAME, cls);
            jobDetail.getJobDataMap().put("param", "peng");

            CronTriggerImpl trigger = new CronTriggerImpl(jobName, TRIGGER_GROUP_NAME);
            trigger.setCronExpression(time);

            scheduler.scheduleJob(jobDetail, trigger);

            //启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }

        } catch (SchedulerException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改一个任务的触发时间
     */
    @SuppressWarnings("rawtypes")
    public static void modifyJobTime(String jobName, String time) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除一个任务
     */
    public static void removeJob(String jobName) {

    }

    /**
     * 启动所有定时任务
     */
    public static void startJobs() {

    }

    /**
     * 关闭所有定时任务
     */
    public static void shutdownJobs() {

    }
}
