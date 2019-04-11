package com.hiddenbean.android.khbarmdinty.models;

import java.util.Date;

public class Token {

    private String token;
    private Date created_at;

    public Token() {

    }

    public Token(String token, Date created_at) {
        this.token = token;
        this.created_at = created_at;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
