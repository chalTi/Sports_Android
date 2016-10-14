package com.wentongwang.mysports.views.fragment.home;

import android.content.Context;

import com.wentongwang.mysports.model.bussiness.VollyRequestManager;
import com.wentongwang.mysports.model.bussiness.VollyResponse;
import com.wentongwang.mysports.model.module.SportEvents;
import com.wentongwang.mysports.utils.VolleyUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wentong WANG on 2016/10/13.
 */
public class HomeFragPresenter {

    private HomeFragView mView;
    private Context mContext;
    private VollyRequestManager vollyRequestManager;


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
}
