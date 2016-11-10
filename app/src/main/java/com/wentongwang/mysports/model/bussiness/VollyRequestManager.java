package com.wentongwang.mysports.model.bussiness;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.wentongwang.mysports.utils.AppServerUtil;
import com.wentongwang.mysports.utils.Logger;


import java.util.HashMap;
import java.util.Map;


/**
 * model层中的网络请求的业务逻辑
 * Created by Wentong WANG on 2016/7/15.
 */
public class VollyRequestManager {

    // 网络请求的volley队列
    private RequestQueue queue;

    public VollyRequestManager(RequestQueue queue) {
        this.queue = queue;
    }


    /**
     * get请求
     * @param context
     * @param url
     * @param listener
     */
    public void doGet(final Context context, String url, final OnRequestFinishedListener listener) {
        VollyStringRequest request = new VollyStringRequest(Request.Method.POST,
                url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Boolean status = AppServerUtil.getIfSuccess(context, response, true);
                if (status) {
                    if (listener != null) {
                        listener.onSucess(null);
                    }
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (listener != null) {
                    listener.onFailed("获取服务器数据失败, 请检查网络");
                }
                return;
            }
        });
        queue.add(request);
    }



    /**
     * post请求,无数据返回，只判断是否成功
     *
     * @param context  上下文
     * @param url      url
     * @param params   请求参数
     * @param listener 回调
     */
    public void doPost(final Context context, String url, final Map<String, String> params,
                       final OnRequestFinishedListener listener) {
        VollyStringRequest request = new VollyStringRequest(Request.Method.POST,
                url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Boolean status = AppServerUtil.getIfSuccess(context, response, true);
                if (status) {
                    if (listener != null) {
                        listener.onSucess(null);
                    }
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (listener != null) {
                    listener.onFailed("获取服务器数据失败, 请检查网络");
                }
                return;
            }
        }) {
            // post参数
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }

        };
        queue.add(request);
    }


    /**
     * post请求
     *
     * @param context  上下文
     * @param url      url
     * @param result   需要解析成的结果的泛型
     * @param params   请求参数
     * @param listener 回调
     */
    public void doPost(final Context context, String url,
                       final VollyResponse result, final Map<String, String> params,
                       final OnRequestFinishedListener listener) {
        VollyStringRequest request = new VollyStringRequest(Request.Method.POST,
                url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String msg = AppServerUtil.getResultArray(context, response);
                if (!TextUtils.isEmpty(msg)) {
                    //获取数组型数据
                    result.setMsg(msg);
                    if (listener != null) {
                        listener.onSucess(result);
                    }
                } else {
                    //获取普通型数据
                    msg = AppServerUtil.getResultData(context, response);
                    if (!TextUtils.isEmpty(msg)) {
                        result.setMsg(msg);
                        if (listener != null) {
                            listener.onSucess(result);
                        }
                    } else {
                        //没有获取到任何数据
                        if (listener != null) {
                            listener.onFailed("获取服务器数据为空");
                        }
                    }
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (listener != null) {
                    listener.onFailed("获取服务器数据失败, 请检查网络");
                }
                return;
            }
        }) {
            // post参数
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }

        };
        queue.add(request);
    }

    /**
     * 带cookies的post请求
     *
     * @param context
     * @param url
     * @param result
     * @param params
     * @param cookies
     * @param listener
     */
    public void doPost(final Context context, String url,
                       final VollyResponse result, final Map<String, String> params, final String cookies,
                       final OnRequestFinishedListener listener) {
        VollyStringRequest request = new VollyStringRequest(Request.Method.POST,
                url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String msg = AppServerUtil.getResultArray(context, response);
                if (!TextUtils.isEmpty(msg)) {
                    result.setMsg(msg);
                    if (listener != null) {
                        listener.onSucess(result);
                    }
                } else {
                    msg = AppServerUtil.getResultData(context, response);
                    if (!TextUtils.isEmpty(msg)) {
                        result.setMsg(msg);
                        if (listener != null) {
                            listener.onSucess(result);
                        }
                    } else {
                        if (listener != null) {
                            listener.onFailed("获取服务器数据为空");
                        }
                    }
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (listener != null) {
                    listener.onFailed("获取服务器数据失败, 请检查网络");
                }
                return;
            }
        }) {
            // post参数
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                if (!TextUtils.isEmpty(cookies))
                    map.put("Cookie", cookies.split(";")[0]);
                return map;
            }

        };
        queue.add(request);
    }


    public interface OnRequestFinishedListener {
        void onSucess(VollyResponse response);

        void onFailed(String msg);
    }
}
