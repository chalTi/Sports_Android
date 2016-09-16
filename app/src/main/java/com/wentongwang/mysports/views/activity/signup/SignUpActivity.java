package com.wentongwang.mysports.views.activity.signup;

import android.content.Context;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.views.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wentong WANG on 2016/9/8.
 */
public class SignUpActivity extends BaseActivity implements SignUpView {

    @BindView(R.id.et_sign_up_username)
    protected EditText userName;
    @BindView(R.id.et_sign_up_pwd)
    protected EditText userPwd;
    @BindView(R.id.et_sign_up_pwd_confirm)
    protected EditText userPwd2;
    @BindView(R.id.et_sign_up_email)
    protected EditText userEmail;
    @BindView(R.id.rg_sign_up_select_sex)
    protected RadioGroup mRadioGroup;


    private int userSex = 0; //0 = male; 1 = female

    @Override
    protected boolean notitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.signup_activity_layout;
    }

    @Override
    protected void initDatasAndViews() {
        ButterKnife.bind(SignUpActivity.this);
    }

    @Override
    protected void initEvents() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_male) {
                    userSex = 0;
                } else {
                    userSex = 1;
                }
            }
        });
    }



    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
