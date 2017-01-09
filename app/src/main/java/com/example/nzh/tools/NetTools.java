package com.example.nzh.tools;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

/**
 * Created by NIZHIHE on 2017/1/6.
 */

public class NetTools {

    /**
     * 监测网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean checkNetworkState(Context context) {
        ConnectivityManager manager = getConnectivityManager(context);

        boolean flag = false;

        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }

        return flag;
    }

    /**
     * 网络没有连接，提示需要进行网络设置
     *
     * @param context
     */
    public static void promptNetworkSetting(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("网络提示");
        builder.setMessage("网络不可用，如果继续，请先设置网络！");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openNetworkSetting(context);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //TODO 无网情况可以选择退出APP
            }
        });
        builder.create();
        builder.show();
    }

    /**
     * 打开网络设置界面
     *
     * @param context
     */
    public static void openNetworkSetting(Context context) {
        Intent intent = new Intent();
        /**
         * 判断手机系统的版本！如果API大于10 就是3.0+
         * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
         */
        if (SystemTools.getAPI() > 10) {
            intent.setAction(Settings.ACTION_WIFI_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName(
                    "com.android.settings",
                    "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        context.startActivity(intent);
    }


    //判断网络类型

    //判断网络供应商

    //获取网络速度


    /**
     * 获取 ConnectivityManager
     *
     * @param context
     * @return
     */
    private static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}
