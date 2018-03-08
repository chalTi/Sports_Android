package com.wentongwang.mysports.views.fragment.news;

import com.wangwentong.sports_api.model.NewsInfo;
import com.wentongwang.mysports.base.BaseView;

import java.util.List;

/**
 * Created by Wentong WANG on 2016/10/13.
 */
public interface NewsView extends BaseView {

    void setNewsList(List<NewsInfo> list);
}
