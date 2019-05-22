package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.bean.City;
import com.example.springbootmybatis.service.CityService;
import com.example.springbootrestful.utils.JSONResult;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CityController
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/8 16:56
 * Version 1.0
 **/
@RestController
public class CityController {

    @Autowired
    private Sid sid;

    @Autowired
    private CityService cityService;

    @GetMapping("/save")
    private String saveCity()  {

        City city = new City();
        city.setCountrycode(sid.nextShort());
        city.setDistrict("中国");
        city.setPopulation(123);
        city.setName("岳池");
        try {
            cityService.saveCity(city);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSONResult.success();
    }

    @GetMapping("/deleteCity")
    private String deleteCityById(@PathVariable String id)  {

        System.out.println(""+id);
        cityService.deleteCity(Integer.parseInt(id));

        return JSONResult.success();
    }
    @GetMapping("/updataCity")
    private String updataCity()  {

        City city = new City();
        city.setId(19);
        city.setDistrict("广安");
        city.setPopulation(123);
        city.setName("岳池");
        cityService.updateCity(city);

        return JSONResult.success();
    }


    //分页请求
    @GetMapping("/listCity")
    private String listCity(@PathVariable Integer page)  {

        if(page==null||page<=0){
            page =1;
        }

        int pageSize = 10;

        City city = new City();
//		user.setNickname("lee");

        List<City> userList = cityService.queryCityListPaged(city, page, pageSize);

        return JSONResult.success();
    }
}
