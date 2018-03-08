package com.wangwentong.sports_api.interactor;


import com.wangwentong.sports_api.Repository;
import com.wangwentong.sports_api.model.SportsFirstClass;
import com.wangwentong.sports_api.retrofit.RetrofitManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by wwtco on 2018/3/8.
 */

public class SportsInteractor {

    public void getSportEvents(String eventType, final InteractorCallback<List<SportsFirstClass>> callback){
        Repository sportService = RetrofitManager.getRetrofit().create(Repository.class);

        sportService.getHomePageSportEvents(eventType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> callback.onSuccess(result.getResultArray(SportsFirstClass.class)),
                error -> callback.onFailed(error.getMessage()));
    }
}
