package com.wentongwang.mysports.views.fragment.friendrequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wentongwang.mysports.R;

/**
 * Created by qifan on 2017/3/25.
 */

public class GridViewAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 14;
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_request_gridview, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    class ViewHolder {


        public ViewHolder(View view) {

        }

    }
}
