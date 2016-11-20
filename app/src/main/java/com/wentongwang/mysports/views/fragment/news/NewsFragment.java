package com.wentongwang.mysports.views.fragment.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.model.module.NewsInfo;
import com.wentongwang.mysports.utils.Logger;
import com.wentongwang.mysports.views.BaseFragment;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wentong WANG on 2016/9/17.
 */
public class NewsFragment extends BaseFragment implements NewsView {
    @BindView(R.id.news_listview)
    protected ListView newslistview;

    private NewsListViewAdapter newsListViewAdapter;
    private NewsPresenter mPresenter = new NewsPresenter(this);

    @Override
    public int getLayoutId() {
        return R.layout.news_fragment_layout;
    }

    @Override
    public void initDatas() {
        mPresenter.init(getActivity());
        newsListViewAdapter = new NewsListViewAdapter();
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

    private class NewsListViewAdapter extends BaseAdapter {
        private List<NewsInfo> itemList = new ArrayList<>();

        public void setItemList(List<NewsInfo> list) {
            this.itemList = list;
        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            return itemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_news_listview, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            NewsInfo item = itemList.get(position);
            holder.news_content.setText(item.getNews_content());
            holder.new_liked.setText(item.getNews_likes_total());
            if (item.getNews_liked().equals("0")) {
                holder.new_liked.setChecked(false);
            } else {
                holder.new_liked.setChecked(true);
            }
            final ViewHolder finalHolder = holder;
            holder.new_liked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsInfo item = (NewsInfo) getItem(position);
                    if (item.getNews_liked().equals("0")) {
                        Logger.i("xxx", "true");
                        item.setNews_liked("1");
                        finalHolder.new_liked.setChecked(true);
                    } else {
                        item.setNews_liked("0");
                        finalHolder.new_liked.setChecked(false);
                    }
                    itemList.remove(position);
                    itemList.add(position, item);
                    Logger.i("xxx", itemList.get(position).getNews_liked());
                    notifyDataSetChanged();
                }
            });

            return convertView;
        }

        class ViewHolder {
            CheckBox new_liked;
            ImageView news_photo;
            TextView news_content;

            public ViewHolder(View view) {
                new_liked = (CheckBox) view.findViewById(R.id.like_button);
                news_photo = (ImageView) view.findViewById(R.id.iv_news_item_background);
                news_content = (TextView) view.findViewById(R.id.tv_news_item_content);
            }
        }
    }
}
