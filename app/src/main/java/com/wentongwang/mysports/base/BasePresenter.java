package com.wentongwang.mysports.base;

import android.content.Context;

/**
 * Created by Wentong WANG on 2017/4/2.
 */

public abstract class BasePresenter<View> {

    protected View view;
    protected Context mContext;

    /**
     * set view for presenter
     * @param view
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * initial Presenter，
     * if you want to customer initial, you can Override this function
     * @param context
     */
    public void init(Context context) {
        this.mContext = context;
    }
}
