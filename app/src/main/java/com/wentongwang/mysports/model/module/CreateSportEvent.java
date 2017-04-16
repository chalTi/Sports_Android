package com.wentongwang.mysports.model.module;

/**
 * Created by Wentong WANG on 2017/4/10.
 */

public class CreateSportEvent {

    private int user_basic_id;
    private String event_sport;
    private String event_location;
    private int event_participer_number;
    private String event_description;
    private String event_sport_type;
    private String event_start_time;

    public int getUserBasicId() {
        return user_basic_id;
    }

    public void setUserBasicId(int user_basic_id) {
        this.user_basic_id = user_basic_id;
    }

    public String getEventSport() {
        return event_sport;
    }

    public void setEventSport(String event_sport) {
        this.event_sport = event_sport;
    }

    public String getEventLocation() {
        return event_location;
    }

    public void setEventLocation(String event_location) {
        this.event_location = event_location;
    }

    public int getEventParticiperNumber() {
        return event_participer_number;
    }

    public void setEventParticiperNumber(int event_participer_number) {
        this.event_participer_number = event_participer_number;
    }

    public String getEventDescription() {
        return event_description;
    }

    public void setEventDescription(String event_description) {
        this.event_description = event_description;
    }

    public String getEventSportType() {
        return event_sport_type;
    }

    public void setEventSportType(String event_sport_type) {
        this.event_sport_type = event_sport_type;
    }

    public String getEventStartTime() {
        return event_start_time;
    }

    public void setEventStartTime(String event_start_time) {
        this.event_start_time = event_start_time;
    }
}
