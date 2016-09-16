package com.wentongwang.mysports.views.activity.home;

import android.widget.RadioButton;

import com.wentongwang.mysports.views.BaseView;

/**
 * Created by Wentong WANG on 2016/9/16.
 */
public interface HomeView extends BaseView {

    void hiddenToolBarAnim(float positionOffset);

    RadioButton getCheckedBtn(int position);

    void goToCreatEventActivity();
}
