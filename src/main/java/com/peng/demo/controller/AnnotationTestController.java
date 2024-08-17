package com.peng.demo.controller;

import com.peng.demo.annotation.OperateLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AnnotationTestController {

    @GetMapping("/test")
    @OperateLog(logName = "查询日志", logType = "select", logModule = "用户管理")
    public void test() {
        System.out.println("自定义日志记录注解测试");
    }
}
