package com.foxleezh.middleware;

import android.app.Application;
import android.content.Context;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍  组件化模组都以这个Application启动
 */

public class ModuleApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
