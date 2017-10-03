package com.wentongwang.mysports.http.services;

import com.wentongwang.mysports.http.RetrofitResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wwtco on 2017/5/30.
 */

public interface SportService {

    @GET("home/sportevents")
    Observable<RetrofitResult> getHomePageSportEvents(@Query("user_like") String user_like);
}
