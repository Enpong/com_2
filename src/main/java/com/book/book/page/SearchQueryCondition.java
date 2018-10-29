package com.book.book.page;

import com.book.common.page.PageQueryBean;

public class SearchQueryCondition extends PageQueryBean {

    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
