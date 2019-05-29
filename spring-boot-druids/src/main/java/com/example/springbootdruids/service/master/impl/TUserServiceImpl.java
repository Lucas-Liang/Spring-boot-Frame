package com.example.springbootdruids.service.master.impl;

import com.example.springbootdruids.bean.TUser;
import com.example.springbootdruids.mapper.master.TUserMapper;
import com.example.springbootdruids.service.master.TUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName TUserServiceImpl
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/29 10:13
 * Version 1.0
 **/
@Service
public class TUserServiceImpl implements TUserService {

    @Autowired
    TUserMapper tUserMapper;

    @Override
    public void save(TUser entity) throws Exception {
        tUserMapper.insert(entity);
    }

    @Override
    public void update(TUser entity) {
        tUserMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public void delete(int Id) {
        tUserMapper.deleteByPrimaryKey(Id);
    }

    @Override
    public TUser queryById(int Id) {
       TUser tUser = tUserMapper.selectByPrimaryKey(Id);
       if(tUser!=null){
        return tUser;
       }
       return null;
    }

    @Override
    public List<TUser> queryList(TUser entity) {
        return null;
    }

    @Override
    public List<TUser> queryListPaged(TUser entity, Integer page, Integer pageSize) {
        // 开始分页
        PageHelper.startPage(page, pageSize);
        Example example = new Example(TUser.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmptyOrWhitespace(entity.getName())) {
            criteria.andLike("name", "%" + entity.getName() + "%");
        }
//        example.orderBy("registTime").desc();
        List<TUser> userList = tUserMapper.selectByExample(example);
        return userList;
    }

    @Override
    public TUser queryByIdCustom(String Id) {
        return null;
    }

    @Override
    public void saveTransactional(TUser entity) {

    }
}
