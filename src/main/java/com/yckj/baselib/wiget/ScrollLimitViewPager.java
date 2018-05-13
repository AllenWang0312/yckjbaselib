package com.yckj.baselib.wiget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 左右是否滑动可控制的viewpager
 * Created by palmy on 2017/5/23.
 */

public class ScrollLimitViewPager extends ViewPager {
    private boolean toRight = true;//能否左滑,向右边
    private boolean toLeft = true;//能否右滑,向左边
    /**
     * 上一次x坐标
     */
    private float beforeX;

    public ScrollLimitViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public ScrollLimitViewPager(Context context) {

        super(context);
    }



    //-----禁止左滑-------左滑：上一次坐标 > 当前坐标
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return super.dispatchTouchEvent(ev);
        if(toRight && toLeft){
            return super.dispatchTouchEvent(ev);
        }else  {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN://按下如果‘仅’作为‘上次坐标’，不妥，因为可能存在左滑，motionValue大于0的情况（来回滑，只要停止坐标在按下坐标的右边，左滑仍然能滑过去）
                    beforeX = ev.getX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float motionValue = ev.getX() - beforeX;
                    if (motionValue < 0&&!toRight) {//禁止左滑
                        return true;
                    }
                    if(motionValue > 0&&!toLeft){//禁止右滑
                        return true;
                    }
                    beforeX = ev.getX();//手指移动时，再把当前的坐标作为下一次的‘上次坐标’，解决上述问题
                    break;
                default:
                    break;
            }
            return super.dispatchTouchEvent(ev);
        }

    }
    public void setCanScroll(boolean left, boolean right){
        this.toLeft = left;
        this.toRight = right;
    }

}
