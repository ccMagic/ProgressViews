package com.github.ccmagic.progressview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 加载条
 */
public class ProgressView2 extends View {
    //
    private boolean running = true;
    //
    private Paint paint;
    /**
     * 相邻线段长度差异
     */
    private float interval;
    /**
     * 竖线条数
     */
    private int progressLines = 10;
    /**
     * 两条竖线之间的间距是线的宽度的几倍
     */
    private int times = 2;
    /**
     * 圆角举行绘制范围参数
     */
    private float left = 0;
    private float top = 0;
    private float right;
    private float bottom = -1;
    /**
     * 每条竖线的宽度
     */
    private float lineWidth;
    /**
     * 每条竖线间的间距
     */
    private float translate;

    /**
     * 每条线的自身数据
     */
    private List<Bean> beanList = new ArrayList<>();


    private class Bean {
        float multiple = 0;
        RectF rectF;

        float getY() {
            return ((float) (interval * (Math.sin(Math.PI * ((--multiple) / progressLines)) + 1)));
        }
    }

    private Runnable runnable;

    public ProgressView2(Context context) {
        super(context);
        init();
    }

    public ProgressView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }


    private void init() {

        post(() -> {
            //
            left = getPaddingLeft();
            top = getPaddingTop();
            bottom = getMeasuredHeight() - getPaddingBottom();
            int drawArea = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
            lineWidth = drawArea / ((progressLines - 1) * (times + 1));
            translate = lineWidth * (times + 1);
            right = left + lineWidth;
            interval = (bottom - top) / progressLines;
            for (int i = 0; i < progressLines; i++) {
                Bean bean = new Bean();
                bean.multiple = i;
                bean.rectF = new RectF(left, top + bean.getY(), right, bottom - bean.getY());
                beanList.add(bean);
            }
            //
            paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(lineWidth);
            //
            postInvalidate();
        });

    }

    /**
     * 注销：进度条取消之前，需要设置运行状态为false，防止内存泄漏
     */
    public void cancellation() {
        this.running = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (paint != null) {
            canvas.save();
            for (Bean bean : beanList) {
                canvas.drawRoundRect(bean.rectF, lineWidth, lineWidth, paint);
                canvas.translate(translate, 0);
            }
            canvas.restore();

            if (runnable == null) {
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        for (Bean bean : beanList) {
                            bean.rectF.set(left, top + bean.getY(), right, bottom - bean.getY());
                        }
                        postInvalidate();
                        if (running) {
                            postDelayed(this, 200);
                        }
                    }
                };
                if (running) {
                    postDelayed(runnable, 200);
                }
            }
        }
    }
}