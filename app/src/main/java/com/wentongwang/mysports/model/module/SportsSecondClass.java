package com.wentongwang.mysports.model.module;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Wentong WANG on 2016/11/3.
 */
public class SportsSecondClass implements Serializable {

    private String event_creator_name;
    private String event_creator_head_url;
    private String event_start_time;
    private String event_creat_time;
    private String event_end_time;
    private String event_place;
    private String event_number;
    private List<String> event_participates_id;

    public String getEvent_creator_name() {
        return event_creator_name;
    }

    public void setEvent_creator_name(String event_creator_name) {
        this.event_creator_name = event_creator_name;
    }

    public String getEvent_creator_head_url() {
        return event_creator_head_url;
    }

    public void setEvent_creator_head_url(String event_creator_head_url) {
        this.event_creator_head_url = event_creator_head_url;
    }

    public String getEvent_start_time() {
        return event_start_time;
    }

    public void setEvent_start_time(String event_start_time) {
        this.event_start_time = event_start_time;
    }

    public String getEvent_creat_time() {
        return event_creat_time;
    }

    public void setEvent_creat_time(String event_creat_time) {
        this.event_creat_time = event_creat_time;
    }

    public String getEvent_end_time() {
        return event_end_time;
    }

    public void setEvent_end_time(String event_end_time) {
        this.event_end_time = event_end_time;
    }

    public String getEvent_place() {
        return event_place;
    }

    public void setEvent_place(String event_place) {
        this.event_place = event_place;
    }

    public String getEvent_number() {
        return event_number;
    }

    public void setEvent_number(String event_number) {
        this.event_number = event_number;
    }

    public List<String> getEvent_participates_id() {
        return event_participates_id;
    }

    public void setEvent_participates_id(List<String> event_participates_id) {
        this.event_participates_id = event_participates_id;
    }
}
