package com.wentongwang.mysports.http.interactor;

import com.wentongwang.mysports.http.InteractorCallback;
import com.wentongwang.mysports.http.RetrofitManager;
import com.wentongwang.mysports.http.RetrofitResult;
import com.wentongwang.mysports.http.services.UserService;
import com.wentongwang.mysports.model.module.UserInfo;
import com.wentongwang.mysports.utils.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wwtco on 2017/10/3.
 */

public class UserInteractor {

    public void login(String userName, String userPassWord, final InteractorCallback<UserInfo> callback) {
        UserService userService = RetrofitManager.getRetrofit().create(UserService.class);

        Observable<RetrofitResult> observable = userService.login(userName, userPassWord);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.immediate())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RetrofitResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        callback.onFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(RetrofitResult result) {
                        UserInfo userInfo = result.getObject(UserInfo.class);
                        callback.onSuccess(userInfo);
                    }
                });
    }


}
