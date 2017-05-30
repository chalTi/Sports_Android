package com.wentongwang.mysports;

import android.app.Application;

import com.wentongwang.mysports.http.RetrofitManager;
import com.wentongwang.mysports.model.bussiness.VolleyQueueManager;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Wentong WANG on 2016/9/16.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //init Retrofit
        RetrofitManager.init();

        VolleyQueueManager.init(this);
        // initalize Calligraphy
        CalligraphyConfig.initDefault(
                new CalligraphyConfig.Builder()
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
