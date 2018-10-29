package com.book.record.page;

import com.book.common.page.PageQueryBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RecordQueryCondition extends PageQueryBean {

    private String userId;

    private Integer bookId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate ;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
