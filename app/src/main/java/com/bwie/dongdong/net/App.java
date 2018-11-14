package com.bwie.dongdong.net;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HttpHelper.getInstents().init("http://www.zhaoapi.cn/");
    }
}
