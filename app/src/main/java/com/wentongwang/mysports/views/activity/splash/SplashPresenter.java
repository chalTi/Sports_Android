package com.wentongwang.mysports.views.activity.splash;

import com.android.volley.Request;
import com.wentongwang.mysports.base.BasePresenter;
import com.wentongwang.mysports.constant.Constant;
import com.wentongwang.mysports.model.bussiness.RxVolleyRequest;
import com.wentongwang.mysports.model.bussiness.VolleyResponse;
import com.wentongwang.mysports.model.module.LoginResponse;
import com.wentongwang.mysports.utils.SharedPreferenceUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Wentong WANG on 2017/3/27.
 */
public class SplashPresenter extends BasePresenter<SplashView>{

    public void autoLogin() {
        //TODO:现在直接跳转登录界面，我之后会做自动登录

        String userName = (String) SharedPreferenceUtil.get(mContext, Constant.USER_NAME, "");
        String userPwd = (String) SharedPreferenceUtil.get(mContext, Constant.USER_PASSWORD, "");
        if (userName != null && !userName.isEmpty() && userPwd != null && !userPwd.isEmpty()){
            login(userName, userPwd);
        }else {
            view.goToLogin();
        }

    }

    private void login(String userName, String userPwd) {
        String url = Constant.HOST + Constant.LOGIN_PATH;

        Map<String, String> params = new HashMap<>();
        params.put("loginName", userName);
        params.put("password", userPwd);

        view.showProgressBar();

        RxVolleyRequest.getInstance().getRequestObservable(mContext, Request.Method.POST, url, params)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgressBar();
                        view.goToHomeActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressBar();
                        view.goToLogin();
                    }

                    @Override
                    public void onNext(String volleyResponse) {
                        VolleyResponse<LoginResponse> loginResponse = new VolleyResponse<LoginResponse>();
                        loginResponse.setMsg(volleyResponse);
                    }
                });
    }
}
