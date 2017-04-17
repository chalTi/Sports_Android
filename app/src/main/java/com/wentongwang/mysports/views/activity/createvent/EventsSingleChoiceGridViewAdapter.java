package com.wentongwang.mysports.views.activity.createvent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.model.module.SportEvents;
import com.wentongwang.mysports.views.activity.choosesports.PresenterHandler;
import com.wentongwang.mysports.views.adapters.AbstractSportTypesGridViewAdapter;
import com.wentongwang.mysports.views.viewholder.SportTypeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qifan on 2017/3/25.
 * adapter for EventGridView
 * *
 */

public class EventsSingleChoiceGridViewAdapter extends AbstractSportTypesGridViewAdapter {

    private PresenterHandler handler;

    public EventsSingleChoiceGridViewAdapter(Context context, List<?> list, int index, int pageItemCount) {
        super(context, list, index, pageItemCount);
    }

    public void setSelectedSportTypeHandler(PresenterHandler handler) {
        this.handler = handler;
    }

    @Override
    protected void onSportTypeSelectSelect(int position, SportEvents sportEvents) {
        if (sportEvents.isSelected()) {
            //if is selected, remove selected icon
            sportEvents.setIsSelected(false);
            items.set(position, sportEvents);
            handler.removeChooseEvent(sportEvents);
        } else {
            clearAllSelectedStatus();

            sportEvents.setIsSelected(true);
            items.set(position, sportEvents);
            handler.addChooseEvent(sportEvents);
        }
        notifyDataSetChanged();
    }

    private void clearAllSelectedStatus() {
        for (int i = 0; i < items.size(); i++) {
            SportEvents sportEvent = items.get(i);
            sportEvent.setIsSelected(false);
            items.set(i, sportEvent);

        }
    }

}

