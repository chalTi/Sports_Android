package com.wentongwang.mysports.views.activity.home;


import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.utils.ActivityManager;
import com.wentongwang.mysports.utils.ToastUtil;

/**
 * Created by Wentong WANG on 2016/9/16.
 */
public class HomePresenter {

    private HomeView view;

    private int currentPage = 0;
    private boolean isToolBarVisible = true;
    private PopupWindow popupWindow;

    private long mExitTime;

    public HomePresenter(HomeView homeView) {
        this.view = homeView;
    }

    /**
     * hide or show toolbar depende on different page
     *
     * @param position       page position
     */
    public void toolBarAnim(int position) {
        if (position == 0) {
            view.setToolBarRightBtnVisible(true);
        } else {
            view.setToolBarRightBtnVisible(false);
        }

        if (position == 3 && isToolBarVisible) {
            view.hiddenToolBarAnim();
        } else if (position != 3 && !isToolBarVisible) {
            view.showToolBarAnim();
        }
    }

    /**
     * selon the position to set page in viewpager
     *
     * @param position
     */
    public void setPage(int position) {
        currentPage = position;
        view.getCheckedBtn(position).setChecked(true);

        //change some infos of the toolbar

    }

    public void toCreatEvent() {
        view.goToCreatEventActivity();

    }

    public void popupChoseWindow(){
        //获取数据，通过构造函数塞到popupWindow里
        popupWindow = new PopupWindow(view.initPopupView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view.setBackGroundAlpha(1.0f);
            }
        });
        view.showPopupWindow(popupWindow);
        view.setBackGroundAlpha(0.55f);

    }
    public void setToolBarVisible(boolean visible) {
        this.isToolBarVisible = visible;
    }

    public void keyBackAction(){
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtil.show(view.getContext(), "click again to quit", 2000);
            mExitTime = System.currentTimeMillis();
        } else {
            ActivityManager.getInstance().exit();
        }
    }
}
