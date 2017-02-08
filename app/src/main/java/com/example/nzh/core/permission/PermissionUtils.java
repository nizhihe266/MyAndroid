package com.example.nzh.core.permission;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import com.example.nzh.tools.AppTools;
import com.example.nzh.tools.LogUtils;
import com.example.nzh.tools.SystemTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by NIZHIHE on 2017/2/7.
 */

public class PermissionUtils {

    public static final int CODE_PERMISSION = 1001;

    /**
     * 检查应用是否有需要授权的权限，有申请授权
     *
     * @param activity
     */
    public static void checkSelfPermissions(final Activity activity) {
        if (activity == null) {
            return;
        }

        List<String> notGranted = getNotGrantedPermissions(activity);
        if (notGranted.size() > 0) {
            requestMultiPermission(activity, notGranted.toArray(new String[notGranted.size()]));
        }
    }


    /**
     * 批量申请开通权限
     */
    public static void requestMultiPermission(final Activity activity, final String[] permissions) {

        if (activity == null || permissions.length == 0) {
            return;
        }

        if (checkShouldRationale(activity, permissions)) {
            openSettingActivity(activity, permissions);
        } else {
            ActivityCompat.requestPermissions(activity, permissions, CODE_PERMISSION);
        }
    }

    /**
     * 单个申请开通权限
     */
    public static void requestPermission(final Activity activity, final String permission) {
        requestMultiPermission(activity, new String[]{permission});
    }

