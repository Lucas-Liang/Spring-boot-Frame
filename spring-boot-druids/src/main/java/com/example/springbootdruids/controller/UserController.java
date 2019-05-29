package com.example.springbootdruids.controller;

import com.example.springbootdruids.bean.TUser;
import com.example.springbootdruids.service.master.TUserService;
import com.example.springbootrestful.utils.JSONResult;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/29 10:06
 * Version 1.0
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    TUserService userService;

    @GetMapping("/get")
    private String get(String id){
        TUser tUser = userService.queryById(Integer.parseInt(id));
        return JSONResult.success(new Gson().toJson(tUser));
    }

    @GetMapping("/save")
    private String save() throws Exception {
        TUser tUser = new TUser();
        tUser.setAge(20);
        tUser.setName("www.omooms.top");
        userService.save(tUser);
        return JSONResult.success();
    }

    @GetMapping("/delete")
    private String save(String id) throws Exception {
        userService.delete(Integer.parseInt(id));
        return JSONResult.success();
    }



}
