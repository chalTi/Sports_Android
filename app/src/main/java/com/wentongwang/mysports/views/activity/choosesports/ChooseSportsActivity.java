package com.wentongwang.mysports.views.activity.choosesports;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.NoScrollGridView;
import com.wentongwang.mysports.model.module.SportEvents;
import com.wentongwang.mysports.views.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wentong WANG on 2016/10/14.
 */
public class ChooseSportsActivity extends BaseActivity implements ChooseSportsView {
    private static final int FINISHE_CHOSE_SPORTS = 0x123;

    @BindView(R.id.ll_point_container)
    protected LinearLayout mPointContainer;
    @BindView(R.id.event_type_container)
    protected ViewPager mGridViewContainer;
    @BindView(R.id.sports_chosed_container)
    protected NoScrollGridView gvSportsChoose;
    @BindView(R.id.confirm_btn)
    protected Button btnConfirm;

    private MySportsChosenAdapter sportsChooseAdapter;

    //save each gridview
    private List<View> listGridViews;
    //amount of item in one page
    private int pageItemCount = 9;
    //grideview's page size
    private int gvPageSize = 1;
    private int gvColumns = 3;

    private ChooseSportsPresenter mPresenter = new ChooseSportsPresenter(this);

    @Override
    protected boolean notitle() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chose_sports_layout;
    }

    @Override
    protected void initDatasAndViews() {
        ButterKnife.bind(ChooseSportsActivity.this);
        mPresenter.init(ChooseSportsActivity.this);

        listGridViews = new ArrayList<>();
        mPresenter.initSportEvents();

        mGridViewContainer.setAdapter(new MyViewPagerAdapter());
        sportsChooseAdapter = new MySportsChosenAdapter();
        gvSportsChoose.setAdapter(sportsChooseAdapter);
    }


    /**
     * 初始化gridview底部的小圆点 and listGirdView
     */
    public void initGirdViewsAndPoints() {
        int totalEvents = mPresenter.getTotalEvents();
        if (totalEvents > 0) {
            gvPageSize = totalEvents / pageItemCount + 1;
            for (int i = 0; i < gvPageSize; i++) {
                //add gridview to list
                listGridViews.add(getViewPagerItem(i));

                View point = new View(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
                if (i == 0) {
                    point.setBackgroundResource(R.drawable.point_selected);
                } else {
                    params.leftMargin = 10;
                    point.setBackgroundResource(R.drawable.point_normal);
                }
                mPointContainer.addView(point, params);

            }
            mPointContainer.setVisibility(View.VISIBLE);
        } else {
            mPointContainer.removeAllViews();
            mPointContainer.setVisibility(View.GONE);
        }

    }

    @Override
    public void refreshEventChoose(List<SportEvents> list) {
        sportsChooseAdapter.setItems(list);
        sportsChooseAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initEvents() {
        mGridViewContainer.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPointSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(FINISHE_CHOSE_SPORTS);
                onBackPressed();
            }
        });
    }

    /**
     * position页的圆点变为选中
     *
     * @param position
     */
    private void setPointSelected(int position) {
        int total = mPointContainer.getChildCount();
        for (int i = 0; i < total; i++) {
            View v = mPointContainer.getChildAt(i);
            v.setBackgroundResource(position == i ? R.drawable.point_selected : R.drawable.point_normal);
        }
    }

    /**
     * 获取不同页的gridview
     *
     * @param index
     * @return
     */
    private View getViewPagerItem(int index) {
        LayoutInflater inflater = (LayoutInflater) ChooseSportsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.viewpage_gridview, null);
        GridView gridView = (GridView) layout.findViewById(R.id.grid_view);

        gridView.setNumColumns(gvColumns);

        MyGridViewAdapter adapter = new MyGridViewAdapter(index, pageItemCount, mPresenter.getSportEvents());

        gridView.setAdapter(adapter);

        return gridView;
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    /**
     * viewpager's adapter
     */
    private class MyViewPagerAdapter extends PagerAdapter {

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
            mGridViewContainer.addView(gv);
            return gv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View gv = listGridViews.get(position);
            mGridViewContainer.removeView(gv);
        }
    }

    /**
     * girdview's adapter in the viewpager
     */
    private class MyGridViewAdapter extends BaseAdapter {
        private List<SportEvents> items;

        /**
         * ViewPager页码
         */
        private int index;
        /**
         * 根据屏幕大小计算得到的每页item个数
         */
        private int pageItemCount;

        /**
         * 构造函数
         *
         * @param index         页码
         * @param pageItemCount 每页个数
         */
        public MyGridViewAdapter(int index, int pageItemCount, List<SportEvents> sportEvents) {
            this.index = index;
            this.pageItemCount = pageItemCount;
            int totalEvents = sportEvents.size();
            items = new ArrayList<SportEvents>();

            //get pageitemcount items
            int list_index = index * pageItemCount;
            int lastItem = list_index + pageItemCount;
            if (lastItem > totalEvents) {
                lastItem = totalEvents;
            }
            for (int i = list_index; i < lastItem; i++) {
                items.add(sportEvents.get(i));
            }
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_gridview_items, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final ViewHolder finalHolder = holder;
            final SportEvents event = items.get(position);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), event.getEvent_image());
            holder.event_icon.setImageBitmap(bitmap);
            holder.event_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (event.isSelected()) {
                        //设为不选定
                        finalHolder.selected.setVisibility(View.INVISIBLE);
                        items.get(position).setIsSelected(false);
                        //从已选择的运动中剔除
                        mPresenter.removeChooseEvent(event);
                    } else {
                        //设为选定
                        finalHolder.selected.setVisibility(View.VISIBLE);
                        items.get(position).setIsSelected(true);
                        //加入到已选择运动中
                        mPresenter.addChooseEvent(event);
                    }
                }
            });


            if (event.isSelected()) {
                holder.selected.setVisibility(View.VISIBLE);
            } else {
                holder.selected.setVisibility(View.INVISIBLE);
            }

            return convertView;
        }

        private class ViewHolder {
            ImageView event_icon;
            ImageView selected;

            public ViewHolder(View view) {
                event_icon = (ImageView) view.findViewById(R.id.event_icon);
                selected = (ImageView) view.findViewById(R.id.iv_selected);
            }
        }
    }

    /**
     * 已选择运动的girdview的adapter
     */
    class MySportsChosenAdapter extends BaseAdapter {
        private List<SportEvents> items;

        public MySportsChosenAdapter() {
            items = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(ChooseSportsActivity.this).inflate(R.layout.events_gridview_items, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            SportEvents event = items.get(position);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), event.getEvent_image());
            holder.event_icon.setImageBitmap(bitmap);
            holder.selected.setVisibility(View.INVISIBLE);
            return convertView;
        }

        public List<SportEvents> getItems() {
            return items;
        }

        public void setItems(List<SportEvents> items) {
            this.items = items;
        }

        private class ViewHolder {
            ImageView event_icon;
            ImageView selected;

            public ViewHolder(View view) {
                event_icon = (ImageView) view.findViewById(R.id.event_icon);
                selected = (ImageView) view.findViewById(R.id.iv_selected);
            }
        }
    }

}

