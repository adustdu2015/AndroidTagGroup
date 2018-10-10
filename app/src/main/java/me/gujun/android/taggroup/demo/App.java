package me.gujun.android.taggroup.demo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.kingja.loadsir.core.LoadSir;

import me.gujun.android.taggroup.demo.callback.CustomCallback;
import me.gujun.android.taggroup.demo.callback.EmptyCallback;
import me.gujun.android.taggroup.demo.callback.ErrorCallback;
import me.gujun.android.taggroup.demo.callback.LoadingCallback;
import me.gujun.android.taggroup.demo.callback.TimeoutCallback;
import me.gujun.android.taggroup.demo.core.preference.Preference;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        Fresco.initialize(this);
        Preference.Companion.setContext(this);
        //loadsir 初始化
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
//        工具类初始化
        Utils.init(this);

    }
    protected void attachBaseContext(Context base){

        super.attachBaseContext(base);

        MultiDex.install(this);

    }

}
