package com.book.record.controller;

import com.book.record.service.RecordService;
import com.book.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("record")
public class RecordController {

    @Autowired
    RecordService recordService;

    /**
    *@Date 2018/8/30 14:17
    *@Description 借书接口
    **/
    @RequestMapping("/borrowBook")
    @ResponseBody
    public Map<String,Object> borrowBook(int book_id){
        return recordService.borrowBook(book_id);
    }
    
    /**
    *@Date 2018/8/30 14:44
    *@Description 还书接口
    **/
    @ResponseBody
    @RequestMapping("/returnBook")
    public Map<String,Object> returnBook(int book_id){
        return recordService.returnBook(book_id);
    }
}
