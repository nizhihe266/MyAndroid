package com.example.nzh;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.nzh.android.animation.AnimationMain;
import com.example.nzh.android.components.ComponentsMain;
import com.example.nzh.android.example.ExampleMain;
import com.example.nzh.android.gadgets.GadgetsMain;
import com.example.nzh.android.others.OthersMain;
import com.example.nzh.android.views.ViewsMain;
import com.example.nzh.commons.Constants;
import com.example.nzh.android.third.ThirdPartyLibraryMain;
import com.example.nzh.core.permission.PermissionCallback;
import com.example.nzh.core.permission.PermissionUtils;
import com.example.nzh.framework.FrameworkMain;
import com.example.nzh.tools.AlertTools;
import com.example.nzh.tools.CollectionTools;
import com.example.nzh.tools.LogUtils;
import com.example.nzh.tools.SystemTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by NIZHIHE on 2016/12/30.
 */

public class MainActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback, PermissionCallback {

    private static List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();

    static {
        mData.add(CollectionTools.generateMap(new String[]{"title", "desc", "target"}, new Object[]{"四大组件", "", ComponentsMain.class}));
        mData.add(CollectionTools.generateMap(new String[]{"title", "desc", "target"}, new Object[]{"基本视图", "", ViewsMain.class}));
        mData.add(CollectionTools.generateMap(new String[]{"title", "desc", "target"}, new Object[]{"动画", "", AnimationMain.class}));
        mData.add(CollectionTools.generateMap(new String[]{"title", "desc", "target"}, new Object[]{"小工具", "", GadgetsMain.class}));
        mData.add(CollectionTools.generateMap(new String[]{"title", "desc", "target"}, new Object[]{"实例", "", ExampleMain.class}));
        mData.add(CollectionTools.generateMap(new String[]{"title", "desc", "target"}, new Object[]{"第三方类库", "", ThirdPartyLibraryMain.class}));
        mData.add(CollectionTools.generateMap(new String[]{"title", "desc", "target"}, new Object[]{"框架", "", FrameworkMain.class}));
        mData.add(CollectionTools.generateMap(new String[]{"title", "desc", "target"}, new Object[]{"其他", "", OthersMain.class}));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // LogUtils.v(""+(1/0));

        LogUtils.w("1*****************************************1"+ SystemTools.getSDKName());

        PermissionUtils.checkSelfPermissions(this);

        LogUtils.w("1*****************************************1");


        LinearLayout root = new LinearLayout(this);

        ListView lv = new ListView(this);
        SimpleAdapter adapter = new SimpleAdapter(this, mData, R.layout.aty_main_module_lv_item,
                new String[]{"title", "desc"}, new int[]{R.id.aty_main_title, R.id.aty_main_desc});
        lv.setAdapter(adapter);
        root.addView(lv, new ViewGroup.LayoutParams(Constants.MP, Constants.MP));

        root.setLayoutParams(new LinearLayout.LayoutParams(Constants.MP, Constants.MP));
        setContentView(root);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> data = mData.get(position);
                Class clz = (Class) data.get("target");

                startActivity(new Intent(MainActivity.this, clz));
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionUtils.requestPermissionsResult(this, permissions, grantResults, this);
    }

    @Override
    public void permissionGranted(String[] permissions) {
        String groups = PermissionUtils.getPermissionGroups(this, permissions);
        AlertTools.makeToast(this, groups + "以上权限已被成功授权");
    }
}
