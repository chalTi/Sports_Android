package com.wentongwang.mysports.views.activity.home;


/**
 * Created by Wentong WANG on 2016/9/16.
 */
public class HomePresenter {


    private HomeView view;

    public HomePresenter(HomeView homeView) {
        this.view = homeView;
    }

    /**
     * hide toolbar depende on different page
     *
     * @param position       page position
     * @param positionOffset percentage
     */
    public void hiddenToolBar(int position, float positionOffset) {
        if (position == 2) {
            view.hiddenToolBarAnim(positionOffset);
        }
    }

    /**
     * selon the position to set page in viewpager
     *
     * @param position
     */
    public void setPage(int position) {
        view.getCheckedBtn(position).setChecked(true);

        //change some infos of the toolbar

    }

    public void toCreatEvent() {
        view.goToCreatEventActivity();

    }
}
