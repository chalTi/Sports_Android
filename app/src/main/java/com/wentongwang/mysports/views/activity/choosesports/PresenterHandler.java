package com.wentongwang.mysports.views.activity.choosesports;

import com.wentongwang.mysports.model.module.SportEvents;

/**
 * Created by Wentong WANG on 2017/1/20.
 */
public interface PresenterHandler {

    void removeChooseEvent(SportEvents events);

    void addChooseEvent(SportEvents events);
}
