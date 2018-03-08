package com.wangwentong.sports_api.interactor;

import com.google.gson.Gson;
import com.wangwentong.sports_api.Repository;
import com.wangwentong.sports_api.model.LoginResponse;
import com.wangwentong.sports_api.retrofit.RetrofitManager;
import com.wangwentong.sports_api.retrofit.RetrofitResponse;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by wwtco on 2017/10/3.
 */

public class UserInteractor {

    public void login(String userName, String userPassWord, final InteractorCallback<LoginResponse> callback) {
        Repository userService = RetrofitManager.getRetrofit().create(Repository.class);


        Observable<RetrofitResponse<LoginResponse>> observable = userService.login(userName, userPassWord);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> callback.onSuccess(result.getResult(LoginResponse.class)),
                        e -> callback.onFailed(e.getMessage()));
    }

    public void signUpAndLogin(Map<String, String> userInfo, final InteractorCallback<LoginResponse> callback) {
        final Repository userService = RetrofitManager.getRetrofit().create(Repository.class);

        Observable.just(userInfo)
                .subscribeOn(Schedulers.io())
                .flatMap(user -> userService.userNameExisted(user.get("user_name")))
                .observeOn(AndroidSchedulers.mainThread())
                .map(result -> {
                    if (result.isSuccess()) {
                        return new Gson().toJson(userInfo);
                    } else {
                        callback.onFailed(result.getMessage());
                        return null;
                    }
                })
                .filter(str -> str != null)
                .observeOn(Schedulers.io())
                .flatMap(userService::signUp)
                .flatMap(new Function<RetrofitResponse<LoginResponse>,
                        ObservableSource<RetrofitResponse<LoginResponse>>>() {
                    @Override
                    public ObservableSource<RetrofitResponse<LoginResponse>>
                    apply(@NonNull RetrofitResponse<LoginResponse> retrofitResponse) throws Exception {

                            LoginResponse loginResponse = retrofitResponse.getResult(LoginResponse.class);

                            if (loginResponse != null) {
                                String userName = loginResponse.getUserName();
                                String userPwd = loginResponse.getUserPassword();

                                return userService.login(userName, userPwd);
                            } else {
                                return null;
                            }
                    }
                })
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> callback.onSuccess(result.getResult(LoginResponse.class)),
                        e -> callback.onFailed(e.getMessage()));

    }


}
