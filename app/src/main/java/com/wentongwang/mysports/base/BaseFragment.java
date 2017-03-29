package com.wentongwang.mysports.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Wentong WANG on 2016/8/18.
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    private View root;
    /**
     * 设置Activity的layout
     * @return layout的id
     */
    public abstract int getLayoutId();

    /**
     * 初始化数据
     */
    public abstract void initDates();

    /**
     * 初始化控件的事件
     */
    public abstract void initEvents();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, root);
        initDates();
        initEvents();
        return root;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
