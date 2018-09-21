package com.yckj.baselib.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

import java.io.File;
import java.net.URISyntaxException;

import static android.content.Context.POWER_SERVICE;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/31 0031 下午 1:55
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class IntentUtil {

    public static Intent getCallPhoneIntent(String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + tel);
        intent.setData(data);
        return intent;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void ignoreBatteryOptimization(Activity activity) {
        PowerManager powerManager = (PowerManager) activity.getSystemService(POWER_SERVICE);
        boolean hasIgnored = powerManager.isIgnoringBatteryOptimizations(activity.getPackageName());
        //  判断当前APP是否有加入电池优化的白名单，如果没有，弹出加入电池优化的白名单的设置对话框。
        if (!hasIgnored) {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + activity.getPackageName()));
            activity.startActivity(intent);
        }
    }

    public static Intent getCallPrivilegedIntent(String tel) {

        Intent intent = new Intent("android.intent.action.CALL_PRIVILEGED");
        Uri data = Uri.parse("tel:" + tel);
        intent.setData(data);
        return intent;
    }

    public static Intent openUrlWithSystemChrome(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        return intent;
    }

    public static Intent getGaoDeNai(MapNaviUtil.LatLang to) {
        Intent intents = new Intent();
        intents.setData(Uri.parse("androidamap://navi?sourceApplication=nyx_super&lat=" +
                to.lat + "&lon=" + to.lng + "&dev=0&style=2"));
        return intents;
    }

    public static Intent getChromeGaoDeNai(MapNaviUtil.LatLang from, MapNaviUtil.LatLang to) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        // 驾车导航
        intent.setData(Uri.parse("http://uri.amap.com/navigation?" +
                "from=" +
                from.lng + "," + from.lat +
                "&" +
                "to=" +
                to.lng + "," + to.lat + "&mode=car&src=nyx_super"));
        return intent;
    }

    public static Intent getBaiduNai(MapNaviUtil.LatLang to) {
        Intent intent = null;
        try {
            intent = Intent.getIntent("intent://map/marker?" +
                    "location=" + to.lat + "," + to.lng +
                    "&title=" + to.title +
                    "&content=" + to.desc +
                    "&src=yourCompanyName|yourAppName#Intent;" +
                    "scheme=bdapp;package=com.baidu.BaiduMap;end");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return intent;
    }


    /**
     * 打开文件
     *
     * @param file
     */
    public static Intent openFileIntent(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //设置intent的Action属性
        intent.setAction(Intent.ACTION_VIEW);
        //获取文件file的MIME类型
        String type = FileUtils.getMIMEType(file);
        //设置intent的data和Type属性。
        intent.setDataAndType(Uri.fromFile(file), type);
        return intent;
    }

    int PHOTO_REQUEST_GALLERY = 1;

    /*
    * 从相册获取
     */
    public void gallery(Activity context) {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        context.startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    //    ACTION_IMAGE_CAPTURE 拍摄一张
    //ACTION_IMAGE_CAPTURE_SECURE 吊起相机
    //INTENT_ACTION_STILL_IMAGE_CAMERA
    public static Intent TakePhotoIntent(String abspath) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = Uri.fromFile(new File(abspath));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        return intent;
    }

    /**
     * 跳转应用商店.
     *
     * @param context   {@link Context}
     * @param appPkg    包名
     * @param marketPkg 应用商店包名
     * @return {@code true} 跳转成功 <br> {@code false} 跳转失败
     */
    public static Intent toMarket(Context context, String appPkg, String marketPkg) {
        Uri uri = Uri.parse("market://details?id=" + appPkg);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (marketPkg != null) {// 如果没给市场的包名，则系统会弹出市场的列表让你进行选择。
            intent.setPackage(marketPkg);
        }
        return intent;

    }
}
