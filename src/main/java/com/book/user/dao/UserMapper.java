package com.book.user.dao;

import com.book.record.entity.Record;
import com.book.user.entity.User;
import com.book.user.page.UserQueryCondition;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);

    User selectiveByUsername(String user);

    int countByCondition(UserQueryCondition condition);

    List<User> selectUserPage(UserQueryCondition condition);

    User selectByUsernameId(User user);

    User selectByPrimaryKeySelective(int user_id);

    User selectByRealname(String realname);

}