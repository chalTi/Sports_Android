package com.wentongwang.mysports.views.fragment.agenda;

import android.content.Context;
import android.widget.PopupWindow;

import com.android.volley.Request;
import com.wentongwang.mysports.constant.Constant;
import com.wentongwang.mysports.custome.PersonInfoPopupWindow;
import com.wentongwang.mysports.model.bussiness.RxVolleyRequest;
import com.wentongwang.mysports.model.bussiness.VollyRequestManager;
import com.wentongwang.mysports.model.bussiness.VolleyResponse;
import com.wentongwang.mysports.model.module.AgendaEvents;
import com.wentongwang.mysports.utils.ToastUtil;
import com.wentongwang.mysports.utils.VolleyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
   /* public void getAgenda(){
        String url=Constant.HOST+ Constant.GET_AGENDA_PATH;
        VolleyResponse<AgendaEvents> agendaEventsVollyResponse=new VolleyResponse<>();

        Map<String, String> params = new HashMap<>();
        params.put("user_id", "");
        params.put("user_event_time", "");
        params.put("page_size", "15");
        params.put("current_page", "5");
        view.showProgressBar();
        vollyRequestManager.doPost(mContext, url, agendaEventsVollyResponse, params, new VollyRequestManager.OnRequestFinishedListener() {
            @Override
            public void onSuccess(VolleyResponse response) {
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
    }*/
    public void getAgenda(){
        String url=Constant.HOST+ Constant.GET_AGENDA_PATH;
        VolleyResponse<AgendaEvents> agendaEventsVollyResponse=new VolleyResponse<>();

        Map<String, String> params = new HashMap<>();
        params.put("user_id", "");
        params.put("user_event_time", "");
        params.put("page_size", "15");
        params.put("current_page", "5");
        view.showProgressBar();
        RxVolleyRequest.getInstance().resourceRequestObservable(mContext,Request.Method.POST,url,params)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressBar();
                        ToastUtil.show(mContext, e.toString(), 1500);
                    }

                    @Override
                    public void onNext(String response) {
                        VolleyResponse<AgendaEvents> agendaEventsVolleyResponse = new VolleyResponse<AgendaEvents>();
                        agendaEventsVolleyResponse.setMsg(response);
                        List<AgendaEvents> list=agendaEventsVolleyResponse.getResultArray(AgendaEvents.class);
                        view.setAgendaList(list);
                    }
                });
    }
}
