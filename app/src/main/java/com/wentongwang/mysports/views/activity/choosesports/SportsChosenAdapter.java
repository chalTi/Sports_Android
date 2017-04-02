package com.wentongwang.mysports.views.activity.choosesports;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.model.module.SportEvents;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wentong WANG on 2017/1/20.
 */
public class SportsChosenAdapter extends BaseAdapter {
    private List<SportEvents> items;

    public SportsChosenAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_gridview_items, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SportEvents event = items.get(position);
        Bitmap bitmap = BitmapFactory.decodeResource(parent.getContext().getResources(), event.getEventImage());
        holder.event_icon.setImageBitmap(bitmap);
        holder.selected.setVisibility(View.INVISIBLE);
        return convertView;
    }

    public List<SportEvents> getItems() {
        return items;
    }

    public void setItems(List<SportEvents> items) {
        this.items = items;
    }

    private class ViewHolder {
        ImageView event_icon;
        ImageView selected;

        public ViewHolder(View view) {
            event_icon = (ImageView) view.findViewById(R.id.event_icon);
            selected = (ImageView) view.findViewById(R.id.iv_selected);
        }
    }
}