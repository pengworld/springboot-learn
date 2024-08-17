package com.peng.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
@Profile("prod")
public class TestProfile {

    //@Profile注解可用于修饰类 方法 注解
    @Profile("prod")
    public void testProfile() {

    }

    //prod mail配置参数
    @Value("${spring.mail.hostname}")
    private String hostname;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.from}")
    private String from;

    public String test(){
        return hostname;
    }

}
