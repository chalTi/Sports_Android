package com.wangwentong.sports_api.model;

/**
 * Created by Wentong WANG on 2016/10/13.
 */
public class UserInfo {

    private String user_id;
    private String user_name;
    private String user_head_url;
    private String user_description;
    private String user_like;

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getUserHeadUrl() {
        return user_head_url;
    }

    public void setUserHeadUrl(String user_head_url) {
        this.user_head_url = user_head_url;
    }

    public String getUserDescription() {
        return user_description;
    }

    public void setUserDescription(String user_description) {
        this.user_description = user_description;
    }

    public String getUserLike() {
        return user_like;
    }

    public void setUserLike(String user_like) {
        this.user_like = user_like;
    }

}
