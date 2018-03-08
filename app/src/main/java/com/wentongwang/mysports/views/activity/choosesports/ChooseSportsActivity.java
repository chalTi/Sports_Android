package com.wentongwang.mysports.views.activity.choosesports;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.NoScrollGridView;
import com.wentongwang.mysports.custome.PointsLayout;
import com.wangwentong.sports_api.model.SportEvents;
import com.wentongwang.mysports.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Wentong WANG on 2016/10/14.
 */
public class ChooseSportsActivity extends BaseActivity implements ChooseSportsView {
    private static final int FINISHE_CHOSE_SPORTS = 0x123;

    @BindView(R.id.ll_point_container)
    protected PointsLayout mPointContainer;
    @BindView(R.id.event_type_container)
    protected ViewPager mGridViewContainer;
    @BindView(R.id.sports_chosed_container)
    protected NoScrollGridView gvSportsChoose;
    @BindView(R.id.confirm_btn)
    protected Button btnConfirm;

    //save each gridview
    private List<View> listGridViews;
    //amount of item in one page
    private int pageItemCount = 9;
    //grideview's page size
    private int gvPageSize = 1;
    private int gvColumns = 3;

    private SportsViewPagerAdapter adapter;
    private EventsMultiChoiceGridViewAdapter gridViewAdapter;
    private SportsChosenAdapter sportsChooseAdapter;

    private ChooseSportsPresenter mPresenter = new ChooseSportsPresenter();

    @Override
    protected boolean noTitle() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chose_sports_layout;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setView(this);
        mPresenter.init(ChooseSportsActivity.this);
    }

    @Override
    protected void initDatesAndViews() {


        listGridViews = new ArrayList<>();
        mPresenter.initSportEvents();

        adapter = new SportsViewPagerAdapter(listGridViews);
        mGridViewContainer.setAdapter(adapter);
        sportsChooseAdapter = new SportsChosenAdapter();
        gvSportsChoose.setAdapter(sportsChooseAdapter);
    }


    /**
     * 初始化gridview底部的小圆点 and listGirdView
     */
    public void initGirdViewsAndPoints() {
        int totalEvents = mPresenter.getTotalEvents();
        if (totalEvents > 0) {
            gvPageSize = totalEvents / pageItemCount + 1;
            mPointContainer.initPoints(gvPageSize);
            for (int i = 0; i < gvPageSize; i++) {
                //add gridview to list
                listGridViews.add(getViewPagerItem(i));
            }
        }
    }

    @Override
    public void refreshEventChoose(List<SportEvents> list) {
        sportsChooseAdapter.setItems(list);
        sportsChooseAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initEvents() {
        mGridViewContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        btnConfirm.setOnClickListener(v -> {
            setResult(FINISHE_CHOSE_SPORTS);
            onBackPressed();
        });
    }


    /**
     * 获取不同页的gridview
     *
     * @param index
     * @return
     */
    private View getViewPagerItem(int index) {
        LayoutInflater inflater = (LayoutInflater) ChooseSportsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.viewpage_gridview, null);
        GridView gridView = (GridView) layout.findViewById(R.id.grid_view);

        gridView.setNumColumns(gvColumns);

        gridViewAdapter = new EventsMultiChoiceGridViewAdapter(this, mPresenter.getSportEvents(), index, pageItemCount);
        gridViewAdapter.setPresenterHandler(mPresenter);

        gridView.setAdapter(gridViewAdapter);

        return gridView;
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }


}

