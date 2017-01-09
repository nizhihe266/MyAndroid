package com.example.nzh.android.gadgets;

import android.app.Activity;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nzh.commons.Constants;
import com.example.nzh.tools.AlertTools;
import com.example.nzh.tools.NetTools;

import static android.widget.Toast.makeText;

/**
 * Created by NIZHIHE on 2017/1/6.
 */
public class GadgetsNetActivity extends Activity implements View.OnClickListener {

    Button checkNetworkStateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        LinearLayout root = new LinearLayout(this);

        checkNetworkStateBtn = new Button(this);
        checkNetworkStateBtn.setText("检查网络状态");
        checkNetworkStateBtn.setOnClickListener(this);
        root.addView(checkNetworkStateBtn, new ViewGroup.LayoutParams(Constants.MP, Constants.WC));


        root.setLayoutParams(new ViewGroup.LayoutParams(Constants.MP, Constants.MP));
        setContentView(root);
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
    }
}
