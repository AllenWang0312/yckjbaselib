package com.yckj.baselib.wiget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/31 0031 上午 9:57
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class UnclickableRecyclerView extends RecyclerView {
    public UnclickableRecyclerView(Context context) {
        super(context);
    }

    public UnclickableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UnclickableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }
}
