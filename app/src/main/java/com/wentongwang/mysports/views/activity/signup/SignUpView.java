package com.wentongwang.mysports.views.activity.signup;

import android.view.View;
import android.widget.PopupWindow;

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

    void showPopupWindow(PopupWindow popupWindow);

    void setBackGroundAlpha(float alpha);

    View initPopupView();
}
