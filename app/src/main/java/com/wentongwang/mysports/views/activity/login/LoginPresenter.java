package com.wentongwang.mysports.views.activity.login;

import android.content.Context;
import android.text.TextUtils;

import com.wentongwang.mysports.constant.Constant;
import com.wentongwang.mysports.model.bussiness.VollyResponse;
import com.wentongwang.mysports.utils.ToastUtil;
import com.wentongwang.mysports.utils.VolleyUtil;
import com.wentongwang.mysports.model.bussiness.VollyRequestManager;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by Wentong WANG on 2016/9/8.
 */
public class LoginPresenter {

    private LoginView view;
    private Context mContext;
    private VollyRequestManager vollyRequestManager;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    /**
     * initial presenter
     * get Activity context
     * initial VolleyRequestManager
     * @param context
     */
    public void init(Context context) {
        this.mContext = context;
        vollyRequestManager = new VollyRequestManager(VolleyUtil.getInstance(mContext).getRequestQueue());
    }

    public void goToSignUp() {
        view.goToSignUp();
    }

    public void login() {


        String userName = view.getUserName();
        String userPwd = view.getUserPwd();

        if (TextUtils.isEmpty(userName)) {
            ToastUtil.show(mContext, "用户名不能为空", 1500);
            return;
        }

        if (TextUtils.isEmpty(userPwd)) {
            ToastUtil.show(mContext, "密码不能为空", 1500);
            return;
        }


        String url = Constant.HOST + Constant.LOGIN_PATH;

        Map<String, String> params = new HashMap<>();
        params.put("loginName", "admin");
        params.put("password", "123456");

        view.showProgressBar();
        vollyRequestManager.doPost(mContext, url, params, new VollyRequestManager.OnRequestFinishedListener() {
            @Override
            public void onSucess(VollyResponse response) {
                view.hideProgressBar();
                //跳转操作
                ToastUtil.show(mContext, "登录成功", 2000);
                //存储用户登录信息，cookie之类的
                view.goToHomeActivity();
            }

            @Override
            public void onFailed(String msg) {
                view.hideProgressBar();
                ToastUtil.show(mContext, msg, 1500);
            }
        });

    }

}
