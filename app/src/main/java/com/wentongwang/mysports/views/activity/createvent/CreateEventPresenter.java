package com.wentongwang.mysports.views.activity.createvent;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.wentongwang.mysports.R;
import com.wentongwang.mysports.base.BasePresenter;
import com.wentongwang.mysports.constant.Constant;
import com.wentongwang.mysports.model.bussiness.RxVolleyRequest;
import com.wentongwang.mysports.model.module.CreateSportEvent;
import com.wentongwang.mysports.model.module.SportEvents;
import com.wentongwang.mysports.utils.ToastUtil;
import com.wentongwang.mysports.views.activity.choosesports.PresenterHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        Log.d("xxxx",sportEventChosen.toString());
    }

    @Override
    public void removeChooseEvent(SportEvents item) {
        sportEventChosen = null;
    }

    public void createSportEvent(){
        //TODO:这里请求服务器
        CreateSportEvent createSportEvent = new CreateSportEvent();

        if (view.getEventName() != null) {
            createSportEvent.setEventSport(view.getEventName());
        }else {
            return;
        }
        if (view.getStartTime() != null) {
            createSportEvent.setEventStartTime(view.getStartTime());
        }else {
            return;
        }
        if (view.getLocalisation() != null) {
            createSportEvent.setEventLocation(view.getLocalisation());
        }else {
            return;
        }

        if (sportEventChosen != null) {
            createSportEvent.setEventSportType(sportEventChosen.getEventCode());
        }else {
            return;
        }
        createSportEvent.setEventParticiperNumber(view.getParticipatorNumber());
        createSportEvent.setEventDescription(view.getDescription());

        String eventJson = new Gson().toJson(createSportEvent);
        Map<String, String> params = new HashMap<>();
        params.put("user_id", "1");
        params.put("eventJson", eventJson);

//        String url = Constant.HOST + Constant.CREATE_EVENT_PATH;
        String url = "http://192.168.1.17:8080/sports/" + Constant.CREATE_EVENT_PATH;
        view.showProgressBar();
        RxVolleyRequest.getInstance().operationRequestObservable(mContext, Request.Method.POST, url, params)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgressBar();
                        view.createFinished();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressBar();
                    }

                    @Override
                    public void onNext(String volleyResponse) {

                    }
                });

    }
}
