package com.wentongwang.mysports.views.activity.splash;

import android.content.Intent;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.base.BaseActivity;
import com.wentongwang.mysports.views.activity.home.HomeActivity;
import com.wentongwang.mysports.views.activity.login.LoginActivity;

/**
 * Created by Wentong WANG on 2017/3/27.
 */
public class SplashActivity extends BaseActivity implements SplashView{

    private SplashPresenter mPresenter = new SplashPresenter(this);

    @Override
    protected boolean notitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash_layout;
    }

    @Override
    protected void initDatasAndViews() {
        mPresenter.init(this);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.autoLogin();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void goToLogin() {
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
    }

    @Override
    public void goToHome() {
        Intent it = new Intent(this, HomeActivity.class);
        startActivity(it);
    }
}
