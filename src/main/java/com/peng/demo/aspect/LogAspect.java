package com.peng.demo.aspect;

import com.peng.demo.annotation.OperateLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 定义切入点  自定义注解类路径
     */
    @Pointcut("@annotation(com.peng.demo.annotation.OperateLog)")
    public void methodPointCut() {

    }

    /**
     * 前置通知
     */
    @Before("methodPointCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info("测试前置通知@Before");
    }

    /**
     * 后置通知
     */
    @After("methodPointCut()")
    public void afterAdvice(JoinPoint joinPoint) {
        log.info("测试后置通知@After");
    }

    /**
     * 环绕通知
     */
    @Around("methodPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("测试环绕通知@Around 环绕通知前");
        long start = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        log.info("环绕通知.....统计方法执行的时间为：{}ms", String.valueOf(System.currentTimeMillis() - start));
        log.info("测试环绕通知@Around 环绕通知后");
        return proceed;
    }


    /**
     * 后置返回通知
     */
    @AfterReturning(returning = "result", pointcut = "methodPointCut()")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        OperateLog annotation = signature.getMethod().getAnnotation(OperateLog.class);
        String logName = annotation.logName();
        String logType = annotation.logType();
        String logModule = annotation.logModule();

        //创建时间字段
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(new Date());

        //获取方法返回值
        log.info(" @AfterReturning:{}", result);

        //保存相关日志操作  调用kafka(消息中间件),通知ES保存相关日志记录（大日志量） 或者 保存到数据库
        log.info("{}->类型:{}->模块:{}->创建时间:{}", logName, logType, logModule, createTime);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("methodPointCut()")
    public void afterThrowing(JoinPoint joinPoint) {
        log.info("测试后置异常通知@AfterThrowing");
    }

}
