package com.wentongwang.mysports.views.activity.newscomment;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.base.BaseActivity;
import com.wentongwang.mysports.constant.IntentConstants;
import com.wentongwang.mysports.custome.CommonHeadView;
import com.wentongwang.mysports.model.module.CommentInfo;
import com.wentongwang.mysports.model.module.NewsInfo;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/28.
 */

public class NewsCommentActivity extends BaseActivity implements NewsCommentView {
    @BindView(R.id.comment_list)
    protected ListView commentList;
    @BindView(R.id.tool_bar)
    protected CommonHeadView mToolbar;
    @BindView(R.id.head_left_tv)
    protected TextView headLeftTv;

    private NewsCommentAdapter newsCommentAdapter;
    private NewsInfo news;

    private NewsCommentPresenter mPresenter = new NewsCommentPresenter();

    @Override
    protected void initPresenter() {
        mPresenter.setView(this);
        mPresenter.init(this);
    }

    @Override
    protected void initDatesAndViews() {
        news = (NewsInfo) getIntent().getSerializableExtra(IntentConstants.EXTRA_NEWS_ITEM);
        headLeftTv.setText(this.getString(R.string.comment));
        initCommentListView();

        mPresenter.getComments();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    protected void initEvents() {
        mToolbar.setCallbck(new CommonHeadView.CALLBACK() {
            @Override
            public void onLeftClick() {
                NewsCommentActivity.this.finish();
            }

            @Override
            public void onRightClick() {
            }

            @Override
            public void onCenterClick() {

            }
        });
    }

    @Override
    protected boolean noTitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_comment_layout;
    }


    private void initCommentListView(){
        newsCommentAdapter = new NewsCommentAdapter();
        LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        //TODO:这个headView需要提取出来复用
        RelativeLayout ll = (RelativeLayout) layoutInflater.inflate( R.layout.acitivity_news_comment_header_layout, null, false );
        TextView tv = (TextView) ll.findViewById(R.id.comment_news_item_content);
        tv.setText(news.getNews_content());
        commentList.addHeaderView(ll);
        commentList.setAdapter(newsCommentAdapter);
    }

    @Override
    public void fillComments(List<CommentInfo> commentInfoList) {
        newsCommentAdapter.setItemList(commentInfoList);
    }
}
