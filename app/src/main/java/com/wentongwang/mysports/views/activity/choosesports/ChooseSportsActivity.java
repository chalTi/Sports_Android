package com.wentongwang.mysports.views.activity.choosesports;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.NoScrollGridView;
import com.wentongwang.mysports.model.module.SportEvents;
import com.wentongwang.mysports.views.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wentong WANG on 2016/10/14.
 */
public class ChooseSportsActivity extends BaseActivity implements ChooseSportsView {
    private static final int FINISHE_CHOSE_SPORTS = 0x123;

    @BindView(R.id.ll_point_container)
    protected LinearLayout mPointContainer;
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
    private SportsGridViewAdapter gridViewAdapter;
    private SportsChosenAdapter sportsChooseAdapter;

    private ChooseSportsPresenter mPresenter = new ChooseSportsPresenter(this);

    @Override
    protected boolean notitle() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chose_sports_layout;
    }

    @Override
    protected void initDatasAndViews() {
        ButterKnife.bind(ChooseSportsActivity.this);
        mPresenter.init(ChooseSportsActivity.this);

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
            for (int i = 0; i < gvPageSize; i++) {
                //add gridview to list
                listGridViews.add(getViewPagerItem(i));

                View point = new View(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
                if (i == 0) {
                    point.setBackgroundResource(R.drawable.point_selected);
                } else {
                    params.leftMargin = 10;
                    point.setBackgroundResource(R.drawable.point_normal);
                }
                mPointContainer.addView(point, params);

            }
            mPointContainer.setVisibility(View.VISIBLE);
        } else {
            mPointContainer.removeAllViews();
            mPointContainer.setVisibility(View.GONE);
        }

    }

    @Override
    public void refreshEventChoose(List<SportEvents> list) {
        sportsChooseAdapter.setItems(list);
        sportsChooseAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initEvents() {
        mGridViewContainer.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPointSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(FINISHE_CHOSE_SPORTS);
                onBackPressed();
            }
        });
    }

    /**
     * position页的圆点变为选中
     *
     * @param position
     */
    private void setPointSelected(int position) {
        int total = mPointContainer.getChildCount();
        for (int i = 0; i < total; i++) {
            View v = mPointContainer.getChildAt(i);
            v.setBackgroundResource(position == i ? R.drawable.point_selected : R.drawable.point_normal);
        }
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

        gridViewAdapter = new SportsGridViewAdapter(index, pageItemCount, mPresenter.getSportEvents());
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

