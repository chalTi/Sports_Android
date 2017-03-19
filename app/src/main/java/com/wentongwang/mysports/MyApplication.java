package com.wentongwang.mysports;

import android.app.Application;

import com.wentongwang.mysports.model.bussiness.VolleyQueueManager;

/**
 * Created by Wentong WANG on 2016/9/16.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyQueueManager.init(this);
    }
}
