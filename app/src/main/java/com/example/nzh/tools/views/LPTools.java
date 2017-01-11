package com.example.nzh.tools.views;

import android.view.ViewGroup;

import com.example.nzh.commons.Constants;

/**
 * Created by NIZHIHE on 2017/1/10.
 */

public class LPTools {

    public static ViewGroup.LayoutParams generateLayoutParams(int width, int height) {
        return new ViewGroup.LayoutParams(width, height);
    }

    /**
     * new ViewGroup.LayoutParams(
     * ViewGroup.LayoutParams.MATCH_PARENT,
     * ViewGroup.LayoutParams.WRAP_CONTENT);
     *
     * @return
     */
    public static ViewGroup.LayoutParams generateMW() {
        return generateLayoutParams(Constants.MP, Constants.WC);
    }

    /**
     * new ViewGroup.LayoutParams(
     * ViewGroup.LayoutParams.WRAP_CONTENT,
     * iewGroup.LayoutParams.WRAP_CONTENT
     *
     * @return
     */
    public static ViewGroup.LayoutParams generateWW() {
        return generateLayoutParams(Constants.WC, Constants.WC);
    }

    /**
     * new ViewGroup.LayoutParams(
     * ViewGroup.LayoutParams.MATCH_PARENT,
     * ViewGroup.LayoutParams.MATCH_PARENT);
     *
     * @return
     */
    public static ViewGroup.LayoutParams generateMM() {
        return generateLayoutParams(Constants.MP, Constants.MP);
    }
}
