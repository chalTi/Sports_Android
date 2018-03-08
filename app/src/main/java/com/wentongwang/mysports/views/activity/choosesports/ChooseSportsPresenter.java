package com.wentongwang.mysports.views.activity.choosesports;

import android.content.Context;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.base.BasePresenter;
import com.wangwentong.sports_api.model.SportEvents;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wentong WANG on 2016/10/14.
 */
public class ChooseSportsPresenter extends BasePresenter<ChooseSportsView> implements PresenterHandler {

    //all events
    private List<SportEvents> sportEvents;

    //amount of item in one page
    private int pageItemCount = 9;
    //grideview's page size
    private int gvPageSize = 1;
    private int gvColumns = 3;
    private int totalEvents = 0;

    //events are chosen
    private List<SportEvents> sportEventsChosen;

    public void init(Context context) {
        super.init(context);
        sportEvents = new ArrayList<>();
        sportEventsChosen = new ArrayList<>();
    }

    public void initSportEvents() {
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
        //初始化爱好ViewPager及其下方的圆点
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
        sportEventsChosen.add(item);
        view.refreshEventChoose(sportEventsChosen);

    }

    @Override
    public void removeChooseEvent(SportEvents item) {
        sportEventsChosen.remove(item);
        view.refreshEventChoose(sportEventsChosen);

    }
}
