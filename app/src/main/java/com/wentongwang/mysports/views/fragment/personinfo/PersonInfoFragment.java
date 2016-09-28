package com.wentongwang.mysports.views.fragment.personinfo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ListView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.MyTabLayout;
import com.wentongwang.mysports.views.BaseFragment;
import com.wentongwang.mysports.views.fragment.friendrequest.FriendRequestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Wentong WANG on 2016/9/17.
 */
public class PersonInfoFragment extends BaseFragment implements PersonInfoView {

    @BindView(R.id.view_pager)
    protected ViewPager viewPager;
    @BindView(R.id.tab_layout)
    protected MyTabLayout myTabLayout;

    private FragmentPagerAdapter adapter;
    private List<String> tabTitles;

    private List<Fragment> fragments;

    @Override
    public int getLayoutId() {
        return R.layout.personinfo_fragment_layout;
    }

    @Override
    public void initDatas() {
        tabTitles = new ArrayList<>();
        tabTitles.add("tab 1");
        tabTitles.add("tab 2");
        tabTitles.add("tab 3");
        myTabLayout.setTabItems(tabTitles);
        myTabLayout.heightLightTextView(0);

        fragments = new ArrayList<>();
        fragments.add(new FriendRequestFragment());
        fragments.add(new FriendRequestFragment());
        fragments.add(new FriendRequestFragment());

        adapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };

        viewPager.setAdapter(adapter);
    }

    @Override
    public void initEvents() {

        myTabLayout.setOnTabSelectedListener(new MyTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position);
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                myTabLayout.scroll(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                myTabLayout.heightLightTextView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
