package com.wentongwang.mysports.views.fragment.friendrequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.NoScrollGridView;
import com.wentongwang.mysports.base.BaseFragment;

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
    public void initDates() {
        adapter = new GridViewAdapter();
        gridView.setAdapter(adapter);
    }

    @Override
    public void initEvents() {

    }


}
