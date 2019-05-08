package com.example.springbootrestful.utils;

import com.google.gson.Gson;


/**
 * @ClassName JSONResult
 * @Description 用来返回相关的数据类型JSON工具类
 * @Author liangkanglin
 * @Date 2019/5/6 16:23
 * Version 1.0
 **/

/** 200：表示成功
  * 500：表示错误，错误信息在msg字段中
 * */
public class JSONResult {

    //相关的网络状态码
    private static Integer STATUS_SUCCESS = 200;
    private static Integer STATUS_FAIL = 201;

    //提示信息
    private static String HTTP_SUCCESS = "success";
    private static String HTTP_FAIL = "fail";

    private static Gson gson =new Gson();

    //成功的简单操作
    public static String success(){
        return gson.toJson(new Result(STATUS_SUCCESS,HTTP_SUCCESS,"成功"));
    }

    //自定义失败的字符
    public static String success(Object msg){
        return gson.toJson(new Result(STATUS_SUCCESS,HTTP_SUCCESS,msg));
    }

    //自定义成功的字符和返回码
    public static String success(Object code ,Object msg){
        return gson.toJson(new Result(code,HTTP_SUCCESS,msg));
    }

    //失败的简单操作
    public static String fail(){
        return gson.toJson(new Result(STATUS_FAIL,HTTP_FAIL,"失败"));
    }

    //自定义失败的字符
    public static String fail(Object msg){
        return gson.toJson(new Result(STATUS_FAIL,HTTP_FAIL,msg));
    }
    //自定义失败的字符
    public static String fail(Object code,Object msg){
        return gson.toJson(new Result(code,HTTP_FAIL,msg));
    }


    private static class Result{

        private Object code;
        private String status;
        private Object msg;

        public Result() {

        }

        public Result(Object code, String status, Object msg) {
            this.code = code;
            this.status = status;
            this.msg = msg;
        }

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getMsg() {
            return msg;
        }

        public void setMsg(Object msg) {
            this.msg = msg;
        }
    }



}
