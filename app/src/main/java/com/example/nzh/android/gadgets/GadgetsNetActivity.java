package com.example.nzh.android.gadgets;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nzh.commons.Constants;
import com.example.nzh.tools.AlertTools;
import com.example.nzh.tools.NetTools;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.widget.Toast.makeText;

/**
 * Created by NIZHIHE on 2017/1/6.
 */
public class GadgetsNetActivity extends Activity implements View.OnClickListener {

    private Button checkNetworkStateBtn;
    private Button openSettingBtn;
    private Button checkAndOpenNetworkBtn;
    private TextView showNetworkStateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);

        checkNetworkStateBtn = new Button(this);
        checkNetworkStateBtn.setText("检查网络状态");
        checkNetworkStateBtn.setOnClickListener(this);
        root.addView(checkNetworkStateBtn, new ViewGroup.LayoutParams(Constants.MP, Constants.WC));
        showNetworkStateTxt = makeTextView(this, "NetTools.checkNetworkState(this)");
        root.addView(showNetworkStateTxt, new ViewGroup.LayoutParams(Constants.MP, Constants.WC));


        openSettingBtn = makeButton(this, "打开网络设置界面", this);
        root.addView(openSettingBtn, new ViewGroup.LayoutParams(Constants.MP, Constants.WC));

        checkAndOpenNetworkBtn = makeButton(this, "检查网络状态,无网络提示", this);
        root.addView(checkAndOpenNetworkBtn, new ViewGroup.LayoutParams(Constants.MP, Constants.WC));

        root.setLayoutParams(new ViewGroup.LayoutParams(Constants.MP, Constants.MP));
        setContentView(root);
    }

    public Button makeButton(Context context, String text, View.OnClickListener listener) {
        Button button = new Button(context);
        button.setText(text);
        button.setOnClickListener(listener);
        return button;
    }

    public TextView makeTextView(Context context, String text){
        TextView tv = new TextView(context);
        tv.setText(text);
        return tv;
    }


    @Override
    public void onClick(View v) {
        if (v == checkNetworkStateBtn) {
            String msg = "网络不可用";
            if (NetTools.checkNetworkState(this)) {
                msg = "网络可用";
            }
            AlertTools.makeToast(this, msg);
        }

        if (v == openSettingBtn) {
            NetTools.openNetworkSetting(this);
        }

        if (v == checkAndOpenNetworkBtn) {
            if (!NetTools.checkNetworkState(this)) {
                NetTools.openNetworkSetting(this);
            }else{
                AlertTools.makeToast(this, "网络可用");
            }
        }
    }
}
