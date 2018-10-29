package com.book.book.service;

import com.book.book.entity.Book;
import com.book.book.page.BookQueryCondition;
import com.book.book.page.SearchQueryCondition;
import com.book.common.page.PageQueryBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface BookService {

    /**
     *@Date 2018/8/29 15:53
     *@Description 根据页码返回书籍信息（条件分页）
     **/
    PageQueryBean paging(BookQueryCondition condition);

    /**
    *@Date 2018/8/30 16:10
    *@Description 获取书籍详细信息
    **/
    Map<String, Object> getBooksDetail(int book_id);

    /**
    *@Date 2018/8/30 16:53
    *@Description 根据书名搜索 - 分页
    **/
    PageQueryBean searchBook(SearchQueryCondition condition);

    /**
    *@Date 2018/8/31 11:00
    *@Description 添加书籍
    **/
    Map<String,Object> addBook(MultipartFile file, HttpServletRequest request, Book book);

    /**
    *@Date 2018/8/31 14:58
    *@Description 删除书籍
    **/
    Map<String,Object> deleteBook(int book_id);

    /**
    *@Date 2018/8/31 15:06
    *@Description 更新书籍信息
    **/
    Map<String,Object> updateBook(MultipartFile file, Book book, HttpServletRequest request);
}
