package com.book.record.dao;

import com.book.record.entity.Record;
import com.book.record.page.RecordQueryCondition;

import java.util.Date;
import java.util.List;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);
//    查看用户所有借阅记录
    List<Record> selectBorrowHistoryByUserId(Integer id);
//    查看是否被借走
    Record selectBorrowHistoryByBookId(int book_id);
//    根据user_id book_id找到记录
    Record selectRecordByUserIdBookId(Record record);

    int countByCondition(RecordQueryCondition condition);

    List<Record> selectRecordPage(RecordQueryCondition condition);
}