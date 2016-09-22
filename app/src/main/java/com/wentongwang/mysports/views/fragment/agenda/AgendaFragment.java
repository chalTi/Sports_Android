package com.wentongwang.mysports.views.fragment.agenda;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.custome.MyProgressBarHorizontal;
import com.wentongwang.mysports.views.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wentong WANG on 2016/9/17.
 */
public class AgendaFragment extends BaseFragment {

    @BindView(R.id.progress_bar)
    protected MyProgressBarHorizontal progressBarHorizontal;


    @Override
    public int getLayoutId() {
        return R.layout.agenda_fragment_layout;
    }

    @Override
    public void initDatas() {
        progressBarHorizontal.setProgress(40);
    }

    @Override
    public void initEvents() {

    }
}
