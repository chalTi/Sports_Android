package com.wangwentong.sports_api.model;

import java.io.Serializable;
import java.util.List;


/**
 * 菜单一级选项信息封装
 * Created by Wentong WANG on 2016/11/3.
 */
public class SportsFirstClass implements Serializable {

    private String type;
    private List<SportsSecondClass> sports;
    private String imageUrl;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SportsSecondClass> getSports() {
        return sports;
    }

    public void setSports(List<SportsSecondClass> sports) {
        this.sports = sports;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getSportEventsNumber() {
        return sports != null ? sports.size() : 0;
    }
}
