package com.wentongwang.mysports.views.fragment.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.views.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Wentong WANG on 2016/9/17.
 */
public class NewsFragment extends BaseFragment {
    @BindView(R.id.news_listview)
    protected ListView newslistview;

    private NewsListViewAdapter newsListViewAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.news_fragment_layout;
    }

    @Override
    public void initDatas() {
        newsListViewAdapter = new NewsListViewAdapter();
        newslistview.setAdapter(newsListViewAdapter);
    }

    @Override
    public void initEvents() {

    }

    private class NewsListViewAdapter extends BaseAdapter {

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
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_news_listview, null);
                holder = new ViewHolder();
                holder.news_content = (TextView) convertView.findViewById(R.id.tv_news_item_content);
                holder.news_photo = (ImageView) convertView.findViewById(R.id.iv_news_item_background);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.news_content.setText("BASKETBALL MATCH CREATED BY UTT\n" + "This is a great match which you can fight wiz kobe and james, they will teach you to play baskeball, show blba lbakankakabkb bbakkbakb kbkabk kabkb kabkbak bkakb ");
            return convertView;
        }

        private class ViewHolder {
            ImageView news_photo;
            TextView news_content;
        }
    }
}
