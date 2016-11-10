package com.wentongwang.mysports.views.activity.signup;

import com.wentongwang.mysports.views.BaseView;

/**
 * Created by Wentong WANG on 2016/9/8.
 */
public interface SignUpView extends BaseView {

    String getUserName();
    String getUserPwd();
    String getUserPwd2();
    String getUserEmail();
    String getUserSex();
    void goToHomeActivity();

}
