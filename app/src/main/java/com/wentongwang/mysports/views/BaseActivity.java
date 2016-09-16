package com.wentongwang.mysports.views;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

/**
 * Created by Wentong WANG on 2016/8/18.
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (notitle()) {
            setNoTitle();
        }
        setContentView(getLayoutId());
        initDatasAndViews();
        initEvents();
    }

    protected abstract boolean notitle();

    protected void setNoTitle(){
        //设置无标题栏和状态栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }


    /**
     * 设置Activity的layout
     * @return layout的id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initDatasAndViews();

    /**
     * 初始化控件的事件
     */
    protected abstract void initEvents();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
