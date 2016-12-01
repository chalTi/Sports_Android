package com.wentongwang.mysports.model.module;

import java.io.Serializable;

/**
 * Created by qifan on 2016/11/29.
 */

public class AgendaEvents implements Serializable {
    private String User_id;
    private String User_name;
    private String User_Image_id;
    private String User_events_name;
    private String User_events_time;
    private String User_events_place;
    private String Events_Progress;

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getUser_Image_id() {
        return User_Image_id;
    }

    public void setUser_Image_id(String user_Image_id) {
        User_Image_id = user_Image_id;
    }

    public String getUser_events_name() {
        return User_events_name;
    }

    public void setUser_events_name(String user_events_name) {
        User_events_name = user_events_name;
    }

    public String getUser_events_time() {
        return User_events_time;
    }

    public void setUser_events_time(String user_events_time) {
        User_events_time = user_events_time;
    }

    public String getUser_events_place() {
        return User_events_place;
    }

    public void setUser_events_place(String user_events_place) {
        User_events_place = user_events_place;
    }

    public String getEvents_Progress() {
        return Events_Progress;
    }

    public void setEvents_Progress(String events_Progress) {
        Events_Progress = events_Progress;
    }
}
