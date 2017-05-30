package com.wentongwang.mysports.views.fragment.home;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.wentongwang.mysports.model.bussiness.RxVolleyRequest;
import com.wentongwang.mysports.model.bussiness.VollyRequestManager;
import com.wentongwang.mysports.model.bussiness.VolleyResponse;
import com.wentongwang.mysports.model.module.SportsFirstClass;
import com.wentongwang.mysports.model.module.SportsSecondClass;
import com.wentongwang.mysports.utils.ToastUtil;
import com.wentongwang.mysports.utils.VolleyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
//        vollyRequestManager = new VollyRequestManager(VolleyUtil.getInstance(mContext).getRequestQueue());
    }


    /* public void getSportEvents() {
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
     }*/
    public void getSportEvents() {
        //这个可能不需要分页
        String url = HOST + GET_EVENT_PATH + "001";
        mView.showProgressBar();
        RxVolleyRequest.getInstance().resourceRequestObservable(mContext, Request.Method.GET, url, null)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        mView.hideProgressBar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgressBar();
                        ToastUtil.show(mContext, e.toString(), 1500);
                    }

                    @Override
                    public void onNext(String response) {
                        VolleyResponse<SportsFirstClass> sportsFirstClassVolleyResponse = new VolleyResponse<>();
                        sportsFirstClassVolleyResponse.setMsg(response);
                        List<SportsFirstClass> list = sportsFirstClassVolleyResponse.getResultArray(SportsFirstClass.class);
                        mView.setHomeEventList(list);
                        for (int i = 0; i < list.size(); i++) {
                            List<SportsSecondClass> listSecond = list.get(i).getSports();
                            Log.i("listSecond.size()", listSecond.size() + "");
                            mView.SportsEventDetail(listSecond);
                        }
                    }
                });
    }

    public void goToEventDetail(int groupPosition, int childPosition) {
        SportsSecondClass item = sportsList.get(groupPosition).getSports().get(childPosition);
        mView.goToEventDetail(item);
    }
}
