package com.example.springbootdruids.service.cluster.impl;

import com.example.springbootdruids.bean.TStudent;
import com.example.springbootdruids.mapper.cluster.TStudentMapper;
import com.example.springbootdruids.service.cluster.TStudentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName TStudentServiceImpl
 * @Description TODO
 * @Author liangkanglin
 * @Date 2019/5/29 10:14
 * Version 1.0
 **/
@Service
public class TStudentServiceImpl implements TStudentService {

    @Autowired
    TStudentMapper tStudentMapper;

    @Override
    public void save(TStudent entity) throws Exception {
        tStudentMapper.insert(entity);
    }

    @Override
    public void update(TStudent entity) {
        tStudentMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public void delete(int Id) {
        tStudentMapper.deleteByPrimaryKey(Id);
    }

    @Override
    public TStudent queryById(int Id) {
        TStudent tStudent = tStudentMapper.selectByPrimaryKey(Id);
        if(tStudent!=null){
            return tStudent;
        }
        return null;
    }

    @Override
    public List<TStudent> queryList(TStudent entity) {
        return null;
    }

    @Override
    public List<TStudent> queryListPaged(TStudent entity, Integer page, Integer pageSize) {
        // 开始分页
        PageHelper.startPage(page, pageSize);
        Example example = new Example(TStudent.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmptyOrWhitespace(entity.getName())) {
            criteria.andLike("name", "%" + entity.getName() + "%");
        }
//        example.orderBy("registTime").desc();
        List<TStudent> studentList = tStudentMapper.selectByExample(example);
        return studentList;
    }

    @Override
    public TStudent queryByIdCustom(String Id) {
        return null;
    }

    @Override
    public void saveTransactional(TStudent entity) {

    }
}
