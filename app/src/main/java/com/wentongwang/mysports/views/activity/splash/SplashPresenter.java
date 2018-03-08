package com.wentongwang.mysports.views.activity.splash;

import android.content.Context;

import com.wangwentong.sports_api.interactor.InteractorCallback;
import com.wangwentong.sports_api.interactor.UserInteractor;
import com.wangwentong.sports_api.model.LoginResponse;
import com.wentongwang.mysports.base.BasePresenter;
import com.wentongwang.mysports.constant.Constant;
import com.wentongwang.mysports.utils.SharedPreferenceUtil;

/**
 * Created by Wentong WANG on 2017/3/27.
 */
public class SplashPresenter extends BasePresenter<SplashView>{

    private UserInteractor userInteractor;


    /**
     * initial presenter
     * get Activity context
     * initial VolleyRequestManager
     *
     * @param context
     */
    @Override
    public void init(Context context) {
        super.init(context);
        userInteractor = new UserInteractor();
    }


    public void autoLogin() {
        String userName = (String) SharedPreferenceUtil.get(mContext, Constant.USER_NAME, "");
        String userPwd = (String) SharedPreferenceUtil.get(mContext, Constant.USER_PASSWORD, "");
        if (userName != null && !userName.isEmpty() && userPwd != null && !userPwd.isEmpty()){
            login(userName, userPwd);
        }else {
            view.goToLogin();
        }

    }

    private void login(String userName, String userPwd) {

        view.showProgressBar();
        userInteractor.login(userName, userPwd, new InteractorCallback<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse result) {
                view.hideProgressBar();
                view.goToHomeActivity();
            }

            @Override
            public void onFailed(String error) {
                view.hideProgressBar();
                view.goToLogin();
            }
        });


    }
}
