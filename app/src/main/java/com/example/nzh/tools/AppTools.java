package com.example.nzh.tools;

import android.app.Activity;
import android.content.pm.PackageInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取APP信息
 * <p>
 * packageName、versionName、versionCode
 * <p>
 * Created by NIZHIHE on 2017/2/8.
 */
public class AppTools {
    /**
     * 获取packageName
     */
    public static String getPackageName(Activity activity) {
        Object obj = getAppInfo(activity).get(PACKAGE_NAME);
        return obj == null ? null : (String) obj;
    }

    /**
     * 获取versionName
     */
    public static String getVersionName(Activity activity) {
        Object obj = getAppInfo(activity).get(VERSION_NAME);
        return obj == null ? null : (String) obj;
    }

    /**
     * 获取versionCode
     */
    public static int getVersionCode(Activity activity) {
        Object obj = getAppInfo(activity).get(VERSION_CODE);
        return obj == null ? 0 : (int) obj;
    }


    public static final String PACKAGE_NAME = "PACKAGE_NAME";
    public static final String VERSION_NAME = "VERSION_NAME";
    public static final String VERSION_CODE = "VERSION_CODE";

    public static Map<String, Object> getAppInfo(Activity activity) {
        Map<String, Object> appInfo = new HashMap<>();
        try {
            String pkName = activity.getPackageName();
            PackageInfo pkInfo = activity.getPackageManager().getPackageInfo(pkName, 0);
            String versionName = pkInfo.versionName;
            int versionCode = pkInfo.versionCode;

            appInfo.put(PACKAGE_NAME, pkName);
            appInfo.put(VERSION_NAME, versionName);
            appInfo.put(VERSION_CODE, versionCode);
        } catch (Exception e) {

        }
        return appInfo;
    }
}
