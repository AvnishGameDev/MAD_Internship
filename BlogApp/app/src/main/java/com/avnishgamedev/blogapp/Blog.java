package com.avnishgamedev.blogapp;

import java.io.Serializable;
import java.util.Date;

public class Blog implements Serializable {
    private String title;
    private String content;
    private String author;
    private Date time;
    private String imageUrl;

    public Blog() {}

    public Blog(String title, String content, String author, Date time, String imageUrl) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.time = time;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public Date getTime() {
        return time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
