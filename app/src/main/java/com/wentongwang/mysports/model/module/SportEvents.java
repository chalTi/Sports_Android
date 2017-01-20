package com.wentongwang.mysports.model.module;

/**
 * Created by Wentong WANG on 2016/9/20.
 */
public class SportEvents {
    //resource id of image
    private int event_image;
    //this item is selected
    private boolean isSelected = false;
    //sport's name
    private String event_name;
    private String event_code;

    public SportEvents() {

    }

    public SportEvents(int image, String name) {
        this.event_image = image;
        this.event_name = name;
    }

    public SportEvents(int image, String name, String event_code) {
        this.event_image = image;
        this.event_name = name;
        this.event_code = event_code;
    }

    public int getEvent_image() {
        return event_image;
    }

    public void setEvent_image(int event_image) {
        this.event_image = event_image;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_code() {
        return event_code;
    }

    public void setEvent_code(String event_code) {
        this.event_code = event_code;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SportEvents) {
            String code = ((SportEvents) o).getEvent_code();
            return event_code.equals(code);
        } else {
            return false;
        }
    }
}

