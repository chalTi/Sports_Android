package com.wentongwang.mysports.views.activity.newsdetail;



import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.CommonHeadView;
import com.wentongwang.mysports.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qifan on 2016/10/18.
 */

public class NewsDetailActivity extends BaseActivity implements NewsDetailView {
    //Top toolbar
    @BindView(R.id.tool_bar)
    protected CommonHeadView mToolbar;

    private boolean isSelected = false;

    @Override
    protected boolean notitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_newsdetail_layout;
    }

    @Override
    protected void initDatasAndViews() {

        if (isSelected) {
            mToolbar.setImageRight(R.drawable.liked);
        } else {
            mToolbar.setImageRight(R.drawable.disliked);
        }

    }

    @Override
    protected void initEvents() {
        ButterKnife.bind(NewsDetailActivity.this);
        mToolbar.setCallbck(new CommonHeadView.CALLBACK(){

            @Override
            public void onLeftClick() {
                onBackPressed();
            }

            @Override
            public void onRightClick() {
                if (isSelected) {
                    mToolbar.setImageRight(R.drawable.disliked);
                    isSelected = false;
                } else {
                    mToolbar.setImageRight(R.drawable.liked);
                    isSelected = true;
                }


            }

            @Override
            public void onCenterClick() {

            }
        });
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
