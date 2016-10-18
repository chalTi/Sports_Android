package com.wentongwang.mysports.views.activity.choosesports;

import com.wentongwang.mysports.model.module.SportEvents;
import com.wentongwang.mysports.views.BaseView;

import java.util.List;

/**
 * Created by Wentong WANG on 2016/10/14.
 */
public interface ChooseSportsView extends BaseView {

    void initGirdViewsAndPoints();

    void refreshEventChoose(List<SportEvents> list);
}
