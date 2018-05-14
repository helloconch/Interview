package com.conch.tiku.utils;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class AppUtils {
    public static int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
