package com.wangwentong.sports_api.model;

import java.io.Serializable;

/**
 * Created by qifan on 2016/11/29.
 */

public class AgendaEvents implements Serializable {
    private String user_id;
    private String user_name;
    private String user_image_id;
    private String user_events_name;
    private String user_events_time;
    private String user_events_place;
    private String events_progress;

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

    public String getUserImageId() {
        return user_image_id;
    }

    public void setUserImageId(String user_image_id) {
        this.user_image_id = user_image_id;
    }

    public String getUserEventsName() {
        return user_events_name;
    }

    public void setUserEventsName(String user_events_name) {
        this.user_events_name = user_events_name;
    }

    public String getUserEventsTime() {
        return user_events_time;
    }

    public void setUserEventsTime(String user_events_time) {
        this.user_events_time = user_events_time;
    }

    public String getUserEventsPlace() {
        return user_events_place;
    }

    public void setUserEventsPlace(String user_events_place) {
        this.user_events_place = user_events_place;
    }

    public String getEventsProgress() {
        return events_progress;
    }

    public void setEventsProgress(String events_Progress) {
        this.events_progress = events_Progress;
    }
}
