package com.book.collect.service;

import com.book.book.dao.BookMapper;
import com.book.book.entity.Book;
import com.book.collect.dao.CollectMapper;
import com.book.collect.entity.Collect;
import com.book.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("collectServiceImpl")
public class CollectServiceImpl implements CollectService {

    @Autowired
    CollectMapper collectMapper ;

    @Autowired
    BookMapper bookMapper;
    
    /**
    *@Date 2018/8/30 16:25
    *@Description 添加收藏
    **/
    @Override
    public Map<String, Object> collectBook(int book_id) {
        Map<String , Object> result = new HashMap<>();

        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userinfo");
        Collect collect = new Collect(user.getId() , book_id);

        Book book = bookMapper.selectByPrimaryKey(book_id);
        if (book == null){
            result.put("result" , "false");
            result.put("error" , "0");
            result.put("info" , "收藏书籍不存在");
            return result;
        }

        Collect fcollect = collectMapper.selectCollectByUserIdBookIdState(collect);
        if(fcollect != null){
            result.put("result" , "false");
            result.put("error" , "1");
            result.put("info" , "已经收藏过此书");
            return result;
        }

        try{
            collect.setCollecttime(new Date());
            collect.setState((byte)1);
            collectMapper.insertSelective(collect);
            result.put("result" , "true");
            result.put("error" , "");
            result.put("info" , "收藏成功");
        }catch (Exception e){
            e.printStackTrace();
            result.put("result" , "false");
            result.put("error" , "2");
            result.put("info" , "收藏失败");
            return result;
        }

        return result;
    }

    /**
    *@Date 2018/8/30 16:40
    *@Description 取消收藏
    **/
    @Override
    public Map<String, Object> no_collect(int book_id) {
        Map<String ,Object> result = new HashMap<>();

        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userinfo");
        Collect collect = new Collect(user.getId() , book_id);

        Collect fcollect = collectMapper.selectCollectByUserIdBookIdState(collect);
        if (fcollect == null){
            result.put("result" , "false");
            result.put("error" , "0");
            result.put("info" , "取消收藏失败");
            return result;
        }

        try{
            fcollect.setState((byte)0);
            collectMapper.updateByPrimaryKeySelective(fcollect);
            result.put("result" , "true");
            result.put("error" , "");
            result.put("info" , "取消收藏成功");
        } catch (Exception e){
            result.put("result" , "false");
            result.put("error" , "0");
            result.put("info" , "取消收藏失败");
            return result;
        }

        return result;
    }
}
