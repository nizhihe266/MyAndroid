package com.example.nzh.android.gadgets;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nzh.commons.Constants;
import com.example.nzh.tools.AlertTools;
import com.example.nzh.tools.views.BasicViewTools;
import com.example.nzh.tools.NetTools;
import com.example.nzh.tools.views.LPTools;

import static android.widget.Toast.makeText;

/**
 * Created by NIZHIHE on 2017/1/6.
 */
public class GadgetsNetActivity extends Activity implements View.OnClickListener {

    private Button checkNetworkStateBtn;
    private Button openSettingBtn;
    private Button checkAndOpenNetworkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);

        root.addView(BasicViewTools.makeTextView(this, "相关权限:" +
                "\r\n1、获取网络状态\r\nandroid.permission.ACCESS_NETWORK_STATE" +
                "\r\n核心内容:" +
                "\r\n1、获取网络连接相关的管理器" +
                "\r\nConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);" +
                "\r\n2、获取当前网络状态:" +
                "\r\nmanager.getActiveNetworkInfo().isAvailable();" +
                "\r\n相关工具类:\r\nNetTools"+
                "\r\n参考资料:" +
                "\r\n1、http://blog.csdn.net/u012547790/article/details/49127031"), LPTools.generateMW());

        checkNetworkStateBtn = BasicViewTools.makeButton(this, "检查网络状态", this);
        root.addView(checkNetworkStateBtn, LPTools.generateMW());
        root.addView(BasicViewTools.makeTextView(this, "NetTools.checkNetworkState(this);"), LPTools.generateMW());


        openSettingBtn = BasicViewTools.makeButton(this, "打开网络设置界面", this);
        root.addView(openSettingBtn, LPTools.generateMW());
        root.addView(BasicViewTools.makeTextView(this, "NetTools.openNetworkSetting(this);"), LPTools.generateMW());

        checkAndOpenNetworkBtn = BasicViewTools.makeButton(this, "检查网络状态,无网络提示", this);
        root.addView(checkAndOpenNetworkBtn, LPTools.generateMW());

        root.setLayoutParams(LPTools.generateMM());
        setContentView(root);
    }

    @Override
    public void onClick(View v) {
        if (v == checkNetworkStateBtn) {
            String msg = "网络不可用";
            if (NetTools.isNetworkAvailable(this)) {
                msg = "网络可用";
            }
            AlertTools.makeToast(this, msg);
        }

        if (v == openSettingBtn) {
            NetTools.openNetworkSetting(this);
        }

        if (v == checkAndOpenNetworkBtn) {
            if (!NetTools.isNetworkAvailable(this)) {
                NetTools.promptNetworkSetting(this);
            } else {
                AlertTools.makeToast(this, "网络可用");
            }
        }
    }
}
