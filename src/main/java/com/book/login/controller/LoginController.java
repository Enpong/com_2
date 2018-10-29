package com.book.login.controller;

import com.book.user.entity.User;
import com.book.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    /**
    *@Date 2018/8/31 16:48
    *@Description 登录页面
    **/
    @RequestMapping
    public String login(){
        return "login";
    }

    /**
    *@Date 2018/8/28 15:00
    *@Description 接受请求 调用检验
    **/
    @RequestMapping("/loginById")
    @ResponseBody
    public Map<String,Object> check(User user){
        Map<String,Object> result = new HashMap<>();
//        获取主体
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername() , user.getPassword());
        try{
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
            result.put("result" , "false");
            result.put("info" , "出错");
            return result;
        }
        User userinfo = (User) subject.getSession().getAttribute("userinfo");
        result.put("result", "true");
        result.put("errorCode",0);
        result.put("username",userinfo.getUsername());
        result.put("department",userinfo.getDepartment());
        result.put("user",userinfo.getUser());
        return result;
    }


}
