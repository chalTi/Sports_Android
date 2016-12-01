package com.wentongwang.mysports.views.fragment.agenda;

import android.content.Context;
import android.util.Log;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.wentongwang.mysports.constant.Constant;
import com.wentongwang.mysports.custome.PersonInfoPopupWindow;
import com.wentongwang.mysports.model.bussiness.VollyRequestManager;
import com.wentongwang.mysports.model.bussiness.VollyResponse;
import com.wentongwang.mysports.model.module.AgendaEvents;
import com.wentongwang.mysports.utils.Logger;
import com.wentongwang.mysports.utils.ToastUtil;
import com.wentongwang.mysports.utils.VolleyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wentong WANG on 2016/9/26.
 */
public class AgendaPresenter {

    private AgendaView view;
    private Context mContext;
    private VollyRequestManager vollyRequestManager;

    public AgendaPresenter(AgendaView view) {
        this.view = view;
    }

    /**
     * initial presenter
     * get Activity context
     * initial VolleyRequestManager
     *
     * @param context
     */
    public void init(Context context) {
        this.mContext = context;
        vollyRequestManager = new VollyRequestManager(VolleyUtil.getInstance(mContext).getRequestQueue());
    }

    public void showPopupWindow() {
        //获取数据，通过构造函数塞到popupWindow里
        final PersonInfoPopupWindow popupWindow = new PersonInfoPopupWindow(mContext);


        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view.setBackGroundAlpha(1f);

            }
        });

        view.showPersonInfoPopupWindow(popupWindow);
        view.setBackGroundAlpha(0.55f);
    }
    //*********************************************************
    public void getAgenda(){
        String url="http://192.168.1.25:8080/sports"+ Constant.GET_AGENDA_PATH;
        VollyResponse<AgendaEvents> agendaEventsVollyResponse=new VollyResponse<>();

        Map<String, String> params = new HashMap<>();
        params.put("user_id", "");
        params.put("user_event_time", "");
        params.put("page_size", "15");
        params.put("current_page", "5");
        view.showProgressBar();
        vollyRequestManager.doPost(mContext, url, agendaEventsVollyResponse, params, new VollyRequestManager.OnRequestFinishedListener() {
            @Override
            public void onSucess(VollyResponse response) {
                Log.i("Agenda", response.getMsg());
                view.hideProgressBar();
                List<AgendaEvents> list=response.getResultArray(AgendaEvents.class);
                view.setAgendaList(list);
            }

            @Override
            public void onFailed(String msg) {
                view.hideProgressBar();
                ToastUtil.show(mContext, msg, 1500);
            }
        });
    }
}
