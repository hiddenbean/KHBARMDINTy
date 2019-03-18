package com.hiddenbean.android.khbarmdinty.models;

import java.util.Date;

public class TextPost {

    private int id;
    private String post;
    private int user_id;
    private Date created_at;
    private Date updated_at;

    public TextPost() {

    }

    public TextPost(int id, String post, int user_id, Date created_at, Date updated_at) {
        this.id = id;
        this.post = post;
        this.user_id = user_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public String getPost() {
        return post;
    }

    public int getUser_id() {
        return user_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdate_at() {
        return updated_at;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUpdate_at(Date update_at) {
        this.updated_at = update_at;
    }
}
