package com.peng.demo.util.quartutil;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 任务执行类
 */
public class QuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date() + "====="));
        //job的名字
        String jobName = jobExecutionContext.getJobDetail().getKey().getName();

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String param = dataMap.getString("param");
        System.out.println("传递的参数是：" + param + "任务名字是=" + jobName);
    }
}
