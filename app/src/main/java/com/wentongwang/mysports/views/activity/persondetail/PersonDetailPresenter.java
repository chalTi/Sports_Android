package com.wentongwang.mysports.views.activity.persondetail;

import android.content.Context;

import com.wentongwang.mysports.model.bussiness.VollyRequestManager;
import com.wentongwang.mysports.utils.VolleyUtil;


/**
 * Created by Wentong WANG on 2016/10/14.
 */
public class PersonDetailPresenter {

    private PersonDetailView view;
    private Context mContext;
    private VollyRequestManager vollyRequestManager;

    public PersonDetailPresenter(PersonDetailView view) {
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
}
