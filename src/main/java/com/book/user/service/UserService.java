package com.book.user.service;

import com.book.common.page.PageQueryBean;
import com.book.user.entity.User;
import com.book.user.page.UserQueryCondition;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface UserService {
    /**
    *@Date 2018/8/28 18:21
    *@Description 查询用户 - 用户名
    **/
    User findUserByUsername(String username);

    /**
    *@Date 2018/8/28 18:21
    *@Description 增加用户
    **/
    Map<String, Object> addUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
    *@Date 2018/8/29 10:47
    *@Description 获取用户信息
    **/
    Map<String,Object> getUserInfo();

    /**
    *@Date 2018/8/30 11:39
    *@Description 修改用户密码
    *
     * @param oldPassword
     * @param newPassword
     * @param newPassword_re*/
    Map<String,Object> changePassword(String oldPassword, String newPassword, String newPassword_re) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
    *@Date 2018/8/30 11:53
    *@Description 用户登出
    **/
    Map<String,Object> logout();

    /**
    *@Date 2018/8/31 9:33
    *@Description 获取用户信息 - 分页
    **/
    PageQueryBean getUsersInfoByPage(UserQueryCondition condition);

    /**
    *@Date 2018/8/31 10:15
    *@Description 删除用户
    **/
    Map<String,Object> deleteUser(int user_id);

    /**
    *@Date 2018/8/31 10:21
    *@Description 更新用户
    *@param user */
    Map<String,Object> updateUser(User user);

    /**
    *@Date 2018/9/5 18:05
    *@Description 获取单个用户信息 - 管理员
    **/
    Map<String,Object> getUserInfo(int user_id);

    /**
    *@Date 2018/9/7 17:35
    *@Description 真实姓名授权
    **/
    User findUserByRealName(String username);
}
