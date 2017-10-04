package com.wentongwang.mysports.http.interactor;

import com.wentongwang.mysports.http.InteractorCallback;
import com.wentongwang.mysports.http.RetrofitManager;
import com.wentongwang.mysports.http.RetrofitResult;
import com.wentongwang.mysports.http.services.UserService;
import com.wentongwang.mysports.model.module.LoginResponse;
import com.wentongwang.mysports.utils.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wwtco on 2017/10/3.
 */

public class UserInteractor {

    public void login(String userName, String userPassWord, final InteractorCallback<LoginResponse> callback) {
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
                        LoginResponse loginResponse = result.getObject(LoginResponse.class);
                        callback.onSuccess(loginResponse);
                    }
                });
    }

    public void signUp(String userJson, final InteractorCallback<LoginResponse> callback) {
        UserService userService = RetrofitManager.getRetrofit().create(UserService.class);

        Observable<RetrofitResult> observable = userService.signUp(userJson);
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
                        LoginResponse loginResponse = result.getObject(LoginResponse.class);
                        callback.onSuccess(loginResponse);
                    }
                });
    }



}
