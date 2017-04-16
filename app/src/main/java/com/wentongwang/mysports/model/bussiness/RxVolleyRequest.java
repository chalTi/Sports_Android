package com.wentongwang.mysports.model.bussiness;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.wentongwang.mysports.utils.AppServerUtil;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Wentong WANG on 2017/3/14.
 */
public class RxVolleyRequest {

    private static RxVolleyRequest instance;

    private RxVolleyRequest(){

    }

    public static RxVolleyRequest getInstance(){
        if (instance == null) {
            instance = new RxVolleyRequest();
        }
        return instance;
    }
    /**
     * 有返回数据的请求
     *
     * @param context  上下文
     * @param url      url
     * @param params   请求参数
     */
    public Observable<String> resourceRequestObservable(final Context context, final int method, final String url, final Map<String, String> params) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                try {
                    //stringRequest方法是用volley请示数据
                    VolleyStringRequest request = new VolleyStringRequest(method, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String msg = AppServerUtil.getResultArray(context, response);
                            if (!TextUtils.isEmpty(msg)) {
                                //获取数组型数据
                                subscriber.onNext(msg);
                                subscriber.onCompleted();
                            } else {
                                //获取普通型数据
                                msg = AppServerUtil.getResultData(context, response);
                                if (!TextUtils.isEmpty(msg)) {
                                    subscriber.onNext(msg);
                                    subscriber.onCompleted();
                                } else {
                                    //没有获取到任何数据
                                    Exception error = new Exception("获取服务器数据为空");
                                    subscriber.onError(error);
                                }
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            subscriber.onError(error);
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            return params;
                        }
                    };
                    VolleyQueueManager.getRequestQueue().add(request);
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }

    /**
     * 请求无数据返回，只判断是否成功
     *
     * @param context  上下文
     * @param url      url
     * @param params   请求参数
     */
    public Observable<String> operationRequestObservable(final Context context, final int method, final String url, final Map<String, String> params) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                try {
                    //stringRequest方法是用volley请示数据
                    VolleyStringRequest request = new VolleyStringRequest(method, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Boolean status = AppServerUtil.getIfSuccess(context, response, true);
                            if (status) {
                                subscriber.onCompleted();
                            }else{
                                Exception error = new Exception("获取服务器数据为空");
                                subscriber.onError(error);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            subscriber.onError(error);
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            return params;
                        }
                    };
                    VolleyQueueManager.getRequestQueue().add(request);
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }


}
