package com.example.spring_boot_mybatis.service;

import com.example.spring_boot_mybatis.bean.City;

import java.util.List;

/**
 * @ClassName cityService
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/8 16:01
 * Version 1.0
 **/
public interface CityService {

    public void saveCity(City city) throws Exception;

    public void updateCity(City city);

    public void deleteCity(int cityId);

    public City queryCityById(String cityId);

    public List<City> queryCityList(City city);

    public List<City> queryCityListPaged(City city, Integer page, Integer pageSize);

    public City queryCityByIdCustom(String cityId);

    public void saveCityTransactional(City city);


}
