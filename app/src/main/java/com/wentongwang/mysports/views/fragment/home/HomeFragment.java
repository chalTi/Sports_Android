package com.wentongwang.mysports.views.fragment.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.NoScrollListView;
import com.wentongwang.mysports.views.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Wentong WANG on 2016/9/17.
 */
public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

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

    private void initViews(View root) {
        //****没有使用R.id 需要修改

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
    }

    @Override
    public void onRefresh() {
        homehandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
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
                Log.i("xxxx", "init");
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
