package com.wentongwang.mysports.views.adapters;

import android.content.Context;
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
 * Created by Wentong WANG on 2016/9/20.
 */
public class GridViewAdapter extends BaseAdapter {
    private List<SportEvents> items;

    private Context context;
    /** ViewPager页码 */
    private int index;
    /** 根据屏幕大小计算得到的每页item个数 */
    private int pageItemCount;
    private int totalEvents;

    public GridViewAdapter(Context context, List<?> list) {
        this.context = context;
        this.index = 0;
        this.pageItemCount = totalEvents;
        this.totalEvents = list.size();

        for (int i = 0; i < totalEvents; i++) {
            items.add((SportEvents) list.get(i));
        }
    }
    /**
     *
     * @param context 上下文
     * @param index 页码
     * @param pageItemCount 每页个数
     */
    public GridViewAdapter(Context context, List<?> list, int index, int pageItemCount) {
        this.context = context;
        this.index = index;
        this.pageItemCount = pageItemCount;
        this.totalEvents = list.size();

        items = new ArrayList<SportEvents>();

        //get pageitemcount items
        int list_index = index * pageItemCount;
        int lastItem = list_index + pageItemCount;
        if (lastItem > totalEvents) {
            lastItem = totalEvents;
        }

        for (int i = list_index; i < lastItem; i++) {
            items.add((SportEvents) list.get(i));
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
        ViewHolder holder = null;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.events_gridview_items, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SportEvents event = items.get(position);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), event.getEventImage());
        holder.event_icon.setImageBitmap(bitmap);

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

        public ViewHolder(View view){
            event_icon = (ImageView) view.findViewById(R.id.event_icon);
            selected = (ImageView) view.findViewById(R.id.iv_selected);
        }
    }
}
