package com.wentongwang.mysports.views.fragment.agenda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.views.custome.CircleImageView;
import com.wentongwang.mysports.views.custome.MyProgressBarHorizontal;
import com.wentongwang.mysports.model.module.AgendaEvents;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2017/3/25.
 */

public class AgendaEventsAdapter extends BaseAdapter {
    private List<AgendaEvents> itemList = new ArrayList<>();

    public void setItemList(List<AgendaEvents> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda_event_list, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        AgendaEvents agendaEvents = itemList.get(position);
        viewHolder.event_place.setText(agendaEvents.getUser_events_place());
        viewHolder.event_time.setText(agendaEvents.getUser_events_time());
        viewHolder.event_type.setText(agendaEvents.getUser_events_name());
        viewHolder.event_progress.setProgress(Integer.parseInt(agendaEvents.getEvents_Progress()));
        viewHolder.user_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.user_head)
        CircleImageView user_head;
        @BindView(R.id.tv_event_type)
        TextView event_type;
        @BindView(R.id.tv_event_time)
        TextView event_time;
        @BindView(R.id.tv_event_place)
        TextView event_place;
        @BindView(R.id.event_progress_bar)
        MyProgressBarHorizontal event_progress;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);

        }
    }
}
