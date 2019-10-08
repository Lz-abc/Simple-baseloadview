package com.abc.baseloadview.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 获取手机屏幕参数帮助类
 */

public class ScreenUtils {


    /**
     *  
     *  获取状态栏高度——方法
     */
    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = 0;
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelOffset(resourceId);
        }
        return statusBarHeight;
    }


    public static int getBarHeight(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }
        return 0;
    }

}
