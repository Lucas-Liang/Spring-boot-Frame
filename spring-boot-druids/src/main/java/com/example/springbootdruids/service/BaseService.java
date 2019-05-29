package com.example.springbootdruids.service;

import java.util.List;

/**---   最基础的数据库语句   ---**/
public interface BaseService<T> {

    void save(T entity) throws Exception;

    void update(T entity);

    void delete(int Id);

    T queryById(int Id);

    List<T> queryList(T entity);

    List<T> queryListPaged(T entity, Integer page, Integer pageSize);

    T queryByIdCustom(String Id);

    void saveTransactional(T entity);
}
