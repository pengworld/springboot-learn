package com.peng.demo.controller;

import com.peng.demo.handler.exception.GlobalExceptionHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/home")
    @PreAuthorize("@ex.hasAuthority('test')")
    public String home() throws Exception {
        throw new GlobalExceptionHandler("101", "Global Error 错误");
    }
}
