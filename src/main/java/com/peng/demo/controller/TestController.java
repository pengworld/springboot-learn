package com.peng.demo.controller;

import com.peng.demo.handler.exception.GlobalExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

public class TestController {

    @RequestMapping("/home")
    public String home() throws Exception {
        throw new GlobalExceptionHandler("101","Global Error 错误");
    }
}
