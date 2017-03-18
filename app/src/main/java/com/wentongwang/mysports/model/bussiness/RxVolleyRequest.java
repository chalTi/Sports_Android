package com.wentongwang.mysports.model.bussiness;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.wentongwang.mysports.utils.AppServerUtil;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Wentong WANG on 2017/3/14.
 */
public class RxVolleyRequest<T> {

    private static RxVolleyRequest instance;

    private RxVolleyRequest(){

    }

    public static RxVolleyRequest getInstance(){
        if (instance == null) {
            instance = new RxVolleyRequest();
        }
        return instance;
    }

    public Observable<VolleyResponse<T>> getRequestObservable(final Context context, final int method, final String url, final Map<String, String> params) {
        return Observable.create(new Observable.OnSubscribe<VolleyResponse<T>>() {
            @Override
            public void call(Subscriber<? super VolleyResponse<T>> subscriber) {
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

    private VolleyResponse<T> stringRequest(final Context context, int method, String url, final Map<String, String> params) {
        final VolleyResponse<T> result = new VolleyResponse<>();
        final VolleyStringRequest request = new VolleyStringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String msg = AppServerUtil.getResultArray(context, response);
                if (!TextUtils.isEmpty(msg)) {
                    //获取数组型数据
                    result.setMsg(msg);
                } else {
                    //获取普通型数据
                    msg = AppServerUtil.getResultData(context, response);
                    if (!TextUtils.isEmpty(msg)) {
                        result.setMsg(msg);
                    } else {
                        //没有获取到任何数据
                        result.setMsg("获取服务器数据为空");
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result.setMsg(error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        VolleyQueueManager.getRequestQueue().add(request);
        return result;
    }

}
