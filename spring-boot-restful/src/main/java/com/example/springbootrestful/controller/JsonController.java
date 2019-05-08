package com.example.springbootrestful.controller;


import com.example.springbootrestful.bean.User;
import com.example.springbootrestful.utils.JSONResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName JsonController
 * @Description  用来测试JSON返回的数据格式
 * @Author liangkanglin
 * @Date 2019/5/6 17:35
 * Version 1.0
 **/
@RestController
public class JsonController {


    @RequestMapping("/user")
    private String User(){
        User user = new User();
        user.setAddress("成都XX科技有限公司");
        user.setName("张三");
        user.setSex("男");
        user.setPassword("123123");
        user.setPhone("13531657389");

        return JSONResult.success(user);
    }

    @RequestMapping("/list")
    private String UserList(){
        List<User> list =new ArrayList();
        for (int i = 0; i <4 ; i++) {
            User user = new User();
            user.setAddress("成都XX科技有限公司"+i);
            user.setName("张三");
            user.setSex("男");
            user.setPassword("123123");
            user.setPhone("13531657389");
            list.add(user);
        }

        return JSONResult.success(list);
    }

    @RequestMapping("/ok")
    private String Ok(){
        return JSONResult.success();
    }

    @RequestMapping("/fail")
    private String Fail(){
        return JSONResult.fail();
    }

    @RequestMapping("/failMsg")
    private String Fail(String Msg){
        return JSONResult.fail(Msg);
    }




}
