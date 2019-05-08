package com.example.spring_boot_mybatis.service.impl;

import com.example.spring_boot_mybatis.bean.City;
import com.example.spring_boot_mybatis.mapper.CityMapper;
import com.example.spring_boot_mybatis.service.CityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.github.pagehelper.PageHelper;


import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName CityServiceImpl
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/8 16:07
 * Version 1.0
 **/
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;



    @Override
    public void saveCity(City city) throws Exception {
        cityMapper.insert(city);
    }

    @Override
    public void updateCity(City city) {
        cityMapper.updateByPrimaryKey(city);
    }

    @Override
    public void deleteCity(String cityId) {
        cityMapper.deleteByPrimaryKey(cityId);
    }

    @Override
    public City queryCityById(String cityId) {

        return cityMapper.selectByPrimaryKey(cityId);
    }

    @Override
    public List<City> queryCityList(City city) {
        return null;
    }

    @Override
    public List<City> queryCityListPaged(City city, Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public City queryCityByIdCustom(String cityId) {
        return null;
    }

    @Override
    public void saveCityTransactional(City city) {

    }
}
