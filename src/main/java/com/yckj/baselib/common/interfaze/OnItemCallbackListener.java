package com.yckj.baselib.common.interfaze;

/**
 * Created by wpc on 2017/6/7.
 */

public interface OnItemCallbackListener {
    void onMove(int fromPosition, int toPosition);
    void onSwipe(int position);
}
