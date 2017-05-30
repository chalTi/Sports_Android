package com.wentongwang.mysports.http;

import java.util.List;

/**
 * Created by wwtco on 2017/5/30.
 */

public class RetrofitResult {
    String message;
    String code;
    String result;

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String getResult() {
        return result;
    }

    public <T> T getObject(Class<T> clazz){
        RetrofitResponse<T> response = new RetrofitResponse<>();
        response.setMsg(result);
        return response.getResult(clazz);
    }

    public <T> List<T> getObjectList(Class<T> clazz){
        RetrofitResponse<T> response = new RetrofitResponse<>();
        response.setMsg(result);
        return response.getResultArray(clazz);
    }

}
