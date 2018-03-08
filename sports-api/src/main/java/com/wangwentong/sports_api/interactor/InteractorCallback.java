package com.wangwentong.sports_api.interactor;

/**
 * Created by wwtco on 2017/10/3.
 */

public interface InteractorCallback<T> {
    void onSuccess(T result);
    void onFailed(String error);
}
