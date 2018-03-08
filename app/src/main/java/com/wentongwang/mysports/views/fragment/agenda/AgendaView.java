package com.wentongwang.mysports.views.fragment.agenda;

import android.widget.PopupWindow;

import com.wangwentong.sports_api.model.AgendaEvents;
import com.wentongwang.mysports.base.BaseView;

import java.util.List;

/**
 * Created by Wentong WANG on 2016/9/26.
 */
public interface AgendaView extends BaseView {

    void showPersonInfoPopupWindow(PopupWindow popupWindow);

    void setBackGroundAlpha(float alpha);

    void setAgendaList(List<AgendaEvents> list);
}
