package com.yckj.baselib.common.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.yckj.baselib.R;
import com.yckj.baselib.util.L;
import com.yckj.baselib.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;

import static com.yckj.baselib.util.DrawUtil.drawBaseRightBottomText;
import static com.yckj.baselib.util.DrawUtil.drawBaseTopCenterText;

/**
 * Created by wpc on 2018/1/30 0030.
 */

public class BaseChartView extends View {

    int paddingLeft;
    int paddingRight = 20;
    int paddingTop = 20;
    int paddingBottom;

    int x_colum_num = 10;
    int y_colum_num = 5;


    private Paint linePaint, textPaint, pointPaint;

    int textSize = 32;
    int height, width;
    int x_length, y_length;
    float x_d, y_d;

    float x_v_d, y_v_d, x_v_p, y_v_p;

    int textcolor_common, textcolor_Title;

    public BaseChartView(Context context) {
        this(context, null);
    }

    public BaseChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void setColumNum(int x_colum_num, int y_colum_num) {
        this.x_colum_num = x_colum_num;
        this.y_colum_num = y_colum_num;
    }

    int titleTextSize = 32;
    int left_text_space = 84;
    int bottom_text_space = 48;

    @Override
    protected void onDraw(Canvas canvas) {
        paddingLeft = titleTextSize + left_text_space;
        paddingBottom = titleTextSize + bottom_text_space;

        textcolor_common = getContext().getResources().getColor(R.color.black);


        linePaint = new Paint();
        textPaint = new TextPaint();
        pointPaint = new Paint();

        textPaint.setTextSize(textSize);

        height = canvas.getHeight();
        width = canvas.getWidth();

        x_length = width - paddingLeft - paddingRight;
        y_length = height - paddingTop - paddingBottom;

        x_d = x_length / x_colum_num;
        y_d = y_length / y_colum_num;
        x_v_d = (x_to - x_from) / x_colum;
        y_v_d = (y_to - y_from) / y_colum;

        x_v_p = x_length / (x_to - x_from);
        y_v_p = y_length / (y_to - y_from);

        drawXY(canvas);
        drawTitles(canvas);
        drawLines(canvas);
        super.onDraw(canvas);
    }

    boolean line = false;

    private void drawLines(Canvas canvas) {
        for (Integer key : doits.keySet()) {
            drawLine(canvas, key, lineWidths.get(key), doitRs.get(key), doits.get(key));
        }
    }

    private void drawLine(Canvas canvas, Integer color, Integer width, Integer doitR, ArrayList<ChartValue> chartValues) {
        if (line) {
            linePaint.setColor(color);
            linePaint.setStrokeWidth(width);
        }
        pointPaint.setColor(color);
        for (int i = 0; i < chartValues.size(); i++) {
            PointF point = getPointF(chartValues.get(i));
            canvas.drawPoint(point.x, point.y, pointPaint);
        }
    }

    private void drawDoit(PointF item, Canvas canvas, boolean line) {
        if (item.x > paddingLeft && item.x < width - paddingRight
                && item.y > paddingTop && item.y < height - paddingBottom) {
            canvas.drawPoint(item.x, item.y, pointPaint);
        }
    }

    private HashMap<Integer, ArrayList<ChartValue>> doits;
    HashMap<Integer, Integer> lineWidths;
    HashMap<Integer, Integer> doitRs;

    public void clean() {
        if (doits != null && doits.size() > 0) {
            doits.clear();
            lineWidths.clear();
            doitRs.clear();
        }
    }

    private void drawXY(Canvas canvas) {
        linePaint.setStrokeWidth(2);
        //画竖线
        for (int i = 0; i <= x_colum_num; i++) {
            canvas.drawLine(paddingLeft + i * x_d, paddingTop, paddingLeft + i * x_d, height - paddingBottom, linePaint);
            drawBaseTopCenterText(canvas, (x_from + i * x_v_d) + "", paddingLeft + i * x_d,
                    height - paddingBottom + textSize,
                    textPaint);

        }
        //画横线
        for (int i = 0; i <= y_colum_num; i++) {
            canvas.drawLine(paddingLeft, paddingTop + i * y_d, width - paddingRight, paddingTop + i * y_d, linePaint);
//            canvas.drawText((y_from+i*y_v_d) + "",
//                    paddingLeft - 64,
//                    paddingTop + i * y_d,
//                    textPaint);
            drawBaseRightBottomText(canvas, (y_to - i * y_v_d) + "",
                    paddingLeft,
                    paddingTop + i * y_d,
                    textPaint);
        }
//        canvas.drawLine();
    }


