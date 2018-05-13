package com.yckj.baselib.common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 下午 12:34
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public abstract class BaseApplication extends Application {

    public static Context applicationContext;
    protected static BaseApplication instance;
    protected ArrayList<BaseActivity> activitys;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public void addActivity(BaseActivity activity) {
        if (activitys == null) {
            activitys = new ArrayList<>();
        }
        activitys.add(activity);
    }

    public void removeActivity(BaseActivity activity) {
        activitys.remove(activity);
    }

    public void exit() {
        for (BaseActivity item : activitys) {
            item.finish();
        }
        activitys.clear();
    }

    /**
     * 获取界面数量
     *
     * @return activity size
     */
    public int getActivitySize() {
        if (activitys != null) {
            return activitys.size();
        }
        return 0;
    }
    public Activity getCurrentActivity() {
        if (activitys != null && activitys.size() > 0) {
            Activity activity = activitys.get(activitys.size() - 1);
            return activity;
        }
        return null;
    }

    public Activity getActivity(Class<?> cls) {
        if (activitys == null) {
            return null;
        }
        for (Activity activity : activitys) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    public void removeActivity(Activity activity) {
        if (activity != null) {
            activitys.remove(activity);
        }
    }

    public void removeActivity(Class<?> cls) {
        if (activitys == null) {
            return;
        }
        for (Activity activity : activitys) {
            if (activity.getClass().equals(cls)) {
                activitys.remove(activity);
            }
        }
    }

    public void finishAllActivity() {
        if (activitys == null) {
            return;
        }
        int size = activitys.size();
        for (int i = 0; i < size; i++) {
            if (null != activitys.get(i)) {
                activitys.get(i).finish();
            }
        }
        activitys.clear();
    }

    /**
     * 结束其他所有的Activity
     *
     * @param activity 不需要销毁的Activity
     */
    public void finishOtherAllActivity(Activity activity) {
        if (activitys == null) {
            return;
        }
        for (int i = 0, size = activitys.size(); i < size; i++) {
            if (activity != activitys.get(i)) {
                activitys.get(i).finish();
                activitys.remove(i);
            }
        }
    }

    public List<BaseActivity> getAllActivitys() {
        if (activitys != null) {
            return activitys;
        }
        return null;
    }

}
