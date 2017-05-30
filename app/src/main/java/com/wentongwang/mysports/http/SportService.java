package com.wentongwang.mysports.http;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wwtco on 2017/5/30.
 */

public interface SportService {

    @POST("user/login")
    Observable<RetrofitResult> login(@Field("loginName") String loginName,
                                                     @Field("password") String passWord);

    @GET("home/sportevents")
    Observable<RetrofitResult> getHomePageSportEvents(@Query("user_like") String user_like);
}
