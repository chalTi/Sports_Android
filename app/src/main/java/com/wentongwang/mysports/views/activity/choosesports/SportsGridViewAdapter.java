package com.wentongwang.mysports.views.activity.choosesports;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.model.module.SportEvents;
import com.wentongwang.mysports.views.viewholder.SportTypeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wentong WANG on 2017/1/20.
 */
public class SportsGridViewAdapter extends BaseAdapter {
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
     * 构造函数
     *
     * @param index         页码
     * @param pageItemCount 每页个数
     */
    public SportsGridViewAdapter(int index, int pageItemCount, List<SportEvents> sportEvents) {
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

    private PresenterHandler handler;

    public void setPresenterHandler(PresenterHandler handler){
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
        SportTypeViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_gridview_items, null);
            holder = new SportTypeViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SportTypeViewHolder) convertView.getTag();
        }

        final SportEvents event = items.get(position);
        holder.setItem(event);
        final SportTypeViewHolder finalHolder = holder;
        holder.setOnIconSelectListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalHolder.isEventSelected()) {
                    //设为不选定
                    finalHolder.setSelectedIconVisibility(false);
                    items.get(position).setIsSelected(false);
                    //从已选择的运动中剔除
                    handler.removeChooseEvent(event);
                } else {
                    //设为选定
                    finalHolder.setSelectedIconVisibility(true);
                    items.get(position).setIsSelected(true);
                    //加入到已选择运动中
                    handler.addChooseEvent(event);
                }
            }
        });

        return convertView;
    }

}