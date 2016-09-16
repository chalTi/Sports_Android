package com.wentongwang.mysports.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 管理网络请求队列的工具
 */
public class VolleyUtil {

    //私有化属性
    private static VolleyUtil singleQueue;
    private RequestQueue requestQueue;
    private static Context context;

    //私有化构造
    private VolleyUtil(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    //提供获得请求队列的方法
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    //提供获取类对象的方法
    public static synchronized VolleyUtil getInstance(Context context) {   //synchronized加锁防止并发
        if (singleQueue == null) {
            singleQueue = new VolleyUtil(context.getApplicationContext());
        }
        return singleQueue;
    }

//    public <T> void addToRequestQueue(Request<T> req) {
//        requestQueue.add(req);  //防止被回收造成空指针异常，所以一般不用
//        getRequestQueue().add(req);
//    }

}
