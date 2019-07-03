package com.hiddenbean.android.khbarmdinty.resources;

import com.hiddenbean.android.khbarmdinty.models.User;

import java.util.Map;

public class UserResource {

    private User data;
    private Map meta;

    public UserResource() {

    }

    public UserResource(User data, Map meta) {
        this.data = data;
        this.meta = meta;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public Map getMeta() {
        return meta;
    }

    public void setMeta(Map meta) {
        this.meta = meta;
    }
}
