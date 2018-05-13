package com.yckj.baselib.common.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.HashMap;

/**
 * Created by wpc on 2018/1/12 0012.
 */

@SuppressLint("AppCompatCustomView")
public class StateImageView extends ImageView {
    public StateImageView(Context context) {
        super(context);
    }

    public StateImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StateImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    HashMap<Integer, Integer> states;

    public void addState(int state, int res_id) {
        if (states == null) {
            states = new HashMap<>();
        }
        states.put(state, res_id);
    }

    int state;

    public void setState(int state) {
        this.state = state;
        setImageResource(states.get(state));
    }

    public int getState() {
        return state;
    }
}
