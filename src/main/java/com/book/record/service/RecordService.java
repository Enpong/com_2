package com.book.record.service;

import com.book.common.page.PageQueryBean;
import com.book.record.entity.Record;
import com.book.record.page.RecordQueryCondition;

import java.util.Map;

public interface RecordService {

    /**
    *@Date 2018/8/30 14:17
    *@Description 借书接口
    **/
    Map<String,Object> borrowBook(int book_id);

    /**
    *@Date 2018/8/30 14:45
    *@Description 还书接口
    **/
    Map<String,Object> returnBook(int book_id);

    /**
    *@Date 2018/8/31 15:38
    *@Description 增加借阅记录
    **/
    Map<String,Object> addRecord(Record record);

    /**
    *@Date 2018/8/31 15:45
    *@Description 删除借阅记录
    **/
    Map<String,Object> deleteRecord(Record record);

    /**
    *@Date 2018/9/4 18:43
    *@Description 查询借阅记录 - 分页
    **/
    PageQueryBean getRecordInfoByPage(RecordQueryCondition condition);
}
