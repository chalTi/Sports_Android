package com.wentongwang.mysports.model.module;

import java.io.Serializable;

/**
 * Created by Wentong WANG on 2016/11/3.
 */
public class LoginResponse implements Serializable {
    private String user_id;
    private String user_like;
    private String user_proprity;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_like() {
        return user_like;
    }

    public void setUser_like(String user_like) {
        this.user_like = user_like;
    }

    public String getUser_proprity() {
        return user_proprity;
    }

    public void setUser_proprity(String user_proprity) {
        this.user_proprity = user_proprity;
    }
}
