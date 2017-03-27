package com.wentongwang.mysports.views.activity.splash;

import android.content.Context;

import com.wentongwang.mysports.views.activity.signup.SignUpView;

/**
 * Created by Wentong WANG on 2017/3/27.
 */
public class SplashPresenter {

    private SplashView view;
    private Context mContext;

    public SplashPresenter(SplashView view){
        this.view = view;
    }

    public void init(Context context) {
        mContext = context;
    }

    public void autoLogin(){
        //TODO:现在直接跳转登录界面，我之后会做自动登录
        view.goToLogin();
    }
}
