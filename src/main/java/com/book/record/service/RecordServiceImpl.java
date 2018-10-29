package com.book.record.service;

import com.book.book.dao.BookMapper;
import com.book.book.entity.Book;
import com.book.common.page.PageQueryBean;
import com.book.record.dao.RecordMapper;
import com.book.record.entity.Record;
import com.book.record.page.RecordQueryCondition;
import com.book.user.dao.UserMapper;
import com.book.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("recordServiceImpl")
public class RecordServiceImpl implements RecordService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    RecordMapper recordMapper;

    @Autowired
    BookMapper bookMapper;


    /**
    *@Date 2018/8/30 14:22
    *@Description 借书接口
    **/
    @Override
    public Map<String, Object> borrowBook(int book_id) {
        Map<String , Object> result = new HashMap<>();

        Book book = bookMapper.selectByPrimaryKey(book_id);
        if (book == null){
            result.put("result" , "false");
            result.put("error" , "0");
            result.put("info" , "借阅书籍不存在");
            return result;
        }

        Record record = recordMapper.selectBorrowHistoryByBookId(book_id);
        if(record != null){
            result.put("result" , "false");
            result.put("error" , "1");
            result.put("info" , "书籍已经被人借走");
            return result;
        }

        try {
            User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userinfo");
            String user_id = user.getId().toString();
            Date createtime = new Date();
            Date updatetime = new Date();
            Integer state = new Integer(1);
            record = new Record( user_id ,new Integer(book_id),createtime ,updatetime, state);
            recordMapper.insertSelective(record);
            result.put("result", "true");
            result.put("error", "");
            result.put("info", "借书成功");
        }catch(Exception e){
            result.put("result", "false");
            result.put("error", "2");
            result.put("info", "借书失败");
            return result;
        }


        return result;
    }

    /**
    *@Date 2018/8/30 14:45
    *@Description 还书接口
    **/
    @Override
    public Map<String, Object> returnBook(int book_id) {
        Map<String , Object> result = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userinfo");
        Integer user_id = user.getId();
        Record record = new Record(user_id.toString() , book_id);

        Book book = bookMapper.selectByPrimaryKey(book_id);
        if (book == null){
            result.put("result" , "false");
            result.put("error" , "0");
            result.put("info" , "归还书籍不存在");
            return result;
        }

        Record frecord = recordMapper.selectRecordByUserIdBookId(record);
        if (frecord == null){
            result.put("result", "false");
            result.put("error", "1");
            result.put("info", "没有借阅这本书");
            return  result;
        }

        try{
            frecord.setUpdatetime(new Date());
            frecord.setState(new Integer(0));
            recordMapper.updateByPrimaryKeySelective(frecord);
            result.put("result", "true");
            result.put("error", "");
            result.put("info", "还书成功");
        }catch (Exception e){
            result.put("result", "false");
            result.put("error", "2");
            result.put("info", "还书失败");
            return result;
        }

        return result;
    }

    /**
    *@Date 2018/8/31 15:40
    *@Description 添加借阅记录
    **/
    @Override
    public Map<String, Object> addRecord(Record record) {
        Map<String,Object> result = new HashMap<>();

        Record frecord = recordMapper.selectRecordByUserIdBookId(record);
        if (frecord != null){
            result.put("result", "false");
            result.put("error", "0");
            result.put("info", "记录存在");
            return result;
        }

        User user = userMapper.selectByPrimaryKey(Integer.parseInt(record.getUserId()));
        Book book = bookMapper.selectByPrimaryKey(record.getBookId());
        if (user == null || user.getState() == false || book == null || book.getState() == false){
            result.put("result", "false");
            result.put("error", "1");
            result.put("info", "信息错误");
            return result;
        }


        try{
            record.setState(1);
            record.setUpdatetime(new Date());
            record.setBorrowtime(new Date());
            recordMapper.insertSelective(record);
            result.put("result", "true");
            result.put("error", "");
            result.put("info", "添加成功");
        }catch (Exception e){
            result.put("result", "false");
            result.put("error", "2");
            result.put("info", "添加失败");
            return result;
        }

        return result;
    }

    /**
     *@Date 2018/8/31 15:46
     *@Description 删除借阅记录
     **/
    @Override
    public Map<String, Object> deleteRecord(Record record) {
        Map<String,Object> result = new HashMap<>();

        Record frecord = recordMapper.selectRecordByUserIdBookId(record);

        if (frecord == null){
            result.put("result", "false");
            result.put("error", "0");
            result.put("info", "记录不存在");
            return result;
        }

        try{
            frecord.setState(0);
            frecord.setUpdatetime(new Date());
            recordMapper.updateByPrimaryKeySelective(frecord);
            result.put("result", "true");
            result.put("error", "");
            result.put("info", "删除成功");
        }catch (Exception e){
            result.put("result", "false");
            result.put("error", "1");
            result.put("info", "删除失败");
            return result;
        }

        return result;
    }

    /**
    *@Date 2018/9/4 18:44
    *@Description 查找借阅记录 - 分页
    **/
    @Override
    public PageQueryBean getRecordInfoByPage(RecordQueryCondition condition) {

        int count = recordMapper.countByCondition(condition);

        PageQueryBean pageResult = new PageQueryBean();
        if (count > 0){
            pageResult.setTotalRows(count);
            pageResult.setCurrentPage(condition.getCurrentPage());
            pageResult.setPageSize(condition.getPageSize());
            List<Record> records = recordMapper.selectRecordPage(condition);
            pageResult.setItems(records);
        }

        return pageResult;
    }


}
