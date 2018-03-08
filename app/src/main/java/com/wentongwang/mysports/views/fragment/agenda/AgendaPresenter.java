package com.wentongwang.mysports.views.fragment.agenda;

import android.content.Context;

import com.wentongwang.mysports.constant.Constant;
import com.wentongwang.mysports.custome.PersonInfoPopupWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wentong WANG on 2016/9/26.
 */
public class AgendaPresenter {

    private AgendaView view;
    private Context mContext;


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

    }

    public void showPopupWindow() {
        //获取数据，通过构造函数塞到popupWindow里
        final PersonInfoPopupWindow popupWindow = new PersonInfoPopupWindow(mContext);


        popupWindow.setOnDismissListener(() -> view.setBackGroundAlpha(1f));

        view.showPersonInfoPopupWindow(popupWindow);
        view.setBackGroundAlpha(0.55f);
    }

    public void getAgenda(){
        String url=Constant.HOST+ Constant.GET_AGENDA_PATH;
        //TODO:接口重写

        Map<String, String> params = new HashMap<>();
        params.put("user_id", "");
        params.put("user_event_time", "");
        params.put("page_size", "15");
        params.put("current_page", "5");
        view.showProgressBar();
//        RxVolleyRequest.getInstance().resourceRequestObservable(mContext,Request.Method.POST,url,params)
//                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
//                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onCompleted() {
//                        view.hideProgressBar();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        view.hideProgressBar();
//                        ToastUtil.show(mContext, e.toString(), 1500);
//                    }
//
//                    @Override
//                    public void onNext(String response) {
//                        VolleyResponse<AgendaEvents> agendaEventsVolleyResponse = new VolleyResponse<AgendaEvents>();
//                        agendaEventsVolleyResponse.setMsg(response);
//                        List<AgendaEvents> list=agendaEventsVolleyResponse.getResultArray(AgendaEvents.class);
//                        view.setAgendaList(list);
//                    }
//                });
    }
}
