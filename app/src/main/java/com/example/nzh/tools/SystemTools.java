package com.example.nzh.tools;

import android.os.Build;

/**
 * 获取手机信息
 * Created by NIZHIHE on 2017/1/6.
 */

public class SystemTools {

    /**
     * 获取当前系统的版本号
     * @return
     */
    public static int getSDK(){
        return Build.VERSION.SDK_INT;
    }

}
