package com.wentongwang.mysports.views.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ExpandableListView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.base.BaseFragment;
import com.wangwentong.sports_api.model.SportsFirstClass;
import com.wangwentong.sports_api.model.SportsSecondClass;
import com.wentongwang.mysports.views.activity.eventdetail.EventDetailActivity;

import java.util.List;

import butterknife.BindView;

/**
 * test
 * Created by Wentong WANG on 2016/9/17.
 */
public class HomeFragment extends BaseFragment implements HomeFragView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.expandable_list)
    protected ExpandableListView expandableListView;
    @BindView(R.id.home_swipe_container)
    protected SwipeRefreshLayout homeContainer;

    private static final int REFRESH_COMPLETE = 0X110;

    private HomeFragPresenter mPresenter = new HomeFragPresenter(this);

    private HomeExpandableListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_layout;
    }

    @Override
    public void initDates() {
        mPresenter.init(getActivity());
        adapter = new HomeExpandableListAdapter(getActivity());
        expandableListView.setAdapter(adapter);

        homeContainer.setColorSchemeColors(
                android.R.color.holo_red_light,
                R.color.holo_orange_dark,
                R.color.holo_yellow_dark,
                android.R.color.holo_green_light,
                R.color.holo_teal_light,
                R.color.holo_blue_light,
                R.color.holo_purple_light
        );
        homeContainer.setRefreshing(false);

        mPresenter.getSportEvents();

    }

    @Override
    public void initEvents() {
        homeContainer.setOnRefreshListener(this);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                mPresenter.goToEventDetail(groupPosition,childPosition);
                return false;
            }
        });
    }

    @Override
    public void onRefresh() {
        mPresenter.getSportEvents();
    }

    @Override
    public void showProgressBar() {
        homeContainer.setRefreshing(true);
    }

    @Override
    public void hideProgressBar() {
        homeContainer.setRefreshing(false);
    }

    @Override
    public void refreshList(List<SportsFirstClass> list) {
        adapter.setSports(list);
//        adapter.refresh();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void goToEventDetail(SportsSecondClass info) {
        Intent it = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", info);
        it.putExtras(bundle);
        it.setClass(getActivity(), EventDetailActivity.class);
        startActivity(it);
    }

    @Override
    public void setHomeEventList(List<SportsFirstClass> list) {
        adapter.setSports(list);
    }

    @Override
    public void SportsEventDetail(List<SportsSecondClass> list) {
    }
}
