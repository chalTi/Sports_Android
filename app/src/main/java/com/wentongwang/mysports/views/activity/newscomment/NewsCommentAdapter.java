package com.wentongwang.mysports.views.activity.newscomment;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.model.module.CommentInfo;
import com.wentongwang.mysports.model.module.NewsInfo;
import com.wentongwang.mysports.utils.Logger;
import com.wentongwang.mysports.views.fragment.news.NewsInfoListAdapter;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */

public class NewsCommentAdapter extends BaseAdapter {
    private List<CommentInfo> itemList = new ArrayList<>();

    public void setItemList(List<CommentInfo> list) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        CommentInfo item = itemList.get(position);
        holder.comment_content.setText(item.getComment_content());

        final ViewHolder finalHolder = holder;

        return convertView;
    }


    class ViewHolder {
        TextView comment_content;


        public ViewHolder(View view) {
            comment_content = (TextView) view.findViewById(R.id.news_comment_content);


        }
    }
}
