package com.wangwentong.sports_api.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wangwentong.sports_api.ServerConstant;
import com.wangwentong.sports_api.utils.Logger;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wwtco on 2017/5/30.
 */
public class RetrofitManager {
    private static Retrofit retrofit;

    public static void init(){

        OkHttpClient httpClient;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(ServerConstant.LOCAL_HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();
    }

    public static Retrofit getRetrofit(){
        if (retrofit == null) {
            Logger.e("RetrofitManager", "do not initial retrofit!!!!!!");
        }
        return retrofit;
    }

}
