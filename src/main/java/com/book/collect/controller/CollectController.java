package com.book.collect.controller;


import com.book.collect.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    CollectService collectService;

    /**
    *@Date 2018/8/30 16:21
    *@Description 添加收藏
    **/
    @ResponseBody
    @RequestMapping("/collectBook")
    public Map<String , Object> collectBook(int book_id){
        return  collectService.collectBook(book_id);
    }
    
    /**
    *@Date 2018/8/30 16:37
    *@Description 取消收藏
    **/
    @ResponseBody
    @RequestMapping("/no_collect")
    public Map<String , Object> no_collect(int book_id){
        return collectService.no_collect(book_id);
    }
}
