package com.wentongwang.mysports.views.activity.choosesports;

import android.content.Context;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.model.bussiness.VollyRequestManager;
import com.wentongwang.mysports.model.module.SportEvents;
import com.wentongwang.mysports.utils.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wentong WANG on 2016/10/14.
 */
public class ChooseSportsPresenter implements PresenterHandler {

    private ChooseSportsView mView;
    private Context mContext;
    private VollyRequestManager vollyRequestManager;
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


    public ChooseSportsPresenter(ChooseSportsView mView) {
        this.mView = mView;
    }

    public void init(Context context) {
        this.mContext = context;
        vollyRequestManager = new VollyRequestManager(VolleyUtil.getInstance(mContext).getRequestQueue());
        sportEvents = new ArrayList<>();
        sportEventsChosen = new ArrayList<>();
    }

    public void initSportEvents() {
        //Event从服务器获取比较好一点
        sportEvents.add(new SportEvents(R.drawable.basketball, "basketball"));
        sportEvents.add(new SportEvents(R.drawable.soccerball, "soccerball"));
        sportEvents.add(new SportEvents(R.drawable.football, "football"));
        sportEvents.add(new SportEvents(R.drawable.volleyball, "volleyball"));
        sportEvents.add(new SportEvents(R.drawable.badminton, "badminton"));
        sportEvents.add(new SportEvents(R.drawable.pingpang, "pingpang"));
        sportEvents.add(new SportEvents(R.drawable.tennis, "tennis"));
        sportEvents.add(new SportEvents(R.drawable.bicycle, "bicycle"));
        sportEvents.add(new SportEvents(R.drawable.running, "running"));
        sportEvents.add(new SportEvents(R.drawable.swimming, "swimming"));
        sportEvents.add(new SportEvents(R.drawable.exercise, "exercise"));
        sportEvents.add(new SportEvents(R.drawable.boxing, "boxing"));

        totalEvents = sportEvents.size();
        //初始化爱好ViewPager及其下方的圆点
        mView.initGirdViewsAndPoints();
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
        mView.refreshEventChoose(sportEventsChosen);

    }

    @Override
    public void removeChooseEvent(SportEvents item) {
        sportEventsChosen.remove(item);
        mView.refreshEventChoose(sportEventsChosen);

    }
}
