package me.gujun.android.taggroup.demo.callback;

import com.kingja.loadsir.callback.Callback;

import me.gujun.android.taggroup.demo.R;

public class EmptyCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_empty;
    }

}