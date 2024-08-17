package com.peng.demo.annotation;

import java.lang.annotation.*;

//自定义注解  比如权限校验、日志记录、统计等非业务代码
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {
    String LogName() default "操作日志";

    String logType();

    String logModule();
}

