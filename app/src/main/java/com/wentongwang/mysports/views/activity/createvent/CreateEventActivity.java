package com.wentongwang.mysports.views.activity.createvent;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.constant.IntentConstants;
import com.wentongwang.mysports.custome.PointsLayout;
import com.wentongwang.mysports.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * creat event layout
 * <p>
 * Created by Wentong WANG on 2016/9/9.
 */
public class CreateEventActivity extends BaseActivity implements CreateEventView {

    @BindView(R.id.ll_point_container)
    protected PointsLayout mPointContainer;
    @BindView(R.id.event_type_container)
    protected ViewPager mGridViewContainer;

    @BindView(R.id.event_location)
    protected EditText localisation;

    @BindView(R.id.event_person_number)
    protected EditText participatorNumber;

    @BindView(R.id.event_name)
    protected EditText eventName;

    @BindView(R.id.event_time)
    protected EditText startTime;

    @BindView(R.id.event_description)
    protected EditText description;

    //save each gridview
    private List<View> listGViews;
    private EventsViewPagerAdapter adapter;
    private EventsSingleChoiceGridViewAdapter gridViewAdapter;

    //amount of item in one page
    private int pageItemCount = 9;
    //grideview's page size
    private int gvPageSize = 1;
    private int gvColumns = 3;

    private CreateEventPresenter mPresenter = new CreateEventPresenter();

    @Override
    protected boolean noTitle() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_creat_event_layout;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setView(this);
        mPresenter.init(this);
    }

    @Override
    protected void initDatesAndViews() {
        listGViews = new ArrayList<>();

        mPresenter.initSportEvents();

        adapter = new EventsViewPagerAdapter(listGViews);
        mGridViewContainer.setAdapter(adapter);

    }


    /**
     * 初始化gridview底部的小圆点 and listGirdView
     */
    public void initGirdViewsAndPoints() {
        int totalEvents = mPresenter.getTotalEvents();
        if (totalEvents > 0) {
            gvPageSize = totalEvents / pageItemCount + 1;
            mPointContainer.initPoints(gvPageSize);
            for (int i = 0; i < gvPageSize; i++) {
                //add gridview to list
                listGViews.add(getViewPagerItem(i));
            }
        }
    }

    @Override
    protected void initEvents() {

        mGridViewContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPointContainer.setPointSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 获取不同页的gridview
     *
     * @param index
     * @return
     */
    private View getViewPagerItem(int index) {
        LayoutInflater inflater = (LayoutInflater) CreateEventActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.viewpage_gridview, null);
        GridView gridView = (GridView) layout.findViewById(R.id.grid_view);

        gridView.setNumColumns(gvColumns);

        gridViewAdapter = new EventsSingleChoiceGridViewAdapter(this, mPresenter.getSportEvents(), index, pageItemCount);
        gridViewAdapter.setSelectedSportTypeHandler(mPresenter);

        gridView.setAdapter(gridViewAdapter);

        return gridView;
    }


    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public String getLocalisation() {
        return localisation.getText().toString();
    }

    @Override
    public String getEventName() {
        return eventName.getText().toString();
    }

    @Override
    public String getStartTime() {
        return startTime.getText().toString();
    }

    @Override
    public int getParticipatorNumber() {
        if (participatorNumber.getText().toString().isEmpty()) {
            return 0;
        }
        return Integer.valueOf(participatorNumber.getText().toString());
    }

    @Override
    public String getDescription() {
        return description.getText().toString();
    }

    @Override
    public void createFinished() {
        setResult(IntentConstants.CREATE_EVENT_SUCCESSED);
        finish();
    }


    @OnClick(R.id.event_confirm_btn)
    public void createSportEvent() {
        mPresenter.createSportEvent();
    }

}

