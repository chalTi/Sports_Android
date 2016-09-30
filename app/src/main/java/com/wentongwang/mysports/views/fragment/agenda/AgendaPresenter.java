package com.wentongwang.mysports.views.fragment.agenda;

import android.content.Context;
import android.widget.PopupWindow;

import com.wentongwang.mysports.custome.PersonInfoPopupWindow;
import com.wentongwang.mysports.model.bussiness.VollyRequestManager;
import com.wentongwang.mysports.utils.Logger;
import com.wentongwang.mysports.utils.VolleyUtil;

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
}
