package com.peng.demo.controller;

import com.peng.demo.handler.exception.GlobalExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String,Object> errorHandler(Exception ex) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        return map;
    }

    @ResponseBody
    @ExceptionHandler(value = GlobalExceptionHandler.class)
    public Map<String,Object> globalErrorHandler(GlobalExceptionHandler gx) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",gx.getCode());
        map.put("msg",gx.getMsg());
        return map;
    }
}
