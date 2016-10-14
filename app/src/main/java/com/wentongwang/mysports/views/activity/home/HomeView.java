package com.wentongwang.mysports.views.activity.home;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RadioButton;

import com.wentongwang.mysports.views.BaseView;

/**
 * Created by Wentong WANG on 2016/9/16.
 */
public interface HomeView extends BaseView {

    void hiddenToolBarAnim();

    void showToolBarAnim();

    void setToolBarRightBtnVisible(boolean show);

    RadioButton getCheckedBtn(int position);

    void goToCreatEventActivity();

    void showPopupWindow(PopupWindow popupWindow);

    void setBackGroundAlpha(float alpha);

    Context getContext();

    View initPopupView();

    void goToChoseSports();
}
