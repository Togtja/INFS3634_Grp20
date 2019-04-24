package com.example.grp20_app;

import android.graphics.Bitmap;

import android.util.Pair;


import java.io.Serializable;

import java.util.ArrayList;
/*
This stores all the information for a single wikipedia page

 */
public class WikiPage implements Serializable {
    private int pageId; //The ID for the page (Mostly useless)
    private String title; //Title of the wikipedia page
    private String text;    //The a extracted section of the page
    private String imgURL;  //The URL to the thumbnail
    private Bitmap image;   //A bitmap of the thumnail after we have downloaded it
    private ArrayList<Pair<String, String>> sections;

    public WikiPage(int pageId, String title, String text, String imageUrl){
        this.pageId = pageId;
        this.title = title;
        this.text = text;
        imgURL = imageUrl;
        image = null;
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

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
