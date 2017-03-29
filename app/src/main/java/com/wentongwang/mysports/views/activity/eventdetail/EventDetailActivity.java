package com.wentongwang.mysports.views.activity.eventdetail;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wentong WANG on 2016/10/24.
 */
public class EventDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @Override
    protected boolean noTitle() {

        return false;
    }

    /**
     * 设置Activity的layout
     *
     * @return layout的id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_event_detail_layout;
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initDatesAndViews() {
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        setSupportActionBar(toolbar);

        collapsingToolbarLayout.setTitle("Title");

    }

    /**
     * 初始化控件的事件
     */
    @Override
    protected void initEvents() {

    }


    /**
     * 用于监听Toolbar上的点击监听
     * 在Toolbar上的左上角的返回箭头的键值为Android.R.id.home，切记为Android.R.id.home，而不是R.id.home，否则可能监听不到左上角监听的点击事件
     * @param item {@link MenuItem} that was clicked
     * @return <code>true</code> if the event was handled, <code>false</code> otherwise.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
