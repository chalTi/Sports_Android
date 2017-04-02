package com.wentongwang.mysports.views.activity.createvent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.model.module.SportEvents;
import com.wentongwang.mysports.utils.Logger;
import com.wentongwang.mysports.views.viewholder.SportTypeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qifan on 2017/3/25.
 * adapter for EventGridView
 * *
 */

public class EventsGridViewAdapter extends BaseAdapter {
    private List<SportEvents> items;

    /**
     * ViewPager页码
     */
    private int index;
    /**
     * 根据屏幕大小计算得到的每页item个数
     */
    private int pageItemCount;

    /**
     * @param index
     * @param pageItemCount
     * @param sportEvents
     */

    public EventsGridViewAdapter(int index, int pageItemCount, List<SportEvents> sportEvents) {
        this.index = index;
        this.pageItemCount = pageItemCount;
        int totalEvents = sportEvents.size();
        items = new ArrayList<>();
        //get pageitemcount items
        int list_index = index * pageItemCount;
        int lastItem = list_index + pageItemCount;
        if (lastItem > totalEvents) {
            lastItem = totalEvents;
        }

        for (int i = list_index; i < lastItem; i++) {
            Logger.i("gv", "item name " + sportEvents.get(i).getEventName());
            items.add(sportEvents.get(i));
        }
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
        SportTypeViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_gridview_items, null);
            holder = new SportTypeViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (SportTypeViewHolder) convertView.getTag();
        }
        SportEvents event = items.get(position);
        holder.setItem(event);

        return convertView;
    }

}

