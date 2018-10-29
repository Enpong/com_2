package com.book.book.service;

import com.book.book.dao.BookMapper;
import com.book.book.entity.Book;
import com.book.book.page.BookQueryCondition;
import com.book.book.page.SearchQueryCondition;
import com.book.common.page.PageQueryBean;
import com.book.record.dao.RecordMapper;
import com.book.record.entity.Record;
import com.book.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service("bookServiceImpl")
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    /**
    *@Date 2018/8/29 15:53
    *@Description 根据页码返回书籍信息（条件分页）
    **/
    @Override
    public PageQueryBean paging(BookQueryCondition condition) {

        int count = bookMapper.countByCondition(condition);

        PageQueryBean pageResult = new PageQueryBean();
        if(count > 0){
            pageResult.setTotalRows(count);
            pageResult.setCurrentPage(condition.getCurrentPage());
            pageResult.setPageSize(condition.getPageSize());
            List<Book> books = bookMapper.selectBookPage(condition);
            for(Book book : books){
                Record record = bookMapper.selectBookBorrowRecord(book);
                book.setRecord(record);
            }
            pageResult.setItems(books);
        }

        return pageResult;

    }

    /**
    *@Date 2018/9/6 14:22
    *@Description 获取书籍详细信息
    **/
    @Override
    public Map<String, Object> getBooksDetail(int book_id) {
        Map<String ,Object> result = new HashMap<>();

        Book book = bookMapper.selectByPrimaryKey(book_id);
        if (book == null){
            result.put("result", "flase");
            result.put("error", "0");
            result.put("info", "书籍不存在");
            return result;
        }
        try {
            Record record = bookMapper.selectBookBorrowRecord(book);
            result.put("brief", book.getName() + "是一本好书 !!! -------------等待开发-----------");
            result.put("bookinfo", book);
            if (record == null) {
                result.put("borrowerInfo", "可借阅");
                result.put("borrower", "null");
            } else {
                result.put("borrowerInfo", "已借出");
                result.put("borrower", record.getUser().getUsername());
            }
        }catch (Exception e){
            result.put("result", "flase");
            result.put("error", "1");
            result.put("info", "查询出错");
            return result;
        }

        return result;
    }


    /**
    *@Date 2018/8/30 16:57
    *@Description 根据书名搜索 - 分页
    **/
    @Override
    public PageQueryBean searchBook(SearchQueryCondition condition) {

        int count = bookMapper.countBySearchCondition(condition);

        PageQueryBean pageResult = new PageQueryBean();
        if (count > 0){
            pageResult.setTotalRows(count);
            pageResult.setCurrentPage(condition.getCurrentPage());
            pageResult.setPageSize(condition.getPageSize());
            List<Book> books = bookMapper.selectSearchBookPage(condition);
            for(Book book : books){
                Record record = bookMapper.selectBookBorrowRecord(book);
                book.setRecord(record);
            }
            pageResult.setItems(books);
        }

        return pageResult;
    }

    /**
    *@Date 2018/8/31 11:00
    *@Description 添加书籍
    **/
    @Override
    public Map<String, Object> addBook(MultipartFile file, HttpServletRequest request, Book book) {
        Map<String,Object> result = new HashMap<>();
        try {
            if (!file.isEmpty() && book != null) {
//            书籍信息入库 插入返回id
                book.setState(true);
                book.setCreatetime(new Date());
                book.setUpdatetime(new Date());
                bookMapper.insertSelective(book);

                String path = request.getServletContext().getRealPath("\\static\\images\\");
                String name = book.getId() + ".jpg";
                file.transferTo(new File(path , name));
                result.put("result", "true");
                result.put("error", "");
                result.put("info", "添加成功");
            } else {
                result.put("result", "false");
                result.put("error", 0);
                result.put("info", "上传失败");
            }
        } catch (Exception e){
            result.put("result", "false");
            result.put("error", 0);
            result.put("info", "添加失败");
        }
        return result;
    }

    /**
    *@Date 2018/8/31 15:03
    *@Description 删除书籍
    **/
    @Override
    public Map<String, Object> deleteBook(int book_id) {
        Map<String, Object> result = new HashMap<>();

        Book book = bookMapper.selectByPrimaryKeyState(book_id);
        if (book == null) {
            result.put("result", "false");
            result.put("error", 0);
            result.put("info", "书籍不存在");
            return result;
        }

        try {
            book.setState(false);
            book.setUpdatetime(new Date());
            bookMapper.updateByPrimaryKeySelective(book);
            result.put("result", "true");
            result.put("error", "");
            result.put("info", "删除书籍成功");
        } catch (Exception e) {
            result.put("result", "false");
            result.put("error", 0);
            result.put("info", "删除失败");
            return result;
        }

        return result;
    }

    /**
    *@Date 2018/8/31 15:06
    *@Description 更新书籍信息
    **/
    @Override
    public Map<String, Object> updateBook(MultipartFile file, Book book, HttpServletRequest request) {
        Map<String,Object > result = new HashMap<>();

        if (book.getId() == null){
            result.put("result", "false");
            result.put("error", 0);
            result.put("info", "Id为空");
            return result;
        }

        Book tbook = bookMapper.selectByPrimaryKey(book.getId());
        if (tbook == null ||tbook.getState() == false){
            result.put("result", "false");
            result.put("error", 1);
            result.put("info", "书籍不存在");
            return result;
        }

        try{
            if (file != null) {
                if (!file.isEmpty()) {
                    String path = request.getServletContext().getRealPath("\\static\\images\\");
                    String name = book.getId() + ".jpg";

                    file.transferTo(new File(path, name));
                }
            }

            book.setUpdatetime(new Date());
            bookMapper.updateByPrimaryKeySelective(book);
            result.put("result", "true");
            result.put("error", "");
            result.put("info", "更新成功");
        }catch (Exception e){
            result.put("result", "false");
            result.put("error", 2);
            result.put("info", "更新失败");
            return result;
        }

        return result;
    }
}
