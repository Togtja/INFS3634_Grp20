package com.example.grp20_app;

import java.io.Serializable;

public class WikiPage implements Serializable {
    int pageId;
    String title;
    String text;

    public WikiPage(int pageId, String title, String text){
        this.pageId = pageId;
        this.title = title;
        this.text = text;
    }

    public int getPageId() {
        return pageId;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
