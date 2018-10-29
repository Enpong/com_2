package com.book.book.dao;

import com.book.book.entity.Book;
import com.book.book.page.BookQueryCondition;
import com.book.book.page.SearchQueryCondition;
import com.book.record.entity.Record;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    int countByCondition(BookQueryCondition condition);

    List<Book> selectBookPage(BookQueryCondition condition);

    Record selectBookBorrowRecord(Book book);

    int countBySearchCondition(SearchQueryCondition condition);

    List<Book> selectSearchBookPage(SearchQueryCondition condition);

    Book selectByPrimaryKeyState(int book_id);

}