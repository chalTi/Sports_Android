package com.wentongwang.mysports.views.activity.newscomment;

import android.content.Context;

import com.wentongwang.mysports.base.BasePresenter;
import com.wentongwang.mysports.model.module.CommentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */

public class NewsCommentPresenter extends BasePresenter<NewsCommentView>{

    private List<CommentInfo> itemList;

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
