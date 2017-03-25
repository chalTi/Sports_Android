package com.wentongwang.mysports.views.fragment.home;

import android.content.Context;
import android.util.Log;

import com.wentongwang.mysports.model.bussiness.VollyRequestManager;
import com.wentongwang.mysports.model.bussiness.VolleyResponse;
import com.wentongwang.mysports.model.module.SportsFirstClass;
import com.wentongwang.mysports.model.module.SportsSecondClass;
import com.wentongwang.mysports.utils.ToastUtil;
import com.wentongwang.mysports.utils.VolleyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wentongwang.mysports.constant.Constant.GET_EVENT_PATH;
import static com.wentongwang.mysports.constant.Constant.HOST;

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

        String url = HOST+GET_EVENT_PATH;
        VolleyResponse<SportsFirstClass> response = new VolleyResponse<>();
        vollyRequestManager.doPost(mContext, url, response, params, new VollyRequestManager.OnRequestFinishedListener() {
            @Override
            public void onSuccess(VolleyResponse response) {
                Log.i("Home", response.getMsg());
                mView.hideProgressBar();
                List<SportsFirstClass> list = response.getResultArray(SportsFirstClass.class);
                mView.setHomeEventList(list);
                for (int i = 0; i < list.size(); i++) {
                    List<SportsSecondClass> listSecond = list.get(i).getSports();
                    Log.i("listSecond.size()",listSecond.size()+"");
                    mView.SportsEventDetail(listSecond);
                }
            }

            @Override
            public void onFailed(String msg) {
                mView.hideProgressBar();
                ToastUtil.show(mContext, msg, 1500);
            }
        });
    }

    public void goToEventDetail(int groupPosition, int childPosition) {
        SportsSecondClass item = sportsList.get(groupPosition).getSports().get(childPosition);
        mView.goToEventDetail(item);
    }
}
