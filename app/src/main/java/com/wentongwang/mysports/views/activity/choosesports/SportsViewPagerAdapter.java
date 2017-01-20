package com.wentongwang.mysports.views.activity.choosesports;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

/**
 * Created by Wentong WANG on 2017/1/20.
 */
public class SportsViewPagerAdapter extends PagerAdapter {

    private List<View> listGridViews;

    public SportsViewPagerAdapter(List<View> listGridViews){
        this.listGridViews = listGridViews;
    }

    @Override
    public int getCount() {
        return listGridViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View gv = listGridViews.get(position);
        //gridview加入到viewpager里
        container.addView(gv);
        return gv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View gv = listGridViews.get(position);
        container.removeView(gv);
    }
}
