package com.example.nzh.core.crash;

import android.app.Application;

/**
 * Created by NIZHIHE on 2017/1/13.
 * <p>
 * 参考资料:
 * http://www.cnblogs.com/lee0oo0/archive/2012/11/28/2793052.html
 */

public class CrashApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
