package com.wentongwang.mysports.views.fragment.friendrequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.NoScrollGridView;
import com.wentongwang.mysports.views.BaseFragment;

import butterknife.BindView;

/**
 * a fragment in the PersoninfoFragment
 * Created by Wentong WANG on 2016/9/29.
 */
public class FriendRequestFragment extends BaseFragment {

    @BindView(R.id.grid_view)
    protected NoScrollGridView gridView;

    private MyGridViewAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.friend_request_fragment_layout;
    }

    @Override
    public void initDatas() {
        adapter = new MyGridViewAdapter();
        gridView.setAdapter(adapter);
    }

    @Override
    public void initEvents() {

    }

    class MyGridViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 14;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_friend_request_gridview, null);
                viewHolder = new ViewHolder(convertView);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }


            return convertView;
        }

        class ViewHolder{


            public ViewHolder(View view){

            }

        }
    }

}
