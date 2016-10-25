package com.wentongwang.mysports.views.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.NoScrollListView;
import com.wentongwang.mysports.views.BaseFragment;
import com.wentongwang.mysports.views.activity.eventdetail.EventDetailActivity;

import butterknife.BindView;

/**
 * test
 * Created by Wentong WANG on 2016/9/17.
 */
public class HomeFragment extends BaseFragment implements HomeFragView,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.home_swipe_container)
    protected SwipeRefreshLayout homeContainer;
    @BindView(R.id.home_noscrollistview)
    protected NoScrollListView homeListView;
    private static final int REFRESH_COMPLETE = 0X110;
    private HomeListViewAdapter homeAdapter;

    private Handler homehandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    homeContainer.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_layout;
    }

    @Override
    public void initDatas() {
        homeAdapter = new HomeListViewAdapter();
        homeListView.setAdapter(homeAdapter);
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

//        homeListView= (NoScrollListView) root.findViewById(R.id.home_noscrollistview);
        homeListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void initEvents() {
        homeContainer.setOnRefreshListener(this);
        homeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent();
                it.setClass(getActivity(), EventDetailActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    public void onRefresh() {
        //这里用presenter去请求服务器
        homehandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }

    @Override
    public void showProgressBar() {
        homeContainer.setRefreshing(true);
    }

    @Override
    public void hideProgressBar() {
        homeContainer.setRefreshing(false);
    }

    private class HomeListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 15;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_home_listview, null);
                holder = new ViewHolder();
                holder.tv = (TextView) convertView.findViewById(R.id.content);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }
        private class ViewHolder {
            TextView tv;
        }
    }
}
