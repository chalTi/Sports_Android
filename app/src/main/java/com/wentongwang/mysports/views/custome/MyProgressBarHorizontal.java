package com.wentongwang.mysports.views.custome;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.utils.DensityUtil;

/**
 * 进度条自定义View
 * Created by Wentong WANG on 2016/9/21.
 */
public class MyProgressBarHorizontal extends ProgressBar {

    private static final int DEFAUT_COLOR_UNREACH = 0XFFD3D6DA;
    private static final int DEFAUT_HEIGHT_UNREACH = 2; //DP
    private static final int DEFAUT_COLOR_REACH = 0XFFFC00D1;
    private static final int DEFAUT_HEIGHT_REACH = 2;

    private int mReachColor = DEFAUT_COLOR_REACH;
    private int mReachHeight = DEFAUT_HEIGHT_REACH;
    private int mUnReachColor = DEFAUT_COLOR_UNREACH;
    private int mUnReachHeight = DEFAUT_HEIGHT_UNREACH;
    //渐变颜色的配置
    private boolean needGradient = true;
    private int mStartColor = Color.BLUE;
//    private int mMiddleColor = Color.GREEN;
    private int mEndColor = Color.GREEN;

    private Paint mReachPaint = new Paint();
    private Paint mUnReachPaint = new Paint();

    private int mRealWidth;

    private Context context;
    private Shader mShader;

    public MyProgressBarHorizontal(Context context) {
        this(context, null);
    }

    public MyProgressBarHorizontal(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgressBarHorizontal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        obtainStyleAttrs(attrs);
    }

    private void obtainStyleAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MyProgressBarHorizontal);

        mReachColor = ta.getColor(R.styleable.MyProgressBarHorizontal_reachColor, DEFAUT_COLOR_REACH);
        mUnReachColor = ta.getColor(R.styleable.MyProgressBarHorizontal_unReachColor, DEFAUT_COLOR_UNREACH);

        mReachHeight = (int) ta.getDimension(R.styleable.MyProgressBarHorizontal_reachHeight, DensityUtil.dp2px(context, DEFAUT_HEIGHT_REACH));
        mUnReachHeight = (int) ta.getDimension(R.styleable.MyProgressBarHorizontal_unReachHeight, DensityUtil.dp2px(context, DEFAUT_HEIGHT_UNREACH));

        needGradient = ta.getBoolean(R.styleable.MyProgressBarHorizontal_gradient, false);

        ta.recycle();

        //新建一个线性渐变，前两个参数是渐变开始的点坐标，
        // 第三四个参数是渐变结束的点的坐标。连接这2个点就拉出一条渐变线了，玩过PS的都懂。
        // 然后那个数组是渐变的颜色。
        //下一个参数是渐变颜色的分布，如果为空，每个颜色就是均匀分布的。
        // 最后是模式，这里设置的是循环渐变
//        mShader = new LinearGradient(0, 0, 100, 0, new int[]{mStartColor, mEndColor}, new float[]{0.5f}, Shader.TileMode.REPEAT);
        mShader = new LinearGradient(0, 0, 200, 0, mStartColor, mEndColor, Shader.TileMode.MIRROR);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = measureHeight(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        setMeasuredDimension(width, height);

        mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }


    /**
     * 测量高
     *
     * @param measureSpec
     * @return
     */
    private int measureHeight(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        int result = 0;
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            //result=2图片最高的那个 + 2个padding
            result = Math.max(mReachHeight, mUnReachHeight) + getPaddingTop() + getPaddingBottom();
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(size, result);
            }
        }
        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();

        canvas.translate(getPaddingLeft(), 0);
        //draw reach bar
        boolean needUnReach = false;

        float radio = getProgress() * 1.0f / getMax();
        if (radio < 1) {
            needUnReach = true;
        }
        //draw reach bar
        float endX = radio * mRealWidth;
        if (endX > 0) {
            mReachPaint.setStyle(Paint.Style.FILL);//设置实心
            mReachPaint.setAntiAlias(true);
//            mShader = new LinearGradient(0, 0, endX, 0, new int[]{Color.BLUE, Color.GREEN}, null, Shader.TileMode.REPEAT);
            if (needGradient) {
                mReachPaint.setShader(mShader);
            } else {
                mReachPaint.setColor(mReachColor);
            }

            canvas.drawPath(drawReachBarPath(endX), mReachPaint);
        }
        //draw unreach bar
        if (needUnReach) {
            mUnReachPaint.setColor(mUnReachColor);
            mUnReachPaint.setStyle(Paint.Style.FILL);//设置实心
            mUnReachPaint.setAntiAlias(true);

            canvas.drawPath(drawUnReachBarPath(endX), mUnReachPaint);

        }

        canvas.restore();
    }


    private Path drawReachBarPath(float endX) {
        //进度条边上圆弧赛贝尔曲线的执行点
        int r_cycle = getHeight() / 2;

        Path path = new Path();
        path.moveTo(r_cycle, 0);
        path.lineTo(endX - r_cycle, 0);
        path.quadTo(endX, r_cycle, endX - r_cycle, getHeight()); //赛贝尔曲线画圆弧
        path.lineTo(r_cycle, getHeight());
        path.quadTo(0, r_cycle, r_cycle, 0); //赛贝尔曲线画圆弧
        path.close();//封闭


        return path;
    }


    private Path drawUnReachBarPath(float endX) {
        int r_cycle = getHeight() / 2;

        Path path = new Path();
        if (endX > 0) {
            path.moveTo(endX - r_cycle, 0);
            path.quadTo(endX, r_cycle, endX - r_cycle, getHeight()); //赛贝尔曲线画圆弧
            path.lineTo(mRealWidth - r_cycle, getHeight());
            path.quadTo(mRealWidth, r_cycle, mRealWidth - r_cycle, 0); //赛贝尔曲线画圆弧
//        path.lineTo(mRealWidth, 0);
            path.close();//封闭
        } else {
            path.moveTo(r_cycle, getHeight());
            path.lineTo(mRealWidth - r_cycle, getHeight());
            path.quadTo(mRealWidth, r_cycle, mRealWidth - r_cycle, 0); //赛贝尔曲线画圆弧
            path.lineTo(r_cycle, 0);
            path.quadTo(0, r_cycle, r_cycle, getHeight()); //赛贝尔曲线画圆弧
            path.close();//封闭
        }


        return path;
    }
}
