package com.wentongwang.mysports.views.activity.createvent;

import com.wentongwang.mysports.base.BaseView;

/**
 * Created by Wentong WANG on 2016/9/9.
 */
public interface CreateEventView extends BaseView {

    void initGirdViewsAndPoints();

    String getLocalisation();

    String getEventName();

    String getStartTime();

    int getParticipatorNumber();

    String getDescription();

    void createFinished();
}
