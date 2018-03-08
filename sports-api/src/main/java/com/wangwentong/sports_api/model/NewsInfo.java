package com.wangwentong.sports_api.model;

import java.io.Serializable;

/**
 * Created by Wentong WANG on 2016/11/20.
 */
public class NewsInfo implements Serializable {

    private String news_title;
    private String news_content;
    private String news_creator_id;
    private String news_create_time;
    private String news_place;
    private String news_liked;
    private String news_likes_total;
    private String news_view;

    public String getNewsLikesTotal() {
        return news_likes_total;
    }

    public void setNewsLikesTotal(String news_likes_total) {
        this.news_likes_total = news_likes_total;
    }

    public String getNewsTitle() {
        return news_title;
    }

    public void setNewsTitle(String news_title) {
        this.news_title = news_title;
    }

    public String getNewsContent() {
        return news_content;
    }

    public void setNewsContent(String news_content) {
        this.news_content = news_content;
    }

    public String getNewsCreatorId() {
        return news_creator_id;
    }

    public void setNewsCreatorId(String news_creator_id) {
        this.news_creator_id = news_creator_id;
    }

    public String getNewsCreateTime() {
        return news_create_time;
    }

    public void setNewsCreateTime(String news_create_time) {
        this.news_create_time = news_create_time;
    }

    public String getNewsPlace() {
        return news_place;
    }

    public void setNewsPlace(String news_place) {
        this.news_place = news_place;
    }

    public String getNewsLiked() {
        return news_liked;
    }

    public void setNewsLiked(String news_liked) {
        this.news_liked = news_liked;
    }

    public String getNewsView() {
        return news_view;
    }

    public void setNewsView(String news_view) {
        this.news_view = news_view;
    }
}
