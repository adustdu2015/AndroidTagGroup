package me.gujun.android.taggroup.demo.service.presenter;

import android.content.Intent;

import me.gujun.android.taggroup.demo.service.view.View;

public interface Presenter {
    void onCreate();
    void onStop();
    void attachView(View view);
}
