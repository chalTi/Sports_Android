package com.wentongwang.mysports.views.fragment.home;

import com.wentongwang.mysports.model.module.SportsFirstClass;
import com.wentongwang.mysports.model.module.SportsSecondClass;
import com.wentongwang.mysports.base.BaseView;

import java.util.List;

/**
 * Created by Wentong WANG on 2016/9/17.
 */
public interface HomeFragView extends BaseView{

    void refreshList(List<SportsFirstClass> list);

    void goToEventDetail(SportsSecondClass info);

    void setHomeEventList(List<SportsFirstClass> list);

    void SportsEventDetail(List<SportsSecondClass> list);
}
