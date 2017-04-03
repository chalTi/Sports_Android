package com.wentongwang.mysports.views.activity.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.base.BaseActivity;
import com.wentongwang.mysports.views.activity.home.HomeActivity;
import com.wentongwang.mysports.views.activity.login.LoginActivity;

import butterknife.BindView;

/**
 * Created by Wentong WANG on 2017/3/27.
 */
public class SplashActivity extends BaseActivity implements SplashView {

    @BindView(R.id.pb_splash_loading)
    protected ProgressBar pbLoading;
    @BindView(R.id.rl_splash_container)
    protected RelativeLayout rlContainer;
    private static final int SPLASH_DISPLAY_LENGTH = 3000;// 最小显示时间
    private static final int GO_LOGIN_ACTIVITY = 1000;
    private static final int GO_HOME_ACTIVITY = 1001;
    private Animation animation;
    private SplashPresenter mPresenter = new SplashPresenter();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_LOGIN_ACTIVITY:
                    goToLogin();
                    break;
                case GO_HOME_ACTIVITY:
                    goToHomeActivity();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected boolean noTitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash_layout;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setView(this);
        mPresenter.init(this);
    }

    @Override
    protected void initDatesAndViews() {
        mPresenter.init(this);
        animation= AnimationUtils.loadAnimation(SplashActivity.this,android.R.anim.fade_in);
        animation.setDuration(1500);
        rlContainer.startAnimation(animation);
    }

    @Override
    protected void initEvents() {
        if (!mPresenter.autoLogin()){
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pbLoading.setVisibility(View.VISIBLE);
                    mHandler.sendEmptyMessage(GO_LOGIN_ACTIVITY);
                }
            },SPLASH_DISPLAY_LENGTH);

        }else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pbLoading.setVisibility(View.GONE);
                    mHandler.sendEmptyMessage(GO_HOME_ACTIVITY);

                }
            },SPLASH_DISPLAY_LENGTH);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

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
        finish();
    }

    @Override
    public void goToHomeActivity() {
        Intent it = new Intent(this, HomeActivity.class);
        startActivity(it);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
