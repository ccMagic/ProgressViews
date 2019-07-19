package com.github.ccmagic.progressviewlib;

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
 *
 * @author kxmc
 */
public class ProgressView1 extends View {
    /***/
    private boolean running = true;
    /***/
    private Paint paint;
    /**
     * 左上右下是针对竖线的绘制区域定义的
     */
    private float left = 0;
    private float top = 0;
    private float right = left + 10;
    private float bottom;
    /**
     * 每条竖线的宽度
     */
    private float lineWidth;
    /**
     * 每条竖线间的间距
     */
    private float translate;
    /***/
    private float interval = 20;
    /**
     * 两条竖线之间的间距是线的宽度的几倍
     */
    private int times = 2;

    private List<Bean> beanList = new ArrayList<>();
    private int progressLines = 10;


    private class Bean {
        int multiple = 0;
        RectF rectF;

        private boolean direction = true;

        //长短变化方向：true变长，false变短
        boolean isDirection() {
            if (multiple >= progressLines - 1) {
                direction = true;
//                multiple = -1;
            }
            if (multiple <= 0) {
                direction = false;
            }
            return direction;
        }
    }

    private Runnable runnable;

    public ProgressView1(Context context) {
        this(context,null);
    }

    public ProgressView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {

        post(new Runnable() {
            @Override
            public void run() {
                left = getPaddingLeft();
                top = getPaddingTop();
                bottom = getMeasuredHeight() - getPaddingBottom();
                int drawArea = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
                lineWidth = (drawArea / ((progressLines - 1) * (times + 1)));

                translate = lineWidth * (times + 1);
                right = left + lineWidth;
                interval = (bottom - top) / progressLines;

                for (int i = 0; i < progressLines; i++) {
                    Bean bean = new Bean();
                    bean.multiple = i;
                    bean.rectF = new RectF(left, top + interval * bean.multiple, right, bottom - interval * bean.multiple);
                    beanList.add(bean);
                }

                //
                paint = new Paint();
                paint.setColor(Color.RED);
                paint.setStrokeJoin(Paint.Join.ROUND);
                paint.setStrokeCap(Paint.Cap.ROUND);
                paint.setStrokeWidth(3);
                paint.setStrokeWidth(lineWidth);
                //
                postInvalidate();
            }
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
                            if (bean.isDirection()) {
                                bean.multiple--;
                            } else {
                                bean.multiple++;
                            }
                            bean.rectF.set(left, top + interval * bean.multiple, right, bottom - interval * bean.multiple);
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