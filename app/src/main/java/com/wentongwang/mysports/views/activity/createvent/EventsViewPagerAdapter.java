package com.wentongwang.mysports.views.activity.createvent;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by qifan on 2017/3/25.
 * viewpager的adapter
 */

public class EventsViewPagerAdapter extends PagerAdapter {

    private List<View> listGViews;

    public EventsViewPagerAdapter(List<View> listGViews) {
        this.listGViews = listGViews;
    }

    @Override
        public int getCount() {
            return listGViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View gv = listGViews.get(position);
            //gridview加入到viewpager里
            container.addView(gv);
            return gv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View gv = listGViews.get(position);
            container.removeView(gv);
        }

}
