package com.book.user.controller;

import com.book.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
    *@Date 2018/8/28 17:52
    *@Description 返回用户首页
    **/
    @RequestMapping("/index")
    public String index(){
        return "book";
    }

    /**
     *@Date 2018/8/28 18:14
     *@Description 获取用户信息
     **/
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Map<String,Object> getUserInfo(){
        return userService.getUserInfo();
    }

    /**
    *@Date 2018/8/30 11:35
    *@Description 修改用户密码
    **/
    @ResponseBody
    @RequestMapping("/changePassword")
    public Map<String,Object> changePassword(String oldPassword , String newPassword, String newPassword_re)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return userService.changePassword(oldPassword , newPassword,newPassword_re);
    }

    /**
    *@Date 2018/8/30 11:52
    *@Description 用户登出
    **/
    @ResponseBody
    @RequestMapping("/logout")
    public Map<String,Object> logout(){
        return userService.logout();
    }

}
