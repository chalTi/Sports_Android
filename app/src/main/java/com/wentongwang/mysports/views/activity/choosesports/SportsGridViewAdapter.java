package com.wentongwang.mysports.views.activity.choosesports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.model.module.SportEvents;
import com.wentongwang.mysports.views.adapters.AbstractSportTypesGridViewAdapter;
import com.wentongwang.mysports.views.viewholder.SportTypeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wentong WANG on 2017/1/20.
 */
public class SportsGridViewAdapter extends AbstractSportTypesGridViewAdapter {

    private PresenterHandler handler;

    /**
     * @param context       上下文
     * @param list
     * @param index         页码
     * @param pageItemCount 每页个数
     */
    public SportsGridViewAdapter(Context context, List<?> list, int index, int pageItemCount) {
        super(context, list, index, pageItemCount);
    }

    public void setPresenterHandler(PresenterHandler handler){
        this.handler = handler;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        SportTypeViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sport_type, null);
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

    @Override
    protected void onSportTypeSelectSelect(int position, SportEvents sportEvents) {

    }

}