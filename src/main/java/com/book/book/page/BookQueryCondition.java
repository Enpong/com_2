package com.book.book.page;

import com.book.common.page.PageQueryBean;

public class BookQueryCondition extends PageQueryBean {

    private String location;

    private String type;

    private String code;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
