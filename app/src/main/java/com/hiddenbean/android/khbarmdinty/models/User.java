package com.hiddenbean.android.khbarmdinty.models;

import com.orm.SugarRecord;

import java.util.Date;

public class User extends SugarRecord {

    private String first_name;
    private String last_name;
    private String email;
    private Date email_verified_at;
    private String api_token;
    private Date created_at;
    private Date updated_at;

    public User() {

    }

    public User(String first_name, String last_name, String email, Date email_verified_at, String api_token, Date created_at, Date updated_at) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.email_verified_at = email_verified_at;
        this.api_token = api_token;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(Date email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
