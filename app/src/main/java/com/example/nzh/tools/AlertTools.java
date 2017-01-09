package com.example.nzh.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by NIZHIHE on 2017/1/6.
 */

public class AlertTools {

    /**
     * 显示提示信息
     * @param context
     * @param msg
     */
    public static void makeToast(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
