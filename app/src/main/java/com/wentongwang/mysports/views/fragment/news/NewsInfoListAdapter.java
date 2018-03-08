package com.wentongwang.mysports.views.fragment.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.constant.IntentConstants;
import com.wangwentong.sports_api.model.NewsInfo;
import com.wentongwang.mysports.utils.Logger;
import com.wentongwang.mysports.views.activity.newscomment.NewsCommentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qifan on 2017/3/25.
 */

public class NewsInfoListAdapter extends BaseAdapter {
    private List<NewsInfo> itemList = new ArrayList<>();
    private Context context;
    public void setItemList(List<NewsInfo> list) {
        this.itemList = list;
    }
    public NewsInfoListAdapter(Context ctx){
        context = ctx;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewsInfo item = itemList.get(position);
        holder.news_content.setText(item.getNewsContent());
        holder.new_liked.setText(item.getNewsLikesTotal());
        if (item.getNewsLiked().equals("0")) {
            holder.new_liked.setChecked(false);
        } else {
            holder.new_liked.setChecked(true);
        }
        final ViewHolder finalHolder = holder;
        holder.new_liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsInfo item = (NewsInfo) getItem(position);
                if (item.getNewsLiked().equals("0")) {
                    Logger.i("xxx", "true");
                    item.setNewsLiked("1");
                    finalHolder.new_liked.setChecked(true);
                } else {
                    item.setNewsLiked("0");
                    finalHolder.new_liked.setChecked(false);
                }
                itemList.remove(position);
                itemList.add(position, item);
                Logger.i("xxx", itemList.get(position).getNewsLiked());
                notifyDataSetChanged();
            }
        });
        holder.news_comment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                NewsInfo item = (NewsInfo) getItem(position);
                Intent intent = new Intent(context,NewsCommentActivity.class);
                intent.putExtra(IntentConstants.EXTRA_NEWS_ITEM,item);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
    class ViewHolder {
        CheckBox new_liked;
        ImageView news_photo;
        TextView news_content;
        TextView news_comment;

        public ViewHolder(View view) {
            new_liked = (CheckBox) view.findViewById(R.id.like_button);
            news_photo = (ImageView) view.findViewById(R.id.iv_news_item_background);
            news_content = (TextView) view.findViewById(R.id.tv_news_item_content);
            news_comment = (TextView) view.findViewById(R.id.comment_button);
        }
    }
}
