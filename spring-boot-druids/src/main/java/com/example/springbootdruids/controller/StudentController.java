package com.example.springbootdruids.controller;

import com.example.springbootdruids.bean.TStudent;
import com.example.springbootdruids.service.cluster.TStudentService;
import com.example.springbootrestful.utils.JSONResult;
import com.google.gson.Gson;
import com.mysql.jdbc.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName StudentController
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/29 10:07
 * Version 1.0
 **/
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    TStudentService studentService;

    @GetMapping("/get")
    private String get(String id){

        TStudent tStudent = studentService.queryById(Integer.parseInt(id));
        return JSONResult.success(new Gson().toJson(tStudent));
    }

    @GetMapping("/save")
    private String save() throws Exception {
        TStudent tStudent = new TStudent();
        tStudent.setAge(20);
        tStudent.setName("www.omoom.top");
        studentService.save(tStudent);
        return JSONResult.success();
    }

    @GetMapping("/delete")
    private String save(String id) throws Exception {
        studentService.delete(Integer.parseInt(id));
        return JSONResult.success();
    }





}
