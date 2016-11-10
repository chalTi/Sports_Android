package com.wentongwang.mysports.utils;

import android.content.Context;
import android.widget.Toast;

import com.wentongwang.mysports.constant.ServerConstant;

import org.json.JSONObject;


public class AppServerUtil {

    /**
     * 访问服务器，返回结构为成功或失败时，调用的接口。 如果需要打印服务器给的toast提示，可以设置isNeedToastSuccess 为true
     *
     * @param context
     * @param resultData
     * @param isNeedToastSuccess
     * @return
     */
    public static boolean getIfSuccess(Context context, String resultData, boolean isNeedToastSuccess) {
        try {
            JSONObject jsonObj = new JSONObject(resultData);
            if (jsonObj.getString(ServerConstant.CODE).equals(ServerConstant.SUCCESS)) {

                if (isNeedToastSuccess) {
                    ToastUtil.show(context, jsonObj.getString(ServerConstant.MESSAGE), Toast.LENGTH_SHORT);
                }
                return true;
            } else {
                ToastUtil.show(context, jsonObj.getString(ServerConstant.MESSAGE), Toast.LENGTH_SHORT);
            }
        } catch (Exception e) {
            ToastUtil.show(context, "服务器数据格式有误", Toast.LENGTH_SHORT);
        }
        return false;
    }

    /**
     * 访问服务器，返回的数据是非列表形式时。
     *
     * @param context
     * @param resultData
     * @return
     */
    public static String getResultData(Context context, String resultData) {
        String result = null;
        try {
            JSONObject jsonObj = new JSONObject(resultData);
            if (jsonObj.getString(ServerConstant.CODE).equals(ServerConstant.SUCCESS)) {
                result = jsonObj.getString(ServerConstant.RESULT);
            } else {
                ToastUtil.show(context, jsonObj.getString(ServerConstant.MESSAGE), Toast.LENGTH_SHORT);
            }
        } catch (Exception e) {
            ToastUtil.show(context, "服务器数据格式有误", Toast.LENGTH_SHORT);
        }
        return result;
    }

    /**
     * 访问服务器，放回的数据是列表形式时。
     *
     * @param context
     * @param resultData
     * @return
     */
    public static String getResultArray(Context context, String resultData) {
        String result = null;
        try {
            JSONObject jsonObj = new JSONObject(resultData);
            if (jsonObj.getString(ServerConstant.CODE).equals(ServerConstant.SUCCESS)) {
                if (jsonObj.has(ServerConstant.JSONARRAY_SIZE)) {
                    String sizeString = jsonObj.getString(ServerConstant.JSONARRAY_SIZE).trim();
                    int arraySize = Integer.parseInt(sizeString);
                    if (arraySize > 0) {
                        result = jsonObj.getString(ServerConstant.RESULT);
                    }
                }
            } else {
                ToastUtil.show(context, jsonObj.getString(ServerConstant.MESSAGE), Toast.LENGTH_SHORT);
            }
        } catch (Exception e) {
            ToastUtil.show(context, "服务器数据格式有误", Toast.LENGTH_SHORT);
        }
        return result;
    }


}
