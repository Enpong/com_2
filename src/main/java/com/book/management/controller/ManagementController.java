package com.book.management.controller;


import com.book.book.entity.Book;
import com.book.book.service.BookService;
import com.book.common.page.PageQueryBean;
import com.book.record.entity.Record;
import com.book.record.page.RecordQueryCondition;
import com.book.record.service.RecordService;
import com.book.user.entity.User;
import com.book.user.page.UserQueryCondition;
import com.book.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Controller
@RequestMapping("/management")
@ResponseBody
public class ManagementController {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    RecordService recordService;

    /**
    *@Date 2018/9/7 16:59
    *@Description 获取管理员页面
    **/
    @RequestMapping("/index")
    public String index(){
        return "management";
    }
    
    /**
    *@Date 2018/8/28 15:11
    *@Description 注册
    **/
    @RequestMapping("/addUser")
    @ResponseBody
    public Map<String,Object> addUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
       return userService.addUser(user);
    }

    /**
    *@Date 2018/8/31 10:13
    *@Description 删除用户
    **/
    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map<String , Object> deleteUser(int user_id){
        return userService.deleteUser(user_id);
    }
    
    /**
    *@Date 2018/8/31 9:30
    *@Description 获取用户信息 -分页
    **/
    @ResponseBody
    @RequestMapping("/getUsersInfoByPage")
    public PageQueryBean getUsersInfoByPage(UserQueryCondition condition){
        return userService.getUsersInfoByPage(condition);
    }
    
    /**
    *@Date 2018/8/31 10:20
    *@Description 更新用户
    **/
    @ResponseBody
    @RequestMapping("/updateUser")
    public Map<String,Object> updateUser(User user){
        return userService.updateUser(user);
    }
    
    /**
    *@Date 2018/8/31 10:48
    *@Description 添加书籍
    **/
    @RequestMapping("/addBook")
    @ResponseBody
    public Map<String,Object> addBook(MultipartFile file , Book book , HttpServletRequest request){
        return bookService.addBook(file,request,book);
    }

    /**
    *@Date 2018/8/31 14:56
    *@Description 删除书籍
    **/
    @RequestMapping("/deleteBook")
    @ResponseBody
    public Map<String,Object> deleteBook(int book_id){
        return bookService.deleteBook(book_id);
    }
    
    /**
    *@Date 2018/8/31 15:04
    *@Description 更新书籍信息
    **/
    @ResponseBody
    @RequestMapping("/updateBook")
    public Map<String,Object> updateById(MultipartFile file,Book book , HttpServletRequest request){
        return bookService.updateBook(file,book,request);
    }
    
    /**
    *@Date 2018/8/31 15:36
    *@Description 增加借阅记录
    **/
    @RequestMapping("/addRecord")
    @ResponseBody
    public Map<String,Object> addRecord(Record record){
        return recordService.addRecord(record);
    }
    
    /**
    *@Date 2018/8/31 15:44
    *@Description 删除借阅记录
    **/
    @ResponseBody
    @RequestMapping("/deleteRecord")
    public Map<String,Object> deleteRecord(Record record){
        return  recordService.deleteRecord(record);
    }

    /**
    *@Date 2018/9/4 18:40
    *@Description 查询借阅记录 - 分页
    **/
    @RequestMapping("/getRecordInfoByPage")
    @ResponseBody
    public PageQueryBean getRecordInfoByPage(RecordQueryCondition condition){
        return recordService.getRecordInfoByPage(condition);
    }

    /**
    *@Date 2018/9/5 18:02
    *@Description 获取单个用户信息 - 管理员
    **/
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Map<String,Object> getUserInfo(int user_id){
        return userService.getUserInfo(user_id);
    }

}
