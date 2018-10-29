package com.book.collect.service;

import java.util.Map;

public interface CollectService {
    /**
    *@Date 2018/8/30 16:24
    *@Description 添加收藏
    **/
    Map<String,Object> collectBook(int book_id);

    /**
    *@Date 2018/8/30 16:40
    *@Description 取消收藏
    **/
    Map<String,Object> no_collect(int book_id);
}
