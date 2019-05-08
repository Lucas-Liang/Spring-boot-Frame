package com.example.springbootrestful.controller;

import com.example.springbootrestful.utils.JSONResult;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName RestController
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/7 18:01
 * Version 1.0
 **/
@RestController
public class RestFulController {

    @PutMapping("/put")
    private String put(){
        return JSONResult.success("使用PUT请求成功");
    }
    @DeleteMapping("/delete")
    private String delete(){
        return JSONResult.success("使用DELETE请求成功");
    }
    @GetMapping("/get")
    private String get(){
        return JSONResult.success("使用GET请求成功");
    }
    @PostMapping("/post")
    private String post(){
        return JSONResult.success("使用POST请求成功");
    }

}
