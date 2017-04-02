package com.wentongwang.mysports.views.viewholder;

import android.view.View;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.CircleImageView;
import com.wentongwang.mysports.model.module.SportsSecondClass;

import butterknife.BindView;

/**
 * Created by Wentong WANG on 2017/4/2.
 */

public class HomeSportsItemViewHolder extends ItemViewHolder<SportsSecondClass> {

    @BindView(R.id.creator_head)
    CircleImageView creatorHead;
    @BindView(R.id.creator_name)
    TextView creatorName;
    @BindView(R.id.event_start_time)
    TextView eventStartTime;
    @BindView(R.id.event_end_time)
    TextView eventEndTime;
    @BindView(R.id.event_place)
    TextView eventPlace;
    @BindView(R.id.event_number_vs_total)
    TextView eventNumber;

    public HomeSportsItemViewHolder(View view) {
        super(view);
    }

    @Override
    public void setItem(SportsSecondClass sportsSecondClass) {
        creatorName.setText(sportsSecondClass.getEventCreatorName());
        eventPlace.setText(sportsSecondClass.getEventPlace());
        eventStartTime.setText(sportsSecondClass.getEventStartTime());
        eventEndTime.setText(sportsSecondClass.getEventEndTime());
        eventNumber.setText(context.getString(R.string.event_participator_number_vs_total,
                sportsSecondClass.getParticipatesNumber(), sportsSecondClass.getEventNumber()));
    }
}
