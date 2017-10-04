package com.wentongwang.mysports.views.activity.login;

import android.content.Context;
import android.text.TextUtils;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.base.BasePresenter;
import com.wentongwang.mysports.constant.Constant;
import com.wentongwang.mysports.http.InteractorCallback;
import com.wentongwang.mysports.http.interactor.UserInteractor;
import com.wentongwang.mysports.model.module.LoginResponse;
import com.wentongwang.mysports.utils.SharedPreferenceUtil;
import com.wentongwang.mysports.utils.ToastUtil;

/**
 * Created by Wentong WANG on 2016/9/8.
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    private UserInteractor userInteractor;


    /**
     * initial presenter
     * get Activity context
     * initial VolleyRequestManager
     *
     * @param context
     */
    @Override
    public void init(Context context) {
        super.init(context);
        userInteractor = new UserInteractor();
    }

    public void goToSignUp() {
        view.goToSignUp();
    }


    public void login(){
        final String userName = view.getUserName();
        final String userPwd = view.getUserPwd();

        if (TextUtils.isEmpty(userName)) {
            ToastUtil.show(mContext, mContext.getString(R.string.user_name_empty_alert), 1500);
            return;
        }

        if (TextUtils.isEmpty(userPwd)) {
            ToastUtil.show(mContext, mContext.getString(R.string.user_password_empty_alert), 1500);
            return;
        }

        view.showProgressBar();

        userInteractor.login(userName, userPwd, new InteractorCallback<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse result) {
                view.hideProgressBar();
                if (view.autoLoginSelected())
                    saveUserLoginInfo(userName, userPwd);
                else
                    clearUserLoginInfo();
                view.goToHomeActivity();
            }

            @Override
            public void onFailed(String error) {
                view.hideProgressBar();
                ToastUtil.show(mContext, mContext.getString(R.string.login_fail), 1500);
            }
        });

    }


    private void saveUserLoginInfo(String userName, String pwd) {
        SharedPreferenceUtil.put(mContext, Constant.USER_NAME, userName);
        SharedPreferenceUtil.put(mContext, Constant.USER_PASSWORD, pwd);
    }

    private void clearUserLoginInfo() {
        SharedPreferenceUtil.remove(mContext, Constant.USER_NAME);
        SharedPreferenceUtil.remove(mContext, Constant.USER_PASSWORD);
    }
}
