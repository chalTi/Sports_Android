package com.wangwentong.sports_api;

import com.wangwentong.sports_api.model.LoginResponse;
import com.wangwentong.sports_api.model.SportsFirstClass;
import com.wangwentong.sports_api.retrofit.RetrofitResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by wwtco on 2018/3/8.
 */

public interface Repository {

    @FormUrlEncoded
    @POST("user/login")
    Observable<RetrofitResponse<LoginResponse>> login(@Field("loginName") String loginName,
                                                      @Field("password") String passWord);

    @FormUrlEncoded
    @POST("user/register")
    Observable<RetrofitResponse<LoginResponse>> signUp( @Field("userJson") String userJson);

    @GET("user/checkuserexisted")
    Observable<RetrofitResponse> userNameExisted(@Query("userName") String userName);


    @GET("home/sportevents")
    Observable<RetrofitResponse<SportsFirstClass>> getHomePageSportEvents(@Query("user_like") String user_like);

}
