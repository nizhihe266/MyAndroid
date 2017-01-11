package com.example.nzh.tools.views;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by NIZHIHE on 2017/1/10.
 */

public class BasicViewTools {
    public static Button makeButton(Context context, String text, View.OnClickListener listener) {
        Button button = new Button(context);
        button.setText(text);
        button.setOnClickListener(listener);
        return button;
    }

    public static TextView makeTextView(Context context, String text) {
        TextView tv = new TextView(context);
        tv.setText(text);
        return tv;
    }
}
