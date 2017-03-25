package com.wentongwang.mysports.views.fragment.friendrequest;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.views.custome.NoScrollGridView;
import com.wentongwang.mysports.views.base.BaseFragment;

import butterknife.BindView;

/**
 * a fragment in the PersoninfoFragment
 * Created by Wentong WANG on 2016/9/29.
 */
public class FriendRequestFragment extends BaseFragment {

    @BindView(R.id.grid_view)
    protected NoScrollGridView gridView;

    private GridViewAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.friend_request_fragment_layout;
    }

    @Override
    public void initDatas() {
        adapter = new GridViewAdapter();
        gridView.setAdapter(adapter);
    }

    @Override
    public void initEvents() {

    }


}
