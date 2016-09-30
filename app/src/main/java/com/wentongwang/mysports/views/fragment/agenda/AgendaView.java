package com.wentongwang.mysports.views.fragment.agenda;

import android.widget.PopupWindow;

import com.wentongwang.mysports.views.BaseView;

/**
 * Created by Wentong WANG on 2016/9/26.
 */
public interface AgendaView extends BaseView {

    void showPersonInfoPopupWindow(PopupWindow popupWindow);

    void setBackGroundAlpha(float alpha);
}
