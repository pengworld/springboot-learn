package com.peng.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class PathVariableTestController {

    @RequestMapping("user/{id}/{name}")
    //比如请求路径 http://localhost:8081/user/{1}/{peng}
    public void test(@PathVariable("id") Long id, @PathVariable("name") String name) {
        System.out.println("id:" + id + ",name:" + name);
    }
}
