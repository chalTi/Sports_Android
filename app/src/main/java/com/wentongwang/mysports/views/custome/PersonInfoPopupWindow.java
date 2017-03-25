package com.wentongwang.mysports.views.custome;


import android.content.Context;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.wentongwang.mysports.R;


/**
 * Created by Wentong WANG on 2016/9/30.
 */
public class PersonInfoPopupWindow extends PopupWindow {

    private Context context;
    private int mWidth;
    private int mHeight;

    private View contentView;

    public PersonInfoPopupWindow(Context context) {
        //TODO：构造函数加个参数，用户信息类的对象
        this.context = context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.popup_person_info_layout, null);

        setContentView(contentView);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setTouchable(true);
        setFocusable(true);
//        Bitmap mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.popupwindow_bg);
        setBackgroundDrawable(new BitmapDrawable());
        setOutsideTouchable(true);

        setAnimationStyle(R.style.popwindow_anim_style);

        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });

        initViewAndDatas();
    }


    private void initViewAndDatas() {
        //get data from serveur

    }




}
