package com.wentongwang.mysports.views.fragment.home;

import android.content.Context;

import com.wangwentong.sports_api.interactor.InteractorCallback;
import com.wangwentong.sports_api.interactor.SportsInteractor;
import com.wangwentong.sports_api.model.SportsFirstClass;
import com.wangwentong.sports_api.model.SportsSecondClass;
import com.wentongwang.mysports.utils.Logger;
import com.wentongwang.mysports.utils.ToastUtil;

import java.util.List;

/**
 * Created by Wentong WANG on 2016/10/13.
 */
public class HomeFragPresenter {

    private HomeFragView mView;
    private Context mContext;
    private List<SportsFirstClass> sportsList;
    private SportsInteractor sportsInteractor;


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
        this.sportsInteractor = new SportsInteractor();
    }

    public void getSportEvents() {
        sportsInteractor.getSportEvents("001", new InteractorCallback<List<SportsFirstClass>>() {
            @Override
            public void onSuccess(List<SportsFirstClass> result) {
                mView.hideProgressBar();
                mView.setHomeEventList(result);
                for (int i = 0; i < result.size(); i++) {
                    List<SportsSecondClass> listSecond = result.get(i).getSports();
                    mView.SportsEventDetail(listSecond);
                }
            }

            @Override
            public void onFailed(String error) {
                mView.hideProgressBar();
                Logger.e(error);
                ToastUtil.show(mContext, error, 1500);
            }
        });

    }

    public void goToEventDetail(int groupPosition, int childPosition) {
        SportsSecondClass item = sportsList.get(groupPosition).getSports().get(childPosition);
        mView.goToEventDetail(item);
    }
}
