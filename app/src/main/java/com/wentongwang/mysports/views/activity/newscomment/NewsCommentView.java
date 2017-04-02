package com.wentongwang.mysports.views.activity.newscomment;

import com.wentongwang.mysports.base.BaseView;
import com.wentongwang.mysports.model.module.CommentInfo;
import com.wentongwang.mysports.model.module.NewsInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */

public interface NewsCommentView extends BaseView {
    void fillComments(List<CommentInfo> commentInfoList);
}
