package com.wentongwang.mysports.views.fragment.news;

import android.content.Context;
import android.text.TextUtils;

import com.wentongwang.mysports.constant.Constant;
import com.wentongwang.mysports.model.bussiness.VollyRequestManager;
import com.wentongwang.mysports.model.bussiness.VollyResponse;
import com.wentongwang.mysports.model.module.LoginResponse;
import com.wentongwang.mysports.model.module.NewsInfo;
import com.wentongwang.mysports.utils.Logger;
import com.wentongwang.mysports.utils.SharedPreferenceUtil;
import com.wentongwang.mysports.utils.ToastUtil;
import com.wentongwang.mysports.utils.VolleyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Wentong WANG on 2016/10/13.
 */
public class NewsPresenter {
    private NewsView mView;
    private Context mContext;
    private VollyRequestManager vollyRequestManager;

    public NewsPresenter(NewsView view) {
        this.mView = view;
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


    public void getNews(){

        String url = "http://192.168.1.25:8080/sports" + Constant.GET_NEWS_PATH;

        VollyResponse<NewsInfo> newsResponse = new VollyResponse<>();

        Map<String, String> params = new HashMap<>();
        params.put("user_id", "");
        params.put("user_like", "");
        params.put("page_size", "15");
        params.put("current_page", "5");

        mView.showProgressBar();
        vollyRequestManager.doPost(mContext, url, newsResponse, params, new VollyRequestManager.OnRequestFinishedListener() {
            @Override
            public void onSucess(VollyResponse response) {
                //Logger.i("News", response.getMsg());
                mView.hideProgressBar();
                List<NewsInfo> list = response.getResultArray(NewsInfo.class);
                mView.setNewsList(list);
            }

            @Override
            public void onFailed(String msg) {
                mView.hideProgressBar();
                ToastUtil.show(mContext, msg, 1500);
            }
        });
    }
}
