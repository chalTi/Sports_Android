package com.wentongwang.mysports.views.fragment.news;

import android.content.Context;

import com.wentongwang.mysports.constant.Constant;


/**
 * Created by Wentong WANG on 2016/10/13.
 */
public class NewsPresenter {
    private NewsView mView;
    private Context mContext;


    public NewsPresenter(NewsView view) {
        this.mView = view;
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
    }


   /* public void getNews(){

        String url =Constant.HOST + Constant.GET_NEWS_PATH;

        VolleyResponse<NewsInfo> newsResponse = new VolleyResponse<>();

        Map<String, String> params = new HashMap<>();
        params.put("user_id", "");
        params.put("user_like", "");
        params.put("page_size", "15");
        params.put("current_page", "5");

        mView.showProgressBar();
        vollyRequestManager.doPost(mContext, url, newsResponse, params, new VollyRequestManager.OnRequestFinishedListener() {
            @Override
            public void onSuccess(VolleyResponse response) {
                //Logger.i("News", response.getMsg());
                mView.hideProgressBar();
                List<NewsInfo> list = response.getResultArray(NewsInfo.class);
                mView.setNewsList(list);
            }

            @Override
            public void onFailed(String msg) {
                mView.hideProgressBar();
                ToastUtil.show(mContext, msg, 1500);
            }
        });
    }*/

    public void getNews(){
        String url =Constant.HOST + Constant.GET_NEWS_PATH;
        //TODO：重写
//        VolleyResponse<NewsInfo> newsResponse = new VolleyResponse<>();
//        Map<String, String> params = new HashMap<>();
//        params.put("user_id", "");
//        params.put("user_like", "");
//        params.put("page_size", "15");
//        params.put("current_page", "5");
//        mView.showProgressBar();
//        RxVolleyRequest.getInstance().resourceRequestObservable(mContext, Request.Method.POST, url, params)
//                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
//                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onCompleted() {
//                        mView.hideProgressBar();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.hideProgressBar();
//                        ToastUtil.show(mContext, e.toString(), 1500);
//                    }
//
//                    @Override
//                    public void onNext(String response) {
//                        VolleyResponse<NewsInfo> newsInfoVolleyResponse = new VolleyResponse<>();
//                        newsInfoVolleyResponse.setMsg(response);
//                        List<NewsInfo> list = newsInfoVolleyResponse.getResultArray(NewsInfo.class);
//                        mView.setNewsList(list);
//                    }
//                });

    }
}
