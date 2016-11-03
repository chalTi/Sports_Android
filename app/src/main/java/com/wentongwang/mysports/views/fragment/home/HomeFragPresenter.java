package com.wentongwang.mysports.views.fragment.home;

import android.content.Context;

import com.wentongwang.mysports.model.bussiness.VollyRequestManager;
import com.wentongwang.mysports.model.bussiness.VollyResponse;
import com.wentongwang.mysports.model.module.SportEvents;
import com.wentongwang.mysports.model.module.SportsFirstClass;
import com.wentongwang.mysports.model.module.SportsSecondClass;
import com.wentongwang.mysports.utils.VolleyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wentong WANG on 2016/10/13.
 */
public class HomeFragPresenter {

    private HomeFragView mView;
    private Context mContext;
    private VollyRequestManager vollyRequestManager;
    private List<SportsFirstClass> sportsList;


    public HomeFragPresenter(HomeFragView view) {
        mView = view;
    }

    /**
     * initial presenter
     * get Activity context
     * initial VolleyRequestManager
     *
     * @param context
     */
    public void init(Context context) {
        this.mContext = context;
        vollyRequestManager = new VollyRequestManager(VolleyUtil.getInstance(mContext).getRequestQueue());
    }

    /**
     * 纯粹为了测试，莫管
     */
    public void getSportEventsTest() {
        sportsList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SportsFirstClass item = new SportsFirstClass();
            item.setType("type" + i);
            List<SportsSecondClass> list1 = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                SportsSecondClass item1 = new SportsSecondClass();
                item1.setEvent_creator_name("user" + i);
                item1.setEvent_start_time("12:30");
                item1.setEvent_end_time("15:30");
                item1.setEvent_place("Troyes");
                list1.add(item1);
            }
            item.setSports(list1);
            sportsList.add(item);
        }
        mView.refreshList(sportsList);
    }

    public void getSportEvents() {
        //这个可能不需要分页
        Map<String, String> params = new HashMap<>();
        params.put("user_id", "123456");
        params.put("user_like", "sssss");

        String url = "";
        VollyResponse<SportEvents> response = new VollyResponse<>();
        vollyRequestManager.doPost(mContext, url, response, params, new VollyRequestManager.OnRequestFinishedListener() {
            @Override
            public void onSucess(VollyResponse response) {

            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    public void goToEventDetail(int groupPosition, int childPosition){
        SportsSecondClass item = sportsList.get(groupPosition).getSports().get(childPosition);
        mView.goToEventDetail(item);
    }
}
