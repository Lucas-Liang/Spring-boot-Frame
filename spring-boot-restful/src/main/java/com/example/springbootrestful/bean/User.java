package com.example.springbootrestful.bean;

/**
 * @ClassName User
 * @Description 用户类
 * @Author liangkanglin
 * @Date 2019/5/6 17:55
 * Version 1.0
 **/
public class User{
    private String name;
    private String password;
    private String sex;
    private String address;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
