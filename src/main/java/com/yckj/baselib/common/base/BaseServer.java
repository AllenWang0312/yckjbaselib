package com.yckj.baselib.common.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yckj.baselib.util.L;

/**
 * Created by wpc on 2018/1/25 0025.
 */

public abstract class BaseServer extends Service {
    protected String TAG = getClass().getSimpleName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.i(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        L.i(TAG,"onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        L.i(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        L.i(TAG,"onDestroy");
        super.onDestroy();
    }
}
