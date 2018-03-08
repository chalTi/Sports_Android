package com.wentongwang.mysports.views.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wangwentong.sports_api.model.SportsFirstClass;

import butterknife.BindView;

/**
 * Created by Wentong WANG on 2017/4/2.
 */

public class HomeSportsTypeViewHolder extends ItemViewHolder<SportsFirstClass> {

    @BindView(R.id.sport_image)
    ImageView sportImage;
    @BindView(R.id.sport_type)
    TextView sportType;
    @BindView(R.id.event_total_count)
    TextView eventTotalCount;

    public HomeSportsTypeViewHolder(View view) {
        super(view);
    }

    @Override
    public void setItem(SportsFirstClass sportsFirstClass) {
        sportType.setText(sportsFirstClass.getType());
        eventTotalCount.setText(context.getString(R.string.sports_event_number, sportsFirstClass.getSportEventsNumber()));
    }
}
