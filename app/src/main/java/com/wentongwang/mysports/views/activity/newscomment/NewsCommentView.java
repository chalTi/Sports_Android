package com.wentongwang.mysports.views.activity.newscomment;

import com.wentongwang.mysports.base.BaseView;
import com.wangwentong.sports_api.model.CommentInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */

public interface NewsCommentView extends BaseView {
    void fillComments(List<CommentInfo> commentInfoList);
}
