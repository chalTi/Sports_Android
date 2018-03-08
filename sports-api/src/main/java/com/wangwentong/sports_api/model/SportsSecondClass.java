package com.wangwentong.sports_api.model;

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

    public String getEventCreatorName() {
        return event_creator_name;
    }

    public void setEventCreatorName(String event_creator_name) {
        this.event_creator_name = event_creator_name;
    }

    public String getEventCreatorHeadUrl() {
        return event_creator_head_url;
    }

    public void setEventCreatorHeadUrl(String event_creator_head_url) {
        this.event_creator_head_url = event_creator_head_url;
    }

    public String getEventStartTime() {
        return event_start_time;
    }

    public void setEventStartTime(String event_start_time) {
        this.event_start_time = event_start_time;
    }

    public String getEventCreatTime() {
        return event_creat_time;
    }

    public void setEventCreatTime(String event_creat_time) {
        this.event_creat_time = event_creat_time;
    }

    public String getEventEndTime() {
        return event_end_time;
    }

    public void setEventEndTime(String event_end_time) {
        this.event_end_time = event_end_time;
    }

    public String getEventPlace() {
        return event_place;
    }

    public void setEventPlace(String event_place) {
        this.event_place = event_place;
    }

    public int getEventNumber() {
        return Integer.valueOf(event_number);
    }

    public void setEventNumber(String event_number) {
        this.event_number = event_number;
    }

    public List<String> getEventParticipatesId() {
        return event_participates_id;
    }

    public void setEventParticipatesId(List<String> event_participates_id) {
        this.event_participates_id = event_participates_id;
    }

    public int getParticipatesNumber(){
        return event_participates_id != null ? event_participates_id.size() : 0;
    }
}
