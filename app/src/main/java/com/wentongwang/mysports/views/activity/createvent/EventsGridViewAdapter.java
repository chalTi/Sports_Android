package com.wentongwang.mysports.views.activity.createvent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.model.module.SportEvents;
import com.wentongwang.mysports.views.activity.choosesports.PresenterHandler;
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

    private PresenterHandler handler;

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
            items.add(sportEvents.get(i));
        }
    }

    public void setSelectedSportTypeHandler(PresenterHandler handler){
        this.handler = handler;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final SportTypeViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_gridview_items, null);
            holder = new SportTypeViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (SportTypeViewHolder) convertView.getTag();
        }
        final SportEvents event = items.get(position);
        holder.setItem(event);
        holder.setOnIconSelectListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (event.isSelected()) {
                    //if is selected, remove selected icon
                    holder.setSelectedIconVisibility(false);
                    event.setIsSelected(false);
                    items.set(position, event);
                    handler.removeChooseEvent(event);
                } else {
                    holder.setSelectedIconVisibility(true);
                    event.setIsSelected(true);
                    items.set(position, event);
                    handler.addChooseEvent(event);
                }
            }
        });

        return convertView;
    }

}

