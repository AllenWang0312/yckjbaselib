package com.yckj.baselib.util;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.util.List;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/30 0030 上午 11:03
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class SystemUtils {

    public static boolean isInstallApk(Context context, String name) {
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            if (packageInfo.packageName.equals(name)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }
}
