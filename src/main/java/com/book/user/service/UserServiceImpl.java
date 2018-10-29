package com.book.user.service;

import com.book.collect.dao.CollectMapper;
import com.book.collect.entity.Collect;
import com.book.common.page.PageQueryBean;
import com.book.common.util.MD5Utils;
import com.book.record.dao.RecordMapper;
import com.book.record.entity.Record;
import com.book.user.dao.UserMapper;
import com.book.user.entity.User;
import com.book.user.page.UserQueryCondition;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RecordMapper recordMapper;

    @Autowired
    CollectMapper collectMapper;


    /**
    *@Date 2018/8/28 15:34
    *@Description 查询用户 - 用户名
    **/
    @Override
    public User findUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }


    /**
    *@Date 2018/8/28 15:34
    *@Description 增加用户
    **/
    @Override
    public Map<String, Object> addUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Map<String,Object> result = new HashMap<>();
//       检验是否已经存在
        User record = userMapper.selectByUsername(user.getUser());
        if (record != null){
            result.put("result" , "false");
            result.put("errorCode" , 0);
            result.put("info" , "账号已经存在");
            return result;
        }

        user.setPassword(MD5Utils.enctpty(user.getPassword()));
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setState(true);
        userMapper.insertSelective(user);

        result.put("result" , "true");
        result.put("errorCode" , "");
        result.put("info" ,"添加成功");
        return result;
    }

    /**
     *@Date 2018/8/29 9:19
     *@Description 获取用户信息
     **/
    @Override
    public Map<String, Object> getUserInfo() {
        Map<String , Object> result = new HashMap<>();

        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userinfo");

        user = userMapper.selectiveByUsername(user.getUser());


        if(user == null){
            result.put("result" , "false");
            result.put("info" , "用户不存在");
            result.put("error" , "1");
            return result;
        }
//        获取收藏记录
        List<Collect> collectList = collectMapper.selectCollectRecordById(user.getId());

//        获取借阅记录
        List<Record> recordList = recordMapper.selectBorrowHistoryByUserId(user.getId());
        List<Record> historyInfo = new ArrayList<>();
        List<Record> borrowInfo = new ArrayList<>();

        for(Record record : recordList){
            if (record.getState() == 0) {
                historyInfo.add(record);
            } else {
                borrowInfo.add(record);
            }
        }
        result.put("result" , "true");
        result.put("info" , "查询成功");
        result.put("error" , "");
        result.put("userInfo" , user);
        result.put("borrowInfo" , borrowInfo);
        result.put("historyInfo" , historyInfo);
        result.put("collectInfo" , collectList);

        return result;
    }

    /**
    *@Date 2018/8/30 11:39
    *@Description 修改用户密码
    *
     * @param oldPassword
     * @param newPassword
     * @param newPassword_re*/
    @Override
    public Map<String, Object> changePassword(String oldPassword, String newPassword, String newPassword_re){
        Map<String,Object> result = new HashMap<>();

        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userinfo");

        try {
            if(!newPassword.equals(newPassword_re)){
                result.put("result","false");
                result.put("info","两次输入不一致");
                result.put("error" , 0);
                return result;
            }

            if(!user.getPassword().equals(MD5Utils.enctpty(oldPassword))){
                result.put("result" , "false");
                result.put("error" , 1);
                result.put("info" , "密码错误");
                return result;
            }

            user.setPassword(MD5Utils.enctpty(newPassword));
            userMapper.updateByPrimaryKey(user);
            result.put("result" , "true");
            result.put("error" , "");
            result.put("info" , "修改成功");
        }
        catch (Exception e){
            result.put("result" , "false");
            result.put("error" , 2);
            result.put("info" , "修改失败");
            return result;
        }

        return result;
    }

    /**
    *@Date 2018/8/30 11:54
    *@Description 用户登出
    **/
    @Override
    public Map<String, Object> logout() {
        Map<String , Object> result = new HashMap<>();

        try{
            SecurityUtils.getSubject().getSession().stop();
            result.put("result" , "true");
            result.put("error" , "");
            result.put("info" , "登出成功");
        }catch(Exception e){
            result.put("result" , "false");
            result.put("error" , 0);
            result.put("info" , "登出失败");
            return result;
        }

        return result;
    }

    /**
    *@Date 2018/8/31 9:34
    *@Description 获取用户信息 - 分页
    **/
    @Override
    public PageQueryBean getUsersInfoByPage(UserQueryCondition condition) {

        int count = userMapper.countByCondition(condition);

        PageQueryBean pageResult = new PageQueryBean();
        if (count > 0){
            pageResult.setTotalRows(count);
            pageResult.setCurrentPage(condition.getCurrentPage());
            pageResult.setPageSize(condition.getPageSize());
            List<User> users = userMapper.selectUserPage(condition);
            pageResult.setItems(users);
        }

        return pageResult;
    }

    /**
    *@Date 2018/8/31 10:21
    *@Description 删除用户
    **/
    @Override
    public Map<String, Object> deleteUser(int user_id) {
        Map<String,Object> result = new HashMap<>();

        User user = userMapper.selectByPrimaryKey(user_id);

        if (user == null || user.getState() == false){
            result.put("result" , "false");
            result.put("error" , 0);
            result.put("info" , "删除失败");
            return result;
        }

        try{
            user.setState(false);
            user.setUpdatetime(new Date());
            userMapper.updateByPrimaryKeySelective(user);
            result.put("result" , "true");
            result.put("error" , "");
            result.put("info" , "删除成功");
        } catch (Exception e){
            result.put("result" , "false");
            result.put("error" , 0);
            result.put("info" , "删除失败");
            return result;
        }

        return result;
    }

    /**
    *@Date 2018/8/31 10:22
    *@Description 更新用户
     * @param user*/
    @Override
    public Map<String, Object> updateUser(User user) {
        Map<String , Object> result = new HashMap<>();

        if (user.getId() == null){
            result.put("result" , "false");
            result.put("error" , 1);
            result.put("info" , "Id为空");
            return result;
        }

        if (user.getUser() != null){
            User fuser = userMapper.selectByUsernameId(user);
            if (fuser != null){
                result.put("result" , "false");
                result.put("error" , 0);
                result.put("info" , "用户名冲突");
                return result;
            }
        }

        User tuser = userMapper.selectByPrimaryKey(user.getId());
        if (tuser == null ||tuser.getState() == false){
            result.put("result" , "true");
            result.put("error" , 2);
            result.put("info" , "用户不存在");
            return result;
        }

        try{
            userMapper.updateByPrimaryKeySelective(user);
            result.put("result" , "true");
            result.put("error" , "");
            result.put("info" , "更新成功");
        }catch (Exception e){
            result.put("result" , "false");
            result.put("error" , 3);
            result.put("info" , "更新失败");
            return result;
        }

        return result;
    }

    /**
    *@Date 2018/9/5 18:08
    *@Description 获取单个用户信息 - 管理员
    **/
    @Override
    public Map<String, Object> getUserInfo(int user_id) {

        Map<String , Object> result = new HashMap<>();

        User user = userMapper.selectByPrimaryKeySelective(user_id);

        if(user == null){
            result.put("result" , "false");
            result.put("info" , "用户不存在");
            result.put("error" , "0");
            return result;
        }

        List<Collect> collectList = collectMapper.selectCollectRecordById(user.getId());


        List<Record> recordList = recordMapper.selectBorrowHistoryByUserId(user.getId());
        List<Record> historyInfo = new ArrayList<>();
        List<Record> borrowInfo = new ArrayList<>();

        for(Record record : recordList){
            if (record.getState() == 0) {
                historyInfo.add(record);
            } else {
                borrowInfo.add(record);
            }
        }
        result.put("result" , "true");
        result.put("info" , "查询成功");
        result.put("error" , "");
        result.put("userInfo" , user);
        result.put("borrowInfo" , borrowInfo);
        result.put("historyInfo" , historyInfo);
        result.put("collectInfo" , collectList);

        return result;

    }

    /**
    *@Date 2018/9/7 17:47
    *@Description 真实姓名授权
    **/
    @Override
    public User findUserByRealName(String realname) {
        return userMapper.selectByRealname(realname);
    }


}
