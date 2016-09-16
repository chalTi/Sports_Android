package com.wentongwang.mysports.views.activity.login;

import com.wentongwang.mysports.views.BaseView;

/**
 * Created by Wentong WANG on 2016/8/18.
 */
public interface LoginView extends BaseView {

    void goToSignUp();

    String getUserName();

    String getUserPwd();

    void goToHomeActivity();
}
