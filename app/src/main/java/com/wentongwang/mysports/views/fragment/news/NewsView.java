package com.wentongwang.mysports.views.fragment.news;

import com.wentongwang.mysports.model.module.NewsInfo;
import com.wentongwang.mysports.views.base.BaseView;

import java.util.List;

/**
 * Created by Wentong WANG on 2016/10/13.
 */
public interface NewsView extends BaseView {

    void setNewsList(List<NewsInfo> list);
}