    /**
     * 权限申请回调处理
     *
     * @param activity
     * @param permissions
     * @param grantResults
     */
    public static void requestPermissionsResult(Activity activity, String[] permissions, int[] grantResults, PermissionCallback callback) {
        List<String> notGranted = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                notGranted.add(permissions[i]);
            }
        }

        if (notGranted.size() == 0) {
            callback.permissionGranted(permissions);
        } else {
            openSettingActivity(activity, notGranted.toArray(new String[notGranted.size()]));
        }
    }

    /**
     * 打开应用权限设置
     *
     * @param activity
     * @param permissions
     */
    private static void openSettingActivity(final Activity activity, String[] permissions) {
        String groups = getPermissionGroups(activity, permissions);
        String msg = "当前应用需要授权以下权限\r\n" + groups;
        showMessageOKCancel(activity, msg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                activity.startActivity(intent);
            }
        });
    }

    /**
     * 显示消息弹窗
     *
     * @param context
     * @param message
     * @param okListener
     */
    private static void showMessageOKCancel(final Activity context, String message, DialogInterface.OnClickListener okListener) {
        new android.app.AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("确定", okListener)
                .setNegativeButton("取消", null)
                .create()
                .show();

    }

    /**
     * 检查是否存在用户不仅拒绝，并且勾选了对话框中”Don’t ask again”的选项的权限
     *
     * @param activity
     * @param permissions
     * @return true:存在，false:不存在
     */
    public static boolean checkShouldRationale(Activity activity, String[] permissions) {

        for (int i = 0; i < permissions.length; i++) {
            if (checkShouldRationale(activity, permissions[i])) {
                return true;
            }
        }
        return false;
    }


    /**
     * 检查权限是否被用户拒绝，并且勾选了对话框中”Don’t ask again”的选项
     *
     * @param activity
     * @param permission
     * @return true:是，false:否
     */
    public static boolean checkShouldRationale(Activity activity, String permission) {
        return !ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    /**
     * 获取所有未授权的权限
     *
     * @param activity
     * @return
     */
    public static List<String> getNotGrantedPermissions(Activity activity) {
        //获取当前应用的packageName
        String pkName = AppTools.getPackageName(activity);
        //获取当前应用声明了那些权限
        Map<String, String> permissions = getPermissions(activity, pkName);

        Iterator<Map.Entry<String, String>> iterator = permissions.entrySet().iterator();
        List<String> notGranted = new ArrayList<>();
        //验证那些权限没有开通
        while (iterator.hasNext()) {
            Map.Entry<String, String> permission = iterator.next();
            if (!checkSelfPermission(activity, permission.getValue())) {
                notGranted.add(permission.getValue());
            }
        }

        return notGranted;
    }

    /**
     * 检查特定权限是否开通
     *
     * @param activity
     * @param permission
     * @return true:SDK23以下检查结果都为真，23及23以上表示用户授权；false：用户没有授权
     */
    public static boolean checkSelfPermission(Activity activity, String permission) {
        //如果是6.0以下的手机，ActivityCompat.checkSelfPermission()会始终等于PERMISSION_GRANTED
        if (SystemTools.getSDK() < 23) {
            return true;
        }

        int checkSelfPermission;
        //如果用户关闭了你申请的权限，ActivityCompat.checkSelfPermission(),会导致程序崩溃(java.lang.RuntimeException: Unknown exception code: 1 msg null)，
        try {
            checkSelfPermission = ActivityCompat.checkSelfPermission(activity, permission);
        } catch (Exception e) {
            return false;
        }

        if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {
            //用户已经准许使用权限
            return true;
        } else {
            return false;
        }
    }


    /**
     * 查看Android应用所需的权限
     *
     * @param activity
     * @param pkName
     * @return
     */
    public static Map<String, String> getPermissions(Activity activity, String pkName) {
        Map<String, String> permissionGroup = new HashMap<>();
        try {
            PackageManager packageManager = activity.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(pkName, packageManager.GET_PERMISSIONS);
            String[] permissions = packageInfo.requestedPermissions;
            for (int i = 0; i < permissions.length; i++) {
                //获取权限名字
                String permission = permissions[i];

                //获取权限详细信息
                PermissionInfo permissionInfo = packageManager.getPermissionInfo(permission, 0);

                try {
                    //获取权限所属于的组别
                    PermissionGroupInfo permissionGroupInfo = packageManager.getPermissionGroupInfo(permissionInfo.group, 0);
                    String permissionGroupLabel = permissionGroupInfo.loadLabel(packageManager).toString();
                    //String permissionGroupDescription = permissionGroupInfo.loadDescription(packageManager).toString();
                    if (!permissionGroup.containsKey(permissionGroupLabel)) {
                        permissionGroup.put(permissionGroupLabel, permission);
                    }
                } catch (Exception e) {

                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return permissionGroup;
    }

    /**
     * 根据权限获取所在组
     *
     * @param activity
     * @param permission
     * @return
     */
    public static String getPermissionGroup(Activity activity, String permission) {
        try {
            PackageManager packageManager = activity.getPackageManager();
            PermissionInfo permissionInfo = packageManager.getPermissionInfo(permission, 0);
            LogUtils.i("permissionInfo.group==" + permissionInfo.group);
            PermissionGroupInfo permissionGroupInfo = packageManager.getPermissionGroupInfo(permissionInfo.group, 0);
            return permissionGroupInfo.loadLabel(packageManager).toString();
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 根据权限获取所在组
     * @param activity
     * @param permissions
     * @return
     */
    public static String getPermissionGroups(Activity activity, String[] permissions) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < permissions.length; i++) {
            String group = getPermissionGroup(activity, permissions[i]);
            if (group != null) {
                sb.append(getGroupName(group)).append(",");
            }
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        } else {
            return null;
        }
    }

    /**
     * 将权限组转成中文
     * @param group
     * @return
     */
    public static String getGroupName(String group) {
        if ("CONTACTS".equals(group.toUpperCase())) {
            return "联系人";
        } else if ("PHONE".equals(group.toUpperCase())) {
            return "手机拨号";
        } else if ("CALENDAR".equals(group.toUpperCase())) {
            return "日历";
        } else if ("CAMERA".equals(group.toUpperCase())) {
            return "相机";
        } else if ("SENSORS".equals(group.toUpperCase())) {
            return "传感器";
        } else if ("LOCATION".equals(group.toUpperCase())) {
            return "定位";
        } else if ("STORAGE".equals(group.toUpperCase())) {
            return "内存卡";
        } else if ("MICROPHONE".equals(group.toUpperCase())) {
            return "麦克风";
        } else if ("SMS".equals(group.toUpperCase())) {
            return "短信";
        } else {
            return "";
        }
    }


}
