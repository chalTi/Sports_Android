package com.wentongwang.mysports.views.custome;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.utils.DensityUtil;
import com.wentongwang.mysports.utils.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wentong WANG on 2016/5/5.
 */
public class MyTabLayout extends LinearLayout {
    private static final int TEXT_COLOR_NORMAL = 0x77FFFFFF;
    private static final int TEXT_COLOR_HEIGHT_LIGHT = 0xFFFFFFFF;
    private static final int DEFAUT_TEXT_SIZE = 16;
    private static final int VISIABLE_COUNT_DEFAUT = 3;
    private static final int DEFAUT_LINE_COLOR = 0X00FFFFFF;
    private static final int DEFAUT_TRIANGLE_COLOR = 0X00FFFFFF;
    private static final int DEFAUT_LINE_HEIGHT = 3;
    //标题参数
    private int textColorNormal = TEXT_COLOR_NORMAL;
    private int textColorHeightLight = TEXT_COLOR_HEIGHT_LIGHT;
    private int textSize = DEFAUT_TEXT_SIZE; //sp


    //三角形参数
    private Paint trianglePaint;
    private Path trianglePath;
    private boolean useTriangle = false;
    //三角形的宽，高
    private int mTriangleWidth;
    private int mTriangleHeight;
    //底部小三角对选项宽的比例
    private static final float RADIO_TRIANGLE_WIDTH = 1 / 6F;
    //选项的宽
    private int mTabWidth;
    //三角形初始位置
    private int triangleStartX;
    //移动位置
    private int moveX = 0;
    private int triangleColor = DEFAUT_TRIANGLE_COLOR;

    //线的参数
    private Paint linePaint;
    private Path linePath;
    private int lineStartX;
    private int lineColor = DEFAUT_LINE_COLOR;
    private int lineHeight = DEFAUT_LINE_HEIGHT;

    private int visiableCount = VISIABLE_COUNT_DEFAUT;

    private Context context;
    private List<String> titles;

    public MyTabLayout(Context context) {
        this(context, null);
    }

