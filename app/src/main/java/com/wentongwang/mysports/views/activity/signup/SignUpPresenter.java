package com.wentongwang.mysports.views.activity.signup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.base.BasePresenter;
import com.wentongwang.mysports.http.InteractorCallback;
import com.wentongwang.mysports.http.interactor.UserInteractor;
import com.wentongwang.mysports.model.module.LoginResponse;
import com.wentongwang.mysports.utils.SharedPreferenceUtil;
import com.wentongwang.mysports.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Wentong WANG on 2016/9/8.
 */
public class SignUpPresenter extends BasePresenter<SignUpView>{

    private UserInteractor userInteractor;
    private PopupWindow popupWindow;

    @Override
    public void init(Context context) {
        super.init(context);

        userInteractor = new UserInteractor();
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

        Map<String, String> map = new HashMap<>();

        map.put("user_name", userName);
        map.put("user_password", userPwd);
        map.put("user_email", userEmail);
        map.put("user_sex", userSex);
        map.put("user_imageURL", "");

        view.showProgressBar();

        userInteractor.signUpAndLogin(map, new InteractorCallback<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse result) {
                view.hideProgressBar();

                SharedPreferenceUtil.put(mContext, "user_base_info", result);
                view.goToHomeActivity();
            }

            @Override
            public void onFailed(String error) {
                view.hideProgressBar();
                ToastUtil.show(mContext, error, 1500);
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
        popupWindow.setOnDismissListener(() -> view.setBackGroundAlpha(1.0f));
        view.showPopupWindow(popupWindow);
        view.setBackGroundAlpha(0.55f);

    }

}
