package com.wentongwang.mysports.views.activity.signup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.google.gson.Gson;
import com.wentongwang.mysports.R;
import com.wentongwang.mysports.constant.Constant;
import com.wentongwang.mysports.model.bussiness.VollyRequestManager;
import com.wentongwang.mysports.model.bussiness.VolleyResponse;
import com.wentongwang.mysports.model.module.LoginResponse;
import com.wentongwang.mysports.utils.SharedPreferenceUtil;
import com.wentongwang.mysports.utils.ToastUtil;
import com.wentongwang.mysports.utils.VolleyUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Wentong WANG on 2016/9/8.
 */
public class SignUpPresenter {
    private SignUpView view;
    private Context mContext;
    private VollyRequestManager vollyRequestManager;
    private PopupWindow popupWindow;

    public SignUpPresenter(SignUpView view){
        this.view = view;
    }

    public void init(Context context) {
        mContext = context;
        vollyRequestManager = new VollyRequestManager(VolleyUtil.getInstance(mContext).getRequestQueue());
    }

    public void signUp(){
        String userName = view.getUserName();
        String userPwd = view.getUserPwd();
        String userPwd2 = view.getUserPwd2();
        String userEmail = view.getUserEmail();
        String userSex = view.getUserSex();

        if (TextUtils.isEmpty(userName)) {
            ToastUtil.show(mContext, "用户名不能为空", 1500);
            return;
        }

        if (TextUtils.isEmpty(userPwd)) {
            ToastUtil.show(mContext, "密码不能为空", 1500);
            return;
        }

        if (TextUtils.isEmpty(userPwd2)) {
            ToastUtil.show(mContext, "二次密码不能为空", 1500);
            return;
        }

        if (!userPwd.equals(userPwd2)) {
            ToastUtil.show(mContext, "两次密码输入不同", 1500);
            return;
        }

        if (userPwd.equals(userEmail)) {
            ToastUtil.show(mContext, "邮箱不能为空", 1500);
            return;
        }

        String url = Constant.HOST + Constant.SIGN_UP_PATH;
        Map<String, String> map = new HashMap<>();

        map.put("user_name", userName);
        map.put("user_password", userPwd);
        map.put("user_email", userEmail);
        map.put("user_sex", userSex);
        map.put("user_imageURL", "");
        String userInfo = new Gson().toJson(map);


        Map<String, String> params = new HashMap<>();
        params.put("userJson", userInfo);


        view.showProgressBar();
        vollyRequestManager.doPost(mContext, url, params, new VollyRequestManager.OnRequestFinishedListener() {
            @Override
            public void onSuccess(VolleyResponse response) {
                view.hideProgressBar();
                //存储用户登录信息，cookie之类的
                login();
            }

            @Override
            public void onFailed(String msg) {
                view.hideProgressBar();
                ToastUtil.show(mContext, msg, 1500);
            }
        });

    }

    /**
     * 注册成功的自动登录
     */
    private void login() {
        String userName = view.getUserName();
        String userPwd = view.getUserPwd();
        String url = Constant.HOST + Constant.LOGIN_PATH;

        VolleyResponse<LoginResponse> loginResponse = new VolleyResponse<>();

        Map<String, String> params = new HashMap<>();
        params.put("loginName", userName);
        params.put("password", userPwd);

        view.showProgressBar();
        vollyRequestManager.doPost(mContext, url, loginResponse, params, new VollyRequestManager.OnRequestFinishedListener() {
            @Override
            public void onSuccess(VolleyResponse response) {
                view.hideProgressBar();
                //存储用户登录信息，cookie之类的
                LoginResponse result = (LoginResponse) response.getResult(LoginResponse.class);
                SharedPreferenceUtil.put(mContext, "user_base_info", result);
                view.goToHomeActivity();
            }

            @Override
            public void onFailed(String msg) {
                view.hideProgressBar();
                ToastUtil.show(mContext, msg, 1500);
            }
        });

    }


    public void popupChoseWindow(){
        //获取数据，通过构造函数塞到popupWindow里
        popupWindow = new PopupWindow(view.initPopupView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view.setBackGroundAlpha(1.0f);
            }
        });
        view.showPopupWindow(popupWindow);
        view.setBackGroundAlpha(0.55f);

    }

}
