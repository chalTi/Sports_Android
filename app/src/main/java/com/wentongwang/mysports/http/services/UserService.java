package com.wentongwang.mysports.http.services;

import com.wentongwang.mysports.http.RetrofitResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wwtco on 2017/10/3.
 */

public interface UserService {

    @FormUrlEncoded
    @POST("user/login")
    Observable<RetrofitResult> login(@Field("loginName") String loginName,
                                     @Field("password") String passWord);

    @FormUrlEncoded
    @POST("user/register")
    Observable<RetrofitResult> signUp( @Field("userJson") String userJson);

    @GET("user/checkuserexisted")
    Observable<RetrofitResult> userNameExisted(@Query("userName") String userName);
}
