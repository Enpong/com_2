package com.book.user.page;

import com.book.common.page.PageQueryBean;

public class UserQueryCondition extends PageQueryBean {

    private String username;

    private String department;

    private Boolean state;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
