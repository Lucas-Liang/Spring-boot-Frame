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
        //只修改涉及到的信息
        cityMapper.updateByPrimaryKeySelective(city);
//        全部修改
//        cityMapper.updateByPrimaryKey(city);
    }

    @Override
    public void deleteCity(int cityId) {
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
// 开始分页
        PageHelper.startPage(page, pageSize);

        Example example = new Example(City.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmptyOrWhitespace(city.getName())) {
            criteria.andLike("nickname", "%" + city.getName() + "%");
        }
        example.orderBy("registTime").desc();
        List<City> userList = cityMapper.selectByExample(example);

        return userList;
    }

    @Override
    public City queryCityByIdCustom(String cityId) {
        return null;
    }

    @Override
    public void saveCityTransactional(City city) {

    }
}
