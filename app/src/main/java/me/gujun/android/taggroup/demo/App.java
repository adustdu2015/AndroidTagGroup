package me.gujun.android.taggroup.demo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        Fresco.initialize(this);
    }
}
