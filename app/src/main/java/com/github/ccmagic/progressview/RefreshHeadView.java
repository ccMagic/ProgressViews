package com.github.ccmagic.progressview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class RefreshHeadView extends View {

    private Paint mPaintLine;
    private Paint mPaintDot;

    public RefreshHeadView(Context context) {
        super(context);
        init();
    }

    public RefreshHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaintLine = new Paint();
        mPaintLine.setColor(Color.YELLOW);
        mPaintLine.setStrokeJoin(Paint.Join.ROUND);
        mPaintLine.setStrokeCap(Paint.Cap.ROUND);
        mPaintLine.setStyle(Paint.Style.FILL);
        mPaintLine.setAntiAlias(true);
        mPaintLine.setStrokeWidth(12);

        mPaintDot = new Paint();
        mPaintDot.setColor(Color.RED);
        mPaintDot.setStrokeJoin(Paint.Join.ROUND);
        mPaintDot.setStrokeCap(Paint.Cap.ROUND);
        mPaintDot.setStyle(Paint.Style.FILL);
        mPaintDot.setAntiAlias(true);
        mPaintDot.setStrokeWidth(12);
    }

    private float fontSize = 60;

    @Override
    protected void onDraw(Canvas canvas) {
        fontSize = getMeasuredHeight();
        //Z
        canvas.drawLine(0, 0, fontSize, 0, mPaintLine);
        canvas.drawLine(fontSize, 0, 0, fontSize, mPaintLine);
        canvas.drawLine(0, fontSize, fontSize, fontSize, mPaintLine);
        canvas.drawPoint(fontSize, 0, mPaintDot);
        canvas.drawPoint(fontSize / 2, fontSize / 2, mPaintDot);
        canvas.drawPoint(0, fontSize, mPaintDot);
        //X
        canvas.translate(fontSize + 10, 0);
        canvas.drawLine(0, 0, fontSize, fontSize, mPaintLine);
        canvas.drawLine(0, fontSize, fontSize, 0, mPaintLine);
        canvas.drawPoint(fontSize / 2, fontSize / 2, mPaintDot);
        //W
        canvas.translate(fontSize + 10, 0);
        canvas.drawLine(0, 0, fontSize / 4, fontSize, mPaintLine);
        canvas.drawLine(fontSize / 4, fontSize, fontSize / 2, fontSize / 3, mPaintLine);
        canvas.drawLine(fontSize / 2, fontSize / 3, fontSize * 3 / 4, fontSize, mPaintLine);
        canvas.drawLine(fontSize * 3 / 4, fontSize, fontSize, 0, mPaintLine);
        canvas.drawPoint(fontSize / 4, fontSize, mPaintDot);
        canvas.drawPoint(fontSize / 2, fontSize / 3, mPaintDot);
        canvas.drawPoint(fontSize * 3 / 4, fontSize, mPaintDot);
        //K
        canvas.translate(fontSize + 10, 0);
        canvas.drawLine(0, 0, 0, fontSize, mPaintLine);
        canvas.drawLine(0, fontSize / 2, fontSize / 3, fontSize / 2, mPaintLine);
        canvas.drawLine(fontSize / 3, fontSize / 2, fontSize, 0, mPaintLine);
        canvas.drawLine(fontSize / 3, fontSize / 2, fontSize, fontSize, mPaintLine);
        canvas.drawPoint(0, fontSize / 2, mPaintDot);
        canvas.drawPoint(fontSize / 3, fontSize / 2, mPaintDot);
    }
}
