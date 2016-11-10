package com.wentongwang.mysports.model.module;

import java.io.Serializable;

/**
 * Created by Wentong WANG on 2016/11/3.
 */
public class LoginResponse implements Serializable {
    private String user_id;
    private String user_like;
    private String user_authority;
    private String user_age;
    private String user_name;
    private String user_password;
    private String user_email;
    private String user_sex;
    private String user_imageURL;

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

    public String getUser_authority() {
        return user_authority;
    }

    public void setUser_authority(String user_authority) {
        this.user_authority = user_authority;
    }

    public String getUser_age() {
        return user_age;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_imageURL() {
        return user_imageURL;
    }

    public void setUser_imageURL(String user_imageURL) {
        this.user_imageURL = user_imageURL;
    }
}
