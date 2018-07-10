package com.android.tag.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.android.tag.di.module.MainModule;

import com.jess.arms.di.scope.ActivityScope;
import com.android.tag.mvp.ui.activity.MainActivity;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}