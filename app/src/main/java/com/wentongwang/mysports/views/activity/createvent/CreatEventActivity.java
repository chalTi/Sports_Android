package com.wentongwang.mysports.views.activity.createvent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.PointsLayout;
import com.wentongwang.mysports.model.module.SportEvents;
import com.wentongwang.mysports.utils.Logger;
import com.wentongwang.mysports.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * creat event layout
 * <p>
 * Created by Wentong WANG on 2016/9/9.
 */
public class CreatEventActivity extends BaseActivity implements CreatEventView {


    @BindView(R.id.ll_point_container)
    protected PointsLayout mPointContainer;
    @BindView(R.id.event_type_container)
    protected ViewPager mGridViewContainer;

    private List<SportEvents> sportEvents;
    //save each gridview
    private List<View> listGViews;
    //amount of item in one page
    private int pageItemCount = 9;
    //grideview's page size
    private int gvPageSize = 1;
    private int gvColumns = 3;
    private int totalEvents = 0;

    @Override
    protected boolean notitle() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_creat_event_layout;
    }

    @Override
    protected void initDatasAndViews() {
        listGViews = new ArrayList<>();

        initSportEvents();
        initGirdViewsAndPoints();
        mGridViewContainer.setAdapter(new EventsViewPagerAdapter(listGViews));

    }

    /**
     * 初始化运动events
     */
    private void initSportEvents() {
        sportEvents = new ArrayList<>();
        //Event从服务器获取比较好一点
        sportEvents.add(new SportEvents(R.drawable.basketball, "basketball"));
        sportEvents.add(new SportEvents(R.drawable.soccerball, "soccerball"));
        sportEvents.add(new SportEvents(R.drawable.football, "football"));
        sportEvents.add(new SportEvents(R.drawable.volleyball, "volleyball"));
        sportEvents.add(new SportEvents(R.drawable.badminton, "badminton"));
        sportEvents.add(new SportEvents(R.drawable.pingpang, "pingpang"));
        sportEvents.add(new SportEvents(R.drawable.tennis, "tennis"));
        sportEvents.add(new SportEvents(R.drawable.bicycle, "bicycle"));
        sportEvents.add(new SportEvents(R.drawable.running, "running"));

        sportEvents.add(new SportEvents(R.drawable.swimming, "swimming"));
        sportEvents.add(new SportEvents(R.drawable.exercise, "exercise"));
        sportEvents.add(new SportEvents(R.drawable.boxing, "boxing"));

        totalEvents = sportEvents.size();
    }

    /**
     * 初始化gridview底部的小圆点 and listGirdView
     */
    private void initGirdViewsAndPoints() {
        if (totalEvents > 0) {
            gvPageSize = totalEvents / pageItemCount + 1;
            mPointContainer.initPoints(gvPageSize);
            for (int i = 0; i < gvPageSize; i++) {
                //add gridview to list
                listGViews.add(getViewPagerItem(i));
            }
        }

    }

    @Override
    protected void initEvents() {

        mGridViewContainer.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPointContainer.setPointSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 获取不同页的gridview
     *
     * @param index
     * @return
     */
    private View getViewPagerItem(int index) {
        LayoutInflater inflater = (LayoutInflater) CreatEventActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.viewpage_gridview, null);
        GridView gridView = (GridView) layout.findViewById(R.id.grid_view);

        gridView.setNumColumns(gvColumns);

        EventsGridViewAdapter adapter = new EventsGridViewAdapter(index, pageItemCount,sportEvents);

        gridView.setAdapter(adapter);

        return gridView;
    }


    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }


}

