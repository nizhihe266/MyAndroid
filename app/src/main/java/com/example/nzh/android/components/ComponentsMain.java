package com.example.nzh.android.components;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.nzh.R;
import com.example.nzh.android.components.activity.CompActivityMain;
import com.example.nzh.android.components.broadcastreceive.CompBroadcastReceiverMain;
import com.example.nzh.android.components.contentprovider.CompContentProviderMain;
import com.example.nzh.android.components.service.CompServiceMain;
import com.example.nzh.commons.Constants;
import com.example.nzh.tools.CollectionTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by NIZHIHE on 2017/1/5.
 */

public class ComponentsMain extends Activity {
    private static List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();

    static {
        mData.add(CollectionTools.generateMap(new String[]{"title", "desc", "target"}, new Object[]{"Activity", "", CompActivityMain.class}));
        mData.add(CollectionTools.generateMap(new String[]{"title", "desc", "target"}, new Object[]{"Service", "", CompServiceMain.class}));
        mData.add(CollectionTools.generateMap(new String[]{"title", "desc", "target"}, new Object[]{"Content Provider", "", CompContentProviderMain.class}));
        mData.add(CollectionTools.generateMap(new String[]{"title", "desc", "target"}, new Object[]{"BroadcastReceiver", "", CompBroadcastReceiverMain.class}));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

                startActivity(new Intent(ComponentsMain.this, clz));
            }
        });
    }
}
