package com.wentongwang.mysports.custome;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.wentongwang.mysports.R;


/**
 * 好友列表的弹框
 * Created by Wentong WANG on 2016/9/24.
 */
public class ListPopupWindow extends PopupWindow {

    private Context context;
    private int mWidth;
    private int mHeight;

    private View contentView;

    private RecyclerView friendList;


    public ListPopupWindow(Context context) {
        this.context = context;
        calWidthAndHeight(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.popup_friend_list_layout, null);
        setContentView(contentView);
        setWidth(mWidth);
        setHeight(mHeight);

        setTouchable(true);
        setFocusable(true);
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
        friendList = (RecyclerView) contentView.findViewById(R.id.friend_recyclerlist);
        friendList.setLayoutManager(new LinearLayoutManager(context));

    }


    private void calWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetris = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetris);

        mWidth = (int) (outMetris.widthPixels * 0.9);
        mHeight = (int) (outMetris.heightPixels * 0.7);
    }


    class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return new MyViewHolder(inflater.inflate(R.layout.item_friend_list, parent));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            public MyViewHolder(View view) {
                super(view);
                // use ButterKnife bind Views
            }
        }
    }
}

