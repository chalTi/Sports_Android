package com.wangwentong.sports_api.model;

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

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public String getUserLike() {
        return user_like;
    }

    public void setUserLike(String user_like) {
        this.user_like = user_like;
    }

    public String getUserAuthority() {
        return user_authority;
    }

    public void setUserAuthority(String user_authority) {
        this.user_authority = user_authority;
    }

    public String getUserAge() {
        return user_age;
    }

    public void setUserAge(String user_age) {
        this.user_age = user_age;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getUserPassword() {
        return user_password;
    }

    public void setUserPassword(String user_password) {
        this.user_password = user_password;
    }

    public String getUserEmail() {
        return user_email;
    }

    public void setUserEmail(String user_email) {
        this.user_email = user_email;
    }

    public String getUserSex() {
        return user_sex;
    }

    public void setUserSex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUserImageURL() {
        return user_imageURL;
    }

    public void setUserImageURL(String user_imageURL) {
        this.user_imageURL = user_imageURL;
    }
}
