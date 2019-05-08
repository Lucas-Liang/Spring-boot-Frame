package com.example.springbootrestful.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/6 15:12
 * Version 1.0
 **/
@RestController
public class HelloController {


    @RequestMapping("/hello")
    private String Hello(){
        return "hello";
    }





}
