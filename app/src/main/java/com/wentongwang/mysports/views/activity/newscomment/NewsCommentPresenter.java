package com.wentongwang.mysports.views.activity.newscomment;

import android.content.Context;

import com.wentongwang.mysports.model.module.CommentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */

public class NewsCommentPresenter {

    private List<CommentInfo> itemList;

    private NewsCommentView view;
    private Context mContext;

    public NewsCommentPresenter(NewsCommentView view) {
        this.view = view;
    }

    public void init(Context context) {
        this.mContext = context;
    }

    //the comment of news for testing
    public void getComments() {
        itemList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CommentInfo item = new CommentInfo("content" + i, i);
            itemList.add(item);
        }

        //获取到list数据后调用view去填充数据
        view.fillComments(itemList);
    }
}
