package com.wentongwang.mysports.views.fragment.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.model.module.NewsInfo;
import com.wentongwang.mysports.utils.Logger;
import com.wentongwang.mysports.base.BaseFragment;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Wentong WANG on 2016/9/17.
 */
public class NewsFragment extends BaseFragment implements NewsView {
    @BindView(R.id.news_listview)
    protected ListView newslistview;

    private NewsInfoListAdapter newsListViewAdapter;
    private NewsPresenter mPresenter = new NewsPresenter(this);

    @Override
    public int getLayoutId() {
        return R.layout.news_fragment_layout;
    }

    @Override
    public void initDatas() {
        mPresenter.init(getActivity());
        newsListViewAdapter = new NewsInfoListAdapter();
        newslistview.setAdapter(newsListViewAdapter);

        mPresenter.getNews();
    }

    @Override
    public void initEvents() {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void setNewsList(List<NewsInfo> list) {
        newsListViewAdapter.setItemList(list);
        newsListViewAdapter.notifyDataSetChanged();
    }
}
