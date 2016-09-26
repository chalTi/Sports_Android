package com.wentongwang.mysports.views.activity.home;


import com.wentongwang.mysports.utils.Logger;

import java.math.BigDecimal;

/**
 * Created by Wentong WANG on 2016/9/16.
 */
public class HomePresenter {

    private HomeView view;

    private int currentPage = 0;
    private boolean isToolBarVisible = true;
    public HomePresenter(HomeView homeView) {
        this.view = homeView;
    }

    /**
     * hide or show toolbar depende on different page
     *
     * @param position       page position
     */
    public void toolBarAnim(int position) {

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

    public void setToolBarVisible(boolean visible) {
        this.isToolBarVisible = visible;
    }
}
