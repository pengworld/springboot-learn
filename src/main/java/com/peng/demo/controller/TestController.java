package com.peng.demo.controller;

import com.peng.demo.common.AsyncDeal;
import com.peng.demo.config.MailConfigProperties;
import com.peng.demo.handler.exception.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@SpringBootTest
public class TestController {

    @RequestMapping("/home")
    @PreAuthorize("@ex.hasAuthority('test')")
    public String home() throws Exception {
        throw new GlobalExceptionHandler("101", "Global Error 错误");
    }

    @Autowired
    MailConfigProperties mailConfigProperties;

    @Test
    public void testMailConfigProperties() {
        String hostname = mailConfigProperties.getHostname();
        int port = mailConfigProperties.getPort();
        String from = mailConfigProperties.getFrom();

        System.out.println(hostname);
    }

    @Test
    public void test(){
        log.info("主线程名称：{}",Thread.currentThread().getName());
        AsyncDeal asyncDeal = new AsyncDeal();
        asyncDeal.sendMsg();
    }
}
