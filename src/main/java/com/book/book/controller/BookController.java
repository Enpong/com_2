package com.book.book.controller;

import com.book.book.page.BookQueryCondition;
import com.book.book.page.SearchQueryCondition;
import com.book.book.service.BookService;
import com.book.common.page.PageQueryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    /**
    *@Date 2018/8/29 14:33
    *@Description 根据页码返回书籍信息（条件分页）
    **/
    @RequestMapping("/getBooksByPage")
    @ResponseBody
    public PageQueryBean getBooksByPage(BookQueryCondition condition){
      return bookService.paging(condition);
    }

    /**
    *@Date 2018/9/7 10:51
    *@Description 根据书名搜索
    **/
    @ResponseBody
    @RequestMapping("/searchBook")
    public PageQueryBean searchBook(SearchQueryCondition condition){
        return bookService.searchBook(condition);
    }

    /**
    *@Date 2018/8/30 16:06
    *@Description 获取书籍详细信息
    **/
    @RequestMapping("/getBooksDetail")
    @ResponseBody
    public Map<String,Object> getBooksDetail(int book_id){
        return bookService.getBooksDetail(book_id);
    }

}
