package com.wentongwang.mysports.custome;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.utils.DensityUtil;


public class CommonHeadView extends LinearLayout {
    private String mTitle;
    private int mTitleVisiability = View.VISIBLE;
    private Drawable mImgLeft = null;
    private int mImgLeftVisiability = View.INVISIBLE;
    private String mLeftInfo;
    private int mTvLeftVisiability = View.INVISIBLE;
    private Drawable mImgRight = null;
    private int mImgRightVisiability = View.INVISIBLE;
    private Drawable mImgCenter = null;
    private int mImgCenterVisiability = View.INVISIBLE;
    private String mRightTitle;
    private int mRightTitleVisiability = View.VISIBLE;


    private int mTitleTextColor = -1;
    private float mTitleTextSize;
    private float mBackgroundHeight;
    private Drawable mBackgroundDrawable;

    private LinearLayout left_lay, right_lay;
    private ImageView mIvLeft, mIvRight;
    private LinearLayout center_lay;
    private ImageView mIvCenter;
    private TextView mTvCenter;
    private TextView mInfoLeft,mInfoRight;
    private RelativeLayout titleBackground;


    public CommonHeadView(Context context) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.common_ch_page_head, this, true);
    }

    public CommonHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);


        LayoutInflater.from(context).inflate(R.layout.common_ch_page_head, this, true);
        //自定义属性获取值
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ToolBarLayout);
        mTitle = ta.getString(R.styleable.ToolBarLayout_tbTitle);
        mTitleVisiability = ta.getBoolean(R.styleable.ToolBarLayout_tbTitleVisiable, true) ? View.VISIBLE : View.GONE;

        mRightTitle = ta.getString(R.styleable.ToolBarLayout_tbInfoRight);
        mRightTitleVisiability = ta.getBoolean(R.styleable.ToolBarLayout_tbInfoRightVisiable,true) ? View.VISIBLE:View.GONE;

        mImgLeft = ta.getDrawable(R.styleable.ToolBarLayout_tbImgLeft);
        mImgLeftVisiability = ta.getBoolean(R.styleable.ToolBarLayout_tbImgLeftVisiable, true) ? View.VISIBLE : View.GONE;

        mImgRight = ta.getDrawable(R.styleable.ToolBarLayout_tbImgRight);
        mImgRightVisiability = ta.getBoolean(R.styleable.ToolBarLayout_tbImgRightVisiable, true) ? View.VISIBLE : View.GONE;

        mTitleTextColor = ta.getColor(R.styleable.ToolBarLayout_tbTitleTextColor, 0Xffffffff);
        mTitleTextSize = ta.getDimension(R.styleable.ToolBarLayout_tbTitleTextSize, DensityUtil.dp2px(context, 20));

        mBackgroundHeight = ta.getDimension(R.styleable.ToolBarLayout_tbBackgroundHeight, DensityUtil.dp2px(context, 55));
        mBackgroundDrawable = ta.getDrawable(R.styleable.ToolBarLayout_tbBackground);

        mLeftInfo = ta.getString(R.styleable.ToolBarLayout_tbInfoLeft);
        mTvLeftVisiability = ta.getBoolean(R.styleable.ToolBarLayout_tbInfoLeftVisiable, true) ? View.VISIBLE : View.GONE;
        mImgCenter = ta.getDrawable(R.styleable.ToolBarLayout_tbImgCenter);
        mImgCenterVisiability = ta.getBoolean(R.styleable.ToolBarLayout_tbImgCenterVisiable, true) ? View.VISIBLE : View.GONE;

        ta.recycle();
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //绑定控件
        mIvLeft = (ImageView) this.findViewById(R.id.img_left);

        mIvRight = (ImageView) this.findViewById(R.id.img_right);

        mTvCenter = (TextView) this.findViewById(R.id.info_center);

        left_lay = (LinearLayout) this.findViewById(R.id.head_left_lay);

        right_lay = (LinearLayout) findViewById(R.id.head_right_lay);

        titleBackground = (RelativeLayout) this.findViewById(R.id.title_layout);
        mInfoLeft = (TextView) this.findViewById(R.id.head_left_tv);
        mInfoRight = (TextView) this.findViewById(R.id.head_right_tv);

        mIvCenter = (ImageView) this.findViewById(R.id.img_center);

        center_lay = (LinearLayout) this.findViewById(R.id.head_center_lay);

        initView();
        initOnClickListener();
    }

    /**
     * 对控件进行设置
     */
    private void initView() {
        if (null == mImgLeft) {
            mIvLeft.setVisibility(mImgLeftVisiability);
        } else {
            mIvLeft.setImageDrawable(mImgLeft);
            mIvLeft.setVisibility(mImgLeftVisiability);
        }

        if (null == mLeftInfo) {
            mInfoLeft.setVisibility(View.GONE);
        } else {
            mInfoLeft.setText(mLeftInfo);
            mInfoLeft.setVisibility(mTvLeftVisiability);
        }

        if (null == mImgRight) {
            mIvRight.setVisibility(View.GONE);
        } else {
            mIvRight.setImageDrawable(mImgRight);
            mIvRight.setVisibility(mImgRightVisiability);
        }

        if (null == mTitle) {
            mTvCenter.setVisibility(View.GONE);
        } else {
            mTvCenter.setText(mTitle);
            mTvCenter.setVisibility(mTitleVisiability);
            mTvCenter.setTextColor(mTitleTextColor);

            mIvCenter.setVisibility(View.GONE);
        }
        if (null == mImgCenter) {
            mIvCenter.setVisibility(View.GONE);
        } else {
            mIvCenter.setImageDrawable(mImgCenter);
            mIvCenter.setVisibility(mImgCenterVisiability);

            mTvCenter.setVisibility(View.INVISIBLE);
        }
        if (mBackgroundDrawable != null) {
            titleBackground.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT, (int) mBackgroundHeight));
            titleBackground.setBackgroundDrawable(mBackgroundDrawable);
        } else {
            titleBackground.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT, (int) mBackgroundHeight));
        }
        if(mRightTitle == null){
            mInfoRight.setVisibility(View.GONE);
        }else{
            mInfoRight.setText(mRightTitle);
            mInfoRight.setVisibility(mRightTitleVisiability);
        }

        if (null == mInfoLeft) {
            mInfoLeft.setVisibility(mTvLeftVisiability);
        } else {
            mInfoLeft.setVisibility(mTvLeftVisiability);
        }

    }

    /**
     * 设置点击事件
     */
    private void initOnClickListener() {
        MyClickListener mListenr = new MyClickListener();
        mIvLeft.setOnClickListener(mListenr);
        mIvRight.setOnClickListener(mListenr);
        mTvCenter.setOnClickListener(mListenr);
        left_lay.setOnClickListener(mListenr);
        right_lay.setOnClickListener(mListenr);
        center_lay.setOnClickListener(mListenr);
    }

    /**
     * 设置中间标题
     * @param head
     */
    public void setTitle(String head) {
        mTvCenter.setText(head);
    }
    public void setRightInfo(String info){
        mInfoRight.setVisibility(View.VISIBLE);
        mInfoRight.setText(info);
    }
    public String getRightInfo(){
        return mInfoRight.getText().toString();
    }
    public String getHead(){
        return mTvCenter.getText().toString();
    }

    /**
     * 是否显现右边的图标
     * @param isShow
     */
    public void setRightImageShow(boolean isShow) {
        if (isShow) {
            mIvRight.setVisibility(View.VISIBLE);
        } else {
            mIvRight.setVisibility(View.GONE);
        }
    }

    /**
     * 是否显现左边的图标
     * @param isShow
     */
    public void setLeftImageShow(boolean isShow) {
        if (isShow) {
            mIvLeft.setVisibility(View.VISIBLE);
        } else {
            mIvLeft.setVisibility(View.GONE);
        }
    }
    /**
     *  是否改变左右的图标
     * @param id
     */
    public void setImageRight(int id){
        mIvRight.setImageResource(id);
    }

    public void setmImageLft(int id ){
        mIvLeft.setImageResource(id);
    }

    /**
     * 获取image 的src
     * @param imageView
     * @return
     */
    public String getImageInfo(ImageView imageView){
        return imageView.getTag().toString();
    }

    private class MyClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.head_left_lay || v.getId() == R.id.img_left) {
                mCallback.onLeftClick();
            } else if (v.getId() == R.id.head_right_lay || v.getId() == R.id.img_right) {
                mCallback.onRightClick();
            } else if (v.getId() == R.id.info_center || v.getId() == R.id.head_center_lay) {
                mCallback.onCenterClick();
            }
        }

    }

    /**
     * 点击事件回调
     * @param callback
     */
    public void setCallbck(CALLBACK callback) {
        if (null != callback) {
            mCallback = callback;
        }
    }

    private CALLBACK mCallback = new EmptyCallback();

    public interface CALLBACK {
        void onLeftClick();

        void onRightClick();

        void onCenterClick();
    }

    private class EmptyCallback implements CALLBACK {

        @Override
        public void onLeftClick() {

        }

        @Override
        public void onRightClick() {

        }

        @Override
        public void onCenterClick() {

        }
    }


}
