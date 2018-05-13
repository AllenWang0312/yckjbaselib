package com.yckj.baselib.util;

import android.util.Log;

import com.yckj.baselib.BuildConfig;


/**
 *功能描述:
 *作者: wpc
 *创建于: 2018/3/14 0014 下午 12:55
 **/
public class L {
    public static final boolean isdebug = BuildConfig.DEBUG;

    public static void i(String tag, String str) {
        if (isdebug) Log.i(tag, str);
    }

    public static void i(String str) {
        i("", str);
    }

    public static void d(String tag, String str) {
        if (isdebug) Log.d(tag, str);
    }

    public static void d(String str) {
        d("", str);
    }

    public static void v(String tag, String str) {
        if (isdebug) Log.v(tag, str);
    }

    public static void v(String str) {
        v("", str);
    }

    public static void e(String tag, String str) {
        if (isdebug) Log.e(tag, str);
    }

    public static void e(String str) {
        e("", str);
    }
}