    public MyTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        titles = new ArrayList<>();
        obtainStyleAttrs(attrs);
        if (useTriangle) {
            initTrianglePaint();
        } else {
            initLinePaint();
        }
    }

    private void obtainStyleAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TabLayoutStyle);

        lineColor = ta.getColor(R.styleable.TabLayoutStyle_lineColor, DEFAUT_LINE_COLOR);
        triangleColor = ta.getColor(R.styleable.TabLayoutStyle_triangleColor, DEFAUT_TRIANGLE_COLOR);

        lineHeight = (int) ta.getDimension(R.styleable.TabLayoutStyle_lineHeight, DensityUtil.dp2px(context, DEFAUT_LINE_HEIGHT));

        useTriangle = ta.getBoolean(R.styleable.TabLayoutStyle_useTriangle, false);

        textColorNormal = ta.getColor(R.styleable.TabLayoutStyle_textColorNormal, TEXT_COLOR_NORMAL);
        textColorHeightLight = ta.getColor(R.styleable.TabLayoutStyle_textColorLight, TEXT_COLOR_HEIGHT_LIGHT);
        textSize = (int) ta.getDimension(R.styleable.TabLayoutStyle_textSize, DEFAUT_TEXT_SIZE);

        ta.recycle();

    }

    /**
     * 初始化三角形画笔
     */
    private void initTrianglePaint() {
        trianglePaint = new Paint();
        trianglePaint.setAntiAlias(true);
        trianglePaint.setColor(triangleColor);
        trianglePaint.setStyle(Paint.Style.FILL);
        trianglePaint.setPathEffect(new CornerPathEffect(3));


    }

    /**
     * 初始化线画笔
     */
    private void initLinePaint() {
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(lineColor);
        linePaint.setStyle(Paint.Style.FILL);

    }


    /**
     * 根据控件的宽高设置东西时候可以在这个方法里设置
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mTabWidth = w / visiableCount;

        if (useTriangle) {
            mTriangleWidth = (int) (mTabWidth * RADIO_TRIANGLE_WIDTH);
            mTriangleHeight = mTriangleWidth * 2 / 3;
            triangleStartX = mTabWidth / 2 - mTriangleWidth / 2;
            initTriangle();
        } else {
            Logger.i("MyTab", "initLine()");
            lineStartX = 0;
            initLine();
        }

    }

    /**
     * 初始化三角形
     * Y轴为负才能显示出来，因为是放tab最下面，所以y轴为负是向上画
     */
    private void initTriangle() {
        trianglePath = new Path();
        trianglePath.moveTo(0, 0);
        trianglePath.lineTo(mTriangleWidth, 0);
        trianglePath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        trianglePath.close();
    }


    /**
     * 横线初始化
     * Y轴为负才能显示出来，因为是放tab最下面，所以y轴为负是向上画
     */
    private void initLine() {
        linePath = new Path();
        linePath.moveTo(0, 0);
        linePath.lineTo(mTabWidth, 0);
        linePath.lineTo(mTabWidth, -lineHeight);
        linePath.lineTo(0, -lineHeight);
        linePath.close();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        if (useTriangle) {
            canvas.translate(triangleStartX + moveX, getHeight() + 2);
            canvas.drawPath(trianglePath, trianglePaint);
        } else {
            canvas.translate(lineStartX + moveX, getHeight());
            canvas.drawPath(linePath, linePaint);
        }

        canvas.restore();

        super.dispatchDraw(canvas);
    }

    /**
     * 滑动三角形，
     *
     * @param position       当前选中的tab
     * @param positionOffset 滑动时的变化量 (0-1)
     */
    public void scroll(int position, float positionOffset) {
        //滑动距离 = positionOffset * tabWidth
        moveX = (int) (mTabWidth * (position + positionOffset));

        if (position >= (visiableCount - 2) && position < (getChildCount() - 2) && positionOffset > 0 && getChildCount() > visiableCount) {
            int dx = (position - (visiableCount - 2)) * mTabWidth + (int) (positionOffset * mTabWidth);
            this.scrollTo(dx, 0);
        }

        //数据值改变，需要重新绘制
        invalidate();
    }




    /**
     * 根据传入字符串数组，动态生成Tab
     *
     * @param titles
     */
    public void setTabItems(List<String> titles) {
        if (titles != null) {
            this.titles = titles;
            this.removeAllViews();
            if (titles.size() < visiableCount) {
                visiableCount = titles.size();
            }
            for (String title : titles) {
                addView(generateTextView(title));
            }
        }

    }


    /**
     * 生成Tab
     *
     * @param title
     * @return
     */
    private TextView generateTextView(String title) {
        TextView tv = new TextView(getContext());
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.width = getScreenWidth() / visiableCount;
        tv.setText(title);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tv.setTextColor(textColorNormal);
        tv.setLayoutParams(lp);

        return tv;
    }


    /**
     * 设置选中tab高亮文本
     * @param position
     */
    public void heightLightTextView(int position) {
        resetTextViewColor();
        View view = getChildAt(position);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(textColorHeightLight);
        }
    }

    /**
     * 恢复文本高亮
     */
    private void resetTextViewColor() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(textColorNormal);
            }
        }
    }


    /**
     * 获取宽度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public void setVisiableTabCount(int count) {
        this.visiableCount = count;
    }

    //点击事件处理
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                float upX = event.getX();
                int position = (int) ((upX + getScrollX()) / mTabWidth);
                scroll(position, 0);
                heightLightTextView(position);
                if (listener != null) {
                    listener.onTabSelected(position);
                }
                break;
        }

        return super.onTouchEvent(event);
    }

    /**
     * 给外部的，tab点击事件的接口
     */
    private OnTabSelectedListener listener;
    public interface OnTabSelectedListener{
        void onTabSelected(int position);
    }
    public void setOnTabSelectedListener(OnTabSelectedListener l) {
        this.listener = l;
    }
}
