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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_gridview_items, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ViewHolder finalHolder = holder;
        final SportEvents event = items.get(position);
        Bitmap bitmap = BitmapFactory.decodeResource(parent.getContext().getResources(), event.getEventImage());
        holder.event_icon.setImageBitmap(bitmap);
        holder.event_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (event.isSelected()) {
                    //设为不选定
                    finalHolder.selected.setVisibility(View.INVISIBLE);
                    items.get(position).setIsSelected(false);
                    //从已选择的运动中剔除
                    handler.removeChooseEvent(event);
                } else {
                    //设为选定
                    finalHolder.selected.setVisibility(View.VISIBLE);
                    items.get(position).setIsSelected(true);
                    //加入到已选择运动中
                    handler.addChooseEvent(event);
                }
            }
        });


        if (event.isSelected()) {
            holder.selected.setVisibility(View.VISIBLE);
        } else {
            holder.selected.setVisibility(View.INVISIBLE);
        }

        return convertView;
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