    private void drawTitles(Canvas canvas) {
        if (!StringUtils.isEmpty(y_title)) {
            textPaint.setColor(y_title_color);
            L.i("drawtitles", "y:" + y_title);

            float length = textPaint.measureText(y_title);
            Path p = new Path();
            p.moveTo(titleTextSize, paddingTop + y_length / 2 + length / 2);
            p.lineTo(titleTextSize, paddingTop + y_length / 2 - length / 2);
            canvas.drawTextOnPath(y_title, p, 0f, 0f, textPaint);
        }
        if (!StringUtils.isEmpty(x_title)) {
            textPaint.setColor(x_title_color);
            L.i("drawtitles", "x:" + x_title);
            float length = textPaint.measureText(x_title);
            canvas.drawText(x_title, paddingLeft + x_length / 2 - length / 2, height, textPaint);
        }
    }

    float x_from = 0f, x_to, x_colum, y_from = 0f, y_to, y_colum;
    int x_title_color, y_title_color;
    String x_title = "", y_title = "";

    public void setStyle(StyleBuilder build) {

        this.line = build.line;

        this.doits = build.doits;

        this.lineWidths = build.lineWidths;
        this.doitRs = build.doitRs;

        this.x_from = build.x_from;
        this.x_to = build.x_to;
        this.x_colum = build.x_colum;
        this.x_title_color = build.x_title_color;

        this.y_from = build.y_from;
        this.y_to = build.y_to;
        this.y_colum = build.y_colum;

        this.y_title_color = build.y_title_color;

        this.x_title = build.x_title;
        this.y_title = build.y_title;

        invalidate();

    }

    public static class StyleBuilder {

        HashMap<Integer, ArrayList<ChartValue>> doits;
        HashMap<Integer, Integer> lineWidths;
        HashMap<Integer, Integer> doitRs;

        private boolean line, dashPath;
        float x_from = 0f, x_to, x_colum, y_from = 0f, y_to, y_colum;
        String x_title = "", y_title = "";
        int x_title_color, y_title_color;
        boolean commit = false;

        public StyleBuilder setXInfo(float from, float to, int colum, String title, int titleColor) {
            x_from = from;
            x_to = to;
            x_colum = colum;
            x_title = title;
            x_title_color = titleColor;
            return this;
        }

        public StyleBuilder setYInfo(float from, float to, int colum, String title, int titleColor) {
            y_from = from;
            y_to = to;
            y_colum = colum;
            y_title = title;
            y_title_color = titleColor;
            return this;
        }

//        public StyleBuilder commit(){
//          commit=true;
//          return this;
//        }

        public StyleBuilder addLine(int color, ArrayList<ChartValue> line) {
            return this.addLine(color, 3, 5, line);
        }

        public StyleBuilder addLine(int color, int lineWidth, int doitR, ArrayList<ChartValue> line) {
            if (doits == null) {
                doits = new HashMap<>();
                lineWidths = new HashMap<>();
                doitRs = new HashMap<>();
            }
            doits.put(color, line);
            lineWidths.put(color, lineWidth);
            doitRs.put(color, doitR);
            return this;
        }

        public StyleBuilder line(boolean bool) {
            line = bool;
            return this;
        }

        public StyleBuilder dashPath(boolean b) {
            dashPath = b;
            return this;
        }
    }


    public static class ChartValue {

        float x;
        float y;

        public ChartValue(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }
    }


    PointF getPointF(ChartValue value) {
        return new PointF(paddingLeft + (value.x - x_from) * x_v_p, height - paddingBottom - (value.y - y_from) * y_v_p);
    }
}
