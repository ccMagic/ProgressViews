package com.github.ccmagic.progressviewlib;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static android.view.animation.Animation.INFINITE;
import static android.view.animation.Animation.REVERSE;

/**
 * 动画实现
 *
 * @author kxmc
 * <a href="http://www.kxmc.top">kxmc.top</a>
 * <a href="https://github.com/ccMagic">github(kxmc)</a>
 * @date 19-7-19 10:14
 */
public class ProgressView3 extends ViewGroup {

    private static final String TAG = "ProgressView3";
    /**
     * 两条线之间的间隙是线宽的多少倍
     */
    private final int times = 2;
    /**
     * 竖线的数量
     */
    private final int lineNum = 5;

    private List<View> viewList = new ArrayList<>();

    public ProgressView3(Context context) {
        this(context, null);
    }

    public ProgressView3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        for (int i = 0; i < lineNum; i++) {
            View view = new View(context);
            view.setBackgroundColor(Color.RED);
            viewList.add(view);
        }
        postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0, size = viewList.size(); i < size; i++) {
                    ScaleAnimation animation = new ScaleAnimation(1f, 1f, 1f, 0.5f);
                    animation.setDuration(40 * lineNum);
                    //实践证明：重复3次的意思是：这个动画首次出现完全后，再重复3次，所以我们会看到有4次
                    animation.setRepeatCount(INFINITE);
                    animation.setRepeatMode(REVERSE);
                    //设置速率
//                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                    //设置开始时时间的偏移量，用在动画集合上面比较多
                    animation.setStartOffset(20 * i);
                    viewList.get(i).setAnimation(animation);
                    animation.start();
                }
            }
        }, 0);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        //
        float lineArea = width - getPaddingLeft() - getPaddingRight();
        //每条竖线的高度 ---根据线的条数求线宽
//            lineNum*lineWidth+(lineNum-1)*lineWidth*times= lineArea;-->
        float lineWidth = lineArea / (lineNum + (lineNum - 1) * times);
        //
        for (int i = 0, size = viewList.size(); i < size; i++) {
            addView(viewList.get(i));
            int left = (int) (getPaddingLeft() + lineWidth * i);
            if (i >= 1) {
                left = (int) (left + lineWidth * times * i);
            }
            viewList.get(i).layout(left, getPaddingTop(), (int) (left + lineWidth), height - getPaddingBottom());
        }
    }
}

