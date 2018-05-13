package com.yckj.baselib.baseui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.yckj.baselib.R;
import com.yckj.baselib.common.base.BaseActivity;

/**
 * Created by wpc on 2018/3/13 0013.
 */

public class CommonWigetTestActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.layout_common_wiget);
    }
}
