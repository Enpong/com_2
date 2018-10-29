package com.book.collect.entity;

import com.book.book.entity.Book;

import java.util.Date;

public class Collect {
    private Integer id;

    private Integer userId;

    private Integer bookId;

    private Date collecttime;

    private Byte state;

    public Collect(){}

    public Collect(Integer userId, Integer bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Book getBookinfo() {
        return bookinfo;
    }

    public void setBookinfo(Book bookinfo) {
        this.bookinfo = bookinfo;
    }

    private Book bookinfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getCollecttime() {
        return collecttime;
    }

    public void setCollecttime(Date collecttime) {
        this.collecttime = collecttime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}