package com.wentongwang.mysports.views.activity.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.CommonHeadView;
import com.wentongwang.mysports.utils.ToastUtil;
import com.wentongwang.mysports.views.BaseActivity;
import com.wentongwang.mysports.views.activity.createvent.CreatEventActivity;
import com.wentongwang.mysports.views.fragment.agenda.AgendaFragment;
import com.wentongwang.mysports.views.fragment.home.HomeFragment;
import com.wentongwang.mysports.views.fragment.news.NewsFragment;
import com.wentongwang.mysports.views.fragment.personinfo.PersonInfoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wentong WANG on 2016/9/16.
 */
public class HomeActivity extends BaseActivity implements HomeView {
    @BindView(R.id.root_view)
    protected View rootView;
    @BindView(R.id.vp_home_activity_content)
    protected ViewPager mViewPager;
    //bottom tab layout
    @BindView(R.id.rg_home_bottom_tab)
    protected RadioGroup mRg;
    //toolbar
    @BindView(R.id.tool_bar)
    protected CommonHeadView mToolbar;
    @BindView(R.id.btn_creat_event)
    protected ImageView creatEventBtn;

    private List<Fragment> mFragmentList;
    private MyFragmentPagerAdapter mvpAdapter; //adapter of fragment

    private HomePresenter mPresenter = new HomePresenter(this);
    private int userType = 1;

    @Override
    protected boolean notitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_layout;
    }

    @Override
    protected void initDatasAndViews() {
        ButterKnife.bind(HomeActivity.this);

        initFragments();

        FragmentManager manager = getSupportFragmentManager();
        mvpAdapter = new MyFragmentPagerAdapter(manager, mFragmentList);
        mViewPager.setAdapter(mvpAdapter);

        mPresenter.setPage(0);
    }

    private void initFragments() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new AgendaFragment());
        mFragmentList.add(new NewsFragment());
        mFragmentList.add(new PersonInfoFragment());
    }

    @Override
    protected void initEvents() {
        creatEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userType == 0) {
                    mPresenter.toCreatEvent();
                } else if (userType == 1) {
                    mPresenter.popupChoseWindow();
                } else {
                    ToastUtil.show(HomeActivity.this, "登录异常", 2000);
                }

            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mPresenter.toolBarAnim(position);
            }

            @Override
            public void onPageSelected(int position) {
                mPresenter.setPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.btn_calendar:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.btn_news:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.btn_preson:
                        mViewPager.setCurrentItem(3);
                        break;
                }
            }
        });
    }


    /**
     * execute hide toolbar animation
     */
    @Override
    public void hiddenToolBarAnim() {
        AlphaAnimation animation = new AlphaAnimation(1, 0);
        animation.setDuration(200);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mPresenter.setToolBarVisible(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mToolbar.setAnimation(animation);
        animation.start();
    }

    /**
     * execute show toolbar animation
     */
    @Override
    public void showToolBarAnim() {
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(200);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mPresenter.setToolBarVisible(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mToolbar.setAnimation(animation);
        animation.start();
    }

    /**
     * make right Btn visible or not
     *
     * @param show
     */
    @Override
    public void setToolBarRightBtnVisible(boolean show) {
        mToolbar.setRightImageShow(show);
    }

    /**
     * get which tab btn is checked
     *
     * @param position
     * @return
     */
    @Override
    public RadioButton getCheckedBtn(int position) {
        return (RadioButton) mRg.getChildAt(position);
    }

    @Override
    public void goToCreatEventActivity() {
        Intent it = new Intent();
        it.setClass(HomeActivity.this, CreatEventActivity.class);
        startActivity(it);
    }

    @Override
    public View initPopupView() {
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_chose_layout, null);

        Button eventBtn = (Button) popupView.findViewById(R.id.btn_creat_event);
        Button postBtn = (Button) popupView.findViewById(R.id.btn_creat_post);

        eventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.toCreatEvent();
            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(HomeActivity.this, "跳转到Post界面", 1500);
            }
        });
        return popupView;
    }

    @Override
    public void showPopupWindow(PopupWindow popupWindow) {
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void setBackGroundAlpha(float alpha) {
        WindowManager.LayoutParams windowlp = HomeActivity.this.getWindow().getAttributes();
        windowlp.alpha = alpha;
        HomeActivity.this.getWindow().setAttributes(windowlp);
    }

    @Override
    public Context getContext() {
        return HomeActivity.this;
    }


    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mPresenter.keyBackAction();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }


    }
}
