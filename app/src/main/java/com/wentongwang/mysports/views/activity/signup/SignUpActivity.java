package com.wentongwang.mysports.views.activity.signup;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.views.BaseActivity;
import com.wentongwang.mysports.views.activity.home.HomeActivity;

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
    @BindView(R.id.btn_sign_in)
    protected Button btnSignUp;


    private int userSex = 0; //0 = male; 1 = female
    private SignUpPresenter mPresenter = new SignUpPresenter(this);

    @Override
    protected boolean notitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sginup_layout;
    }

    @Override
    protected void initDatasAndViews() {
        ButterKnife.bind(SignUpActivity.this);
        mPresenter.init(SignUpActivity.this);
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

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.signUp();
            }
        });
    }


    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public String getUserName() {
        return userName.getText().toString();
    }

    @Override
    public String getUserPwd() {
        return userPwd.getText().toString();
    }

    @Override
    public String getUserPwd2() {
        return userPwd2.getText().toString();
    }

    @Override
    public String getUserEmail() {
        return userEmail.getText().toString();
    }

    @Override
    public String getUserSex() {
        return Integer.toString(userSex);
    }

    @Override
    public void goToHomeActivity() {
        Intent it = new Intent();
        it.setClass(SignUpActivity.this, HomeActivity.class);
        startActivity(it);
    }
}
