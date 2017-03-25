package com.wentongwang.mysports.views.fragment.agenda;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.model.module.AgendaEvents;
import com.wentongwang.mysports.views.base.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Wentong WANG on 2016/9/17.
 */
public class AgendaFragment extends BaseFragment implements AgendaView {

    @BindView(R.id.root_view)
    protected View rootView;
    @BindView(R.id.agenda_events_list)
    protected ListView listView;

    private AgendaEventsAdapter adapter;

    private AgendaPresenter mPresenter = new AgendaPresenter(this);

    @Override
    public int getLayoutId() {
        return R.layout.agenda_fragment_layout;
    }

    @Override
    public void initDatas() {
        mPresenter.init(getActivity());
        adapter = new AgendaEventsAdapter();
        listView.setAdapter(adapter);
        mPresenter.getAgenda();
    }

    @Override
    public void initEvents() {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showPersonInfoPopupWindow(PopupWindow popupWindow) {
        popupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }

    @Override
    public void setBackGroundAlpha(float alpha) {
        WindowManager.LayoutParams windowlp = getActivity().getWindow().getAttributes();
        windowlp.alpha = alpha;
        getActivity().getWindow().setAttributes(windowlp);
    }

    @Override
    public void setAgendaList(List<AgendaEvents> list) {
        adapter.setItemList(list);
        adapter.notifyDataSetChanged();
    }


}
