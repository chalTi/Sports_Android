package com.wentongwang.mysports.views.fragment.agenda;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.CircleImageView;
import com.wentongwang.mysports.custome.MyProgressBarHorizontal;
import com.wentongwang.mysports.model.module.AgendaEvents;
import com.wentongwang.mysports.views.viewholder.AgendaEventViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2017/3/25.
 */

public class AgendaEventsAdapter extends RecyclerView.Adapter<AgendaEventViewHolder> {
    private List<AgendaEvents> itemList = new ArrayList<>();

    public void setItemList(List<AgendaEvents> itemList) {
        this.itemList = itemList;
    }

    @Override
    public AgendaEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda_event_list, null);
        return new AgendaEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AgendaEventViewHolder holder, int position) {
        AgendaEvents agendaEvents = itemList.get(position);
        holder.setItem(agendaEvents);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
