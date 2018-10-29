package com.book.collect.dao;

import com.book.collect.entity.Collect;

import java.util.List;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    List<Collect> selectCollectRecordById(Integer id);

    Collect selectCollectByUserIdBookIdState(Collect collect);

}