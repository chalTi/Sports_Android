package com.wentongwang.mysports.model.module;

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

    public String getNews_likes_total() {
        return news_likes_total;
    }

    public void setNews_likes_total(String news_likes_total) {
        this.news_likes_total = news_likes_total;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_content() {
        return news_content;
    }

    public void setNews_content(String news_content) {
        this.news_content = news_content;
    }

    public String getNews_creator_id() {
        return news_creator_id;
    }

    public void setNews_creator_id(String news_creator_id) {
        this.news_creator_id = news_creator_id;
    }

    public String getNews_create_time() {
        return news_create_time;
    }

    public void setNews_create_time(String news_create_time) {
        this.news_create_time = news_create_time;
    }

    public String getNews_place() {
        return news_place;
    }

    public void setNews_place(String news_place) {
        this.news_place = news_place;
    }

    public String getNews_liked() {
        return news_liked;
    }

    public void setNews_liked(String news_liked) {
        this.news_liked = news_liked;
    }

    public String getNews_view() {
        return news_view;
    }

    public void setNews_view(String news_view) {
        this.news_view = news_view;
    }
}
