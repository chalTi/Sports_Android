package com.wentongwang.mysports.custome;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.wentongwang.mysports.R;

/**
 * Created by Wentong WANG on 2017/1/20.
 */
public class PointsLayout extends LinearLayout {
    public PointsLayout(Context context) {
        super(context);
    }

    public PointsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PointsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化的小圆点
     */
    public void initPoints(int pageSize) {
        if (pageSize > 0) {
            for (int i = 0; i < pageSize; i++) {
                addPoint(i);
            }
            setVisibility(View.VISIBLE);
        } else {
            removeAllViews();
            setVisibility(View.GONE);
        }
    }

    private void addPoint(int index) {
        View point = new View(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
        if (index == 0) {
            point.setBackgroundResource(R.drawable.point_selected);
        } else {
            params.leftMargin = 10;
            point.setBackgroundResource(R.drawable.point_normal);
        }
        addView(point, params);
    }

    public void setPointSelected(int position) {
        int total = getChildCount();
        for (int i = 0; i < total; i++) {
            View v = getChildAt(i);
            v.setBackgroundResource(position == i ? R.drawable.point_selected : R.drawable.point_normal);
        }
    }
}
