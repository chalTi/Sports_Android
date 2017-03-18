package com.wentongwang.mysports.model.bussiness;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.wentongwang.mysports.utils.AppServerUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Wentong WANG on 2017/3/14.
 */
public class RxVolleyRequest {

    //    //该方法返回的是一个Observable，这种的是还需要进行在一步封装的，用泛型，这里我就不处理了的，也不会用的，给大家一个例子，也就是说我们完全可以把请求网络屏蔽了。
    public static Observable<String> getRequestObservable(final Context context, final int method, final String url, final Map<String, String> params) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    //stringRequest方法是用volley请示数据
                    subscriber.onNext(stringRequest(context, method, url, params));
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }

    private static String stringRequest(final Context context, int method, String url, final Map<String, String> params) {
        final StringWarrper result = new StringWarrper();
        final VollyStringRequest request = new VollyStringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String msg = AppServerUtil.getResultArray(context, response);
                if (!TextUtils.isEmpty(msg)) {
                    //获取数组型数据
                    result.setContent(msg);
                } else {
                    //获取普通型数据
                    msg = AppServerUtil.getResultData(context, response);
                    if (!TextUtils.isEmpty(msg)) {
                        result.setContent(msg);
                    } else {
                        //没有获取到任何数据
                        result.setContent("获取服务器数据为空");
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result.setContent(error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        VolleyQueueManager.getRequestQueue().add(request);
        return result.getContent();
    }

}
