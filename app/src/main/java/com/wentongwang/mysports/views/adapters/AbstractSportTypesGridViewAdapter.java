package com.wentongwang.mysports.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wentongwang.mysports.R;
import com.wangwentong.sports_api.model.SportEvents;
import com.wentongwang.mysports.views.viewholder.SportTypeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wentong WANG on 2016/9/20.
 */
public abstract class AbstractSportTypesGridViewAdapter extends BaseAdapter {
    protected List<SportEvents> items;

    protected Context context;
    /**
     * ViewPager页码
     */
    protected int index;
    /**
     * 根据屏幕大小计算得到的每页item个数
     */
    protected int pageItemCount;
    protected int totalEvents;

    /**
     * @param context       上下文
     * @param index         页码
     * @param pageItemCount 每页个数
     */
    public AbstractSportTypesGridViewAdapter(Context context, List<?> list, int index, int pageItemCount) {
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
        return items != null ? items.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return items != null ? items.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        SportTypeViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.item_sport_type, null);
            holder = new SportTypeViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (SportTypeViewHolder) convertView.getTag();
        }

        final SportEvents sportEvents = items.get(position);
        holder.setItem(sportEvents);
        holder.setOnIconSelectListener(v -> onSportTypeSelectSelect(position, sportEvents));
        return convertView;
    }

    protected abstract void onSportTypeSelectSelect(int position, SportEvents sportEvents);
}
