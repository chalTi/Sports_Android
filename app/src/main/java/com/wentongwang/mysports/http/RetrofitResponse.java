package com.wentongwang.mysports.http;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.wentongwang.mysports.utils.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * 请求封装
 * Created by Wentong WANG on 2016/7/15.
 */
public class RetrofitResponse<T> {

    private String msg;

    /**
     * 获取数据
     * @param clazz 加上这个是为了防止泛型类型擦除问题
     * @return
     */
    public T getResult(Class<T> clazz) {
        return new Gson().fromJson(msg, clazz);
    }

    /**
     * 以List的形式获取一组数据
     * @param clazz 加上这个是为了防止泛型类型擦除问题
     * @return
     */
    public List<T> getResultArray(Class<T> clazz) {
        List<T> list =  new ArrayList<T>();
        JsonArray array = new JsonParser().parse(msg).getAsJsonArray();
        for(final JsonElement elem : array){
            list.add(new Gson().fromJson(elem, clazz));
        }
        return list;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
