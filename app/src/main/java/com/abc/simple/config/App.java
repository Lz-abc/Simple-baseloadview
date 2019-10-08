package com.abc.simple.config;

import android.app.Application;

/**
 * @name lz
 * @time 2019/3/13 14:34
 */
public class App extends Application {
    public static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static App getApp() {
        return mApp;
    }
}
