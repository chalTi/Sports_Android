package com.wentongwang.mysports.views.viewholder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.wentongwang.mysports.R;
import com.wangwentong.sports_api.model.SportEvents;

import butterknife.BindView;

/**
 * Created by Wentong WANG on 2017/4/2.
 */

public class SportTypeViewHolder extends ItemViewHolder<SportEvents> {

    @BindView(R.id.event_icon)
    ImageView eventIcon;
    @BindView(R.id.iv_selected)
    ImageView selected;

    private SportEvents event;

    public SportTypeViewHolder(View view) {
        super(view);
    }

    @Override
    public void setItem(SportEvents sportEvents) {
        this.event = sportEvents;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), sportEvents.getEventImage());
        eventIcon.setImageBitmap(bitmap);

        if (sportEvents.isSelected()) {
            selected.setVisibility(View.VISIBLE);
        } else {
            selected.setVisibility(View.INVISIBLE);
        }
    }

    public void setOnIconSelectListener(View.OnClickListener listener){
        eventIcon.setOnClickListener(listener);
    }

    public boolean isEventSelected(){
        return event.isSelected();
    }

    public void setSelectedIconVisibility(boolean visibility){
        if (visibility){
            selected.setVisibility(View.INVISIBLE);
            event.setIsSelected(false);
        }else{
            selected.setVisibility(View.VISIBLE);
            event.setIsSelected(true);
        }
    }
}
