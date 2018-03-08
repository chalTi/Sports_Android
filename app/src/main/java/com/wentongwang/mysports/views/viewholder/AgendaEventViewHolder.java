package com.wentongwang.mysports.views.viewholder;

import android.view.View;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.CircleImageView;
import com.wentongwang.mysports.custome.MyProgressBarHorizontal;
import com.wangwentong.sports_api.model.AgendaEvents;

import butterknife.BindView;

/**
 * Created by Wentong WANG on 2017/4/2.
 */

public class AgendaEventViewHolder extends ItemViewHolder<AgendaEvents> {

    @BindView(R.id.user_head)
    CircleImageView userHead;
    @BindView(R.id.tv_event_type)
    TextView eventType;
    @BindView(R.id.tv_event_time)
    TextView eventTime;
    @BindView(R.id.tv_event_place)
    TextView eventPlace;
    @BindView(R.id.event_progress_bar)
    MyProgressBarHorizontal eventProgress;

    public AgendaEventViewHolder(View view) {
        super(view);
    }

    @Override
    public void setItem(AgendaEvents agendaEvents) {
        eventPlace.setText(agendaEvents.getUserEventsPlace());
        eventTime.setText(agendaEvents.getUserEventsTime());
        eventType.setText(agendaEvents.getUserEventsName());
        eventProgress.setProgress(Integer.parseInt(agendaEvents.getEventsProgress()));
        userHead.setOnClickListener(v -> {

        });
    }
}
