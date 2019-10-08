package com.abc.simple.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.abc.simple.config.App;

/**
 * @name lz
 * @time 2019/7/18 16:20
 */
public class ToastUtils {

    public static void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Toast.makeText(App.getApp().getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
