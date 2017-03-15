package com.wentongwang.mysports.model.bussiness;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Wentong WANG on 2017/3/15.
 */
public class VolleyQueueManager {
    private static RequestQueue mRequestQueue;

    private VolleyQueueManager() {
        // no instances
    }

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("Not initialized");
        }
    }
}
