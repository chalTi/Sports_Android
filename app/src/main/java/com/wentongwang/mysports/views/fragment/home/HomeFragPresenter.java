package com.wentongwang.mysports.views.fragment.home;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.wentongwang.mysports.http.RetrofitManager;
import com.wentongwang.mysports.http.RetrofitResult;
import com.wentongwang.mysports.http.SportService;
import com.wentongwang.mysports.model.bussiness.RxVolleyRequest;
import com.wentongwang.mysports.model.bussiness.VollyRequestManager;
import com.wentongwang.mysports.model.bussiness.VolleyResponse;
import com.wentongwang.mysports.model.module.SportsFirstClass;
import com.wentongwang.mysports.model.module.SportsSecondClass;
import com.wentongwang.mysports.utils.Logger;
import com.wentongwang.mysports.utils.ToastUtil;
import com.wentongwang.mysports.utils.VolleyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
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
    }

    public void getSportEvents() {
        SportService sportService = RetrofitManager.getRetrofit().create(SportService.class);

        Observable<RetrofitResult> observable = sportService.getHomePageSportEvents("001");
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.immediate())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RetrofitResult>() {
                    @Override
                    public void onCompleted() {
                        mView.hideProgressBar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgressBar();
                        Logger.e(e.toString());
                        ToastUtil.show(mContext, e.toString(), 1500);
                    }

                    @Override
                    public void onNext(RetrofitResult result) {
                        List<SportsFirstClass> list = result.getObjectList(SportsFirstClass.class);
                        mView.setHomeEventList(list);
                        for (int i = 0; i < list.size(); i++) {
                            List<SportsSecondClass> listSecond = list.get(i).getSports();
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
