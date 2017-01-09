package com.example.nzh.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by NIZHIHE on 2017/1/5.
 */
public class CollectionTools {

    public static Map<String, Object> generateMap(String[] keys, Object[] values) {

        if (keys == null || values == null) {
            return null;
        }

        if (keys.length != values.length) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }

}