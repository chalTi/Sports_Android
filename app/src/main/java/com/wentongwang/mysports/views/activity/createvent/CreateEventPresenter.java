package com.wentongwang.mysports.views.activity.createvent;

import android.content.Context;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.base.BasePresenter;
import com.wentongwang.mysports.model.module.SportEvents;
import com.wentongwang.mysports.views.activity.choosesports.PresenterHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wentong WANG on 2016/9/15.
 */
public class CreateEventPresenter extends BasePresenter<CreateEventView> implements PresenterHandler {

    private List<SportEvents> sportEvents;

    private int totalEvents = 0;

    //event is chosen
    private SportEvents sportEventChosen;

    @Override
    public void init(Context context) {
        super.init(context);
        sportEvents = new ArrayList<>();
    }


    /**
     * 初始化运动events
     */
    public void initSportEvents() {
        sportEvents = new ArrayList<>();
        //Event从服务器获取比较好一点
        sportEvents.add(new SportEvents(R.drawable.basketball, "basketball", "001"));
        sportEvents.add(new SportEvents(R.drawable.soccerball, "soccerball", "002"));
        sportEvents.add(new SportEvents(R.drawable.football, "football", "003"));
        sportEvents.add(new SportEvents(R.drawable.volleyball, "volleyball", "004"));
        sportEvents.add(new SportEvents(R.drawable.badminton, "badminton", "005"));
        sportEvents.add(new SportEvents(R.drawable.pingpang, "pingpang", "006"));
        sportEvents.add(new SportEvents(R.drawable.tennis, "tennis", "007"));
        sportEvents.add(new SportEvents(R.drawable.bicycle, "bicycle", "008"));
        sportEvents.add(new SportEvents(R.drawable.running, "running", "009"));
        sportEvents.add(new SportEvents(R.drawable.swimming, "swimming", "010"));
        sportEvents.add(new SportEvents(R.drawable.exercise, "exercise", "011"));
        sportEvents.add(new SportEvents(R.drawable.boxing, "boxing", "012"));

        totalEvents = sportEvents.size();

        view.initGirdViewsAndPoints();
    }

    public int getTotalEvents() {
        return totalEvents;
    }

    public List<SportEvents> getSportEvents() {
        return sportEvents;
    }

    @Override
    public void addChooseEvent(SportEvents item) {
        sportEventChosen = item;
    }

    @Override
    public void removeChooseEvent(SportEvents item) {
        sportEventChosen = null;
    }
}
