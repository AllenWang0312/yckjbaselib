package com.yckj.baselib.common;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by wpc on 2018/5/13.
 */

public interface AppInterface {
    void attach(Context context);

    void onCreate(Bundle bundle);

    void onStart();

    void onResume();

    void onDestroy();
}
