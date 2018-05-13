package com.yckj.baselib.common.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;

/**
 * 类描述: 类似 下载butto text 在时差两边不同颜色
 * 创建人: Administrator
 * 创建时间: 2018/4/11 0011 下午 10:45
 * 修改人:
 * 修改时间:
 * 修改备注:
 *
 * todo
 */

public class ProgressTextView extends View {

    float progress;

    public ProgressTextView(Context context) {
        super(context);
    }

    public ProgressTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    int width, height;

    Paint textPaint;

    @Override
    protected void onDraw(Canvas canvas) {
        RectF rect = new RectF(0f, 0f, (progress / 100 * width), height);
        DecimalFormat format = new DecimalFormat(".00");

        String text = format.format(progress);
        textPaint = new Paint();
        textPaint.setColor(0xffffffff);
        textPaint.setTextSize(100);

        float length = textPaint.measureText(text);
//        canvas.drawTextOnPath();
        width = canvas.getWidth();
        height = canvas.getHeight();

        super.onDraw(canvas);
    }
}
