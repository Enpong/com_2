package com.book.record.entity;

import com.book.book.entity.Book;
import com.book.user.entity.User;

import java.util.Date;

public class Record {
    private Integer id;

    private String userId;

    private Integer bookId;

    private Date borrowtime;

    private Date updatetime;

    private Integer state;

    private Book bookinfo;

    private User user;

    public Book getBookinfo() {
        return bookinfo;
    }

    public void setBookinfo(Book bookinfo) {
        this.bookinfo = bookinfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public Record(){}

    public Record(String userId, Integer bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Record(String userId, Integer bookId, Date borrowtime, Date updatetime, Integer state) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowtime = borrowtime;
        this.updatetime = updatetime;
        this.state = state;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(Date borrowtime) {
        this.borrowtime = borrowtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}