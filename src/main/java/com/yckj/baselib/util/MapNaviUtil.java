package com.yckj.baselib.util;

import android.content.Context;

import static com.yckj.baselib.util.SystemUtils.isInstallApk;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/31 0031 上午 11:10
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class MapNaviUtil {

    public static void Navi(Context context, LatLang from, LatLang to) {
        if (isInstallApk(context, PackageName.GAODE)) {// 是否安装了高德
            context.startActivity(IntentUtil.getGaoDeNai(to)); // 启动调用
        } else if (isInstallApk(context, PackageName.BAIDUDITU)) {
            context.startActivity(IntentUtil.getBaiduNai(to));
        } else {
//            AmapNaviActivity.start(context,new NaviLatLng(from.lat,from.lng),new NaviLatLng(to.lat,to.lng));
            //网页
//                    Location location = LocationUtils.getInstance(mContext).init();
            context.startActivity(IntentUtil.getChromeGaoDeNai(from, to)); // 启动调用
        }
    }

    public static class LatLang {
        float lat;
        float lng;
        String title;
        String desc;

        public LatLang(float lat, float lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public LatLang(float lat, float lng, String title) {
            this.lat = lat;
            this.lng = lng;
            this.title = title;
        }
    }
}
