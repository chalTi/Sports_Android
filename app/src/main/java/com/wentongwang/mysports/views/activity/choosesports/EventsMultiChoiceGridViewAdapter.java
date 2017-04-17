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
public class EventsMultiChoiceGridViewAdapter extends AbstractSportTypesGridViewAdapter {

    private PresenterHandler handler;

    /**
     * @param context       上下文
     * @param list
     * @param index         页码
     * @param pageItemCount 每页个数
     */
    public EventsMultiChoiceGridViewAdapter(Context context, List<?> list, int index, int pageItemCount) {
        super(context, list, index, pageItemCount);
    }

    public void setPresenterHandler(PresenterHandler handler){
        this.handler = handler;
    }

    @Override
    protected void onSportTypeSelectSelect(int position, SportEvents sportEvents) {
        if (sportEvents.isSelected()) {
            //设为不选定
            sportEvents.setIsSelected(false);
            items.set(position,sportEvents);
            //从已选择的运动中剔除
            handler.removeChooseEvent(sportEvents);
        } else {
            //设为选定
            sportEvents.setIsSelected(true);
            items.set(position,sportEvents);
            //加入到已选择运动中
            handler.addChooseEvent(sportEvents);
        }
        notifyDataSetChanged();
    }

}