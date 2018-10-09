package me.gujun.android.taggroup.demo.ui;

import com.classic.common.MultipleStatusView;
import com.gyf.barlibrary.ImmersionBar;

import me.gujun.android.taggroup.demo.R;
import me.gujun.android.taggroup.demo.base.BaseActivity;

public class SecondActivity extends BaseActivity {
    MultipleStatusView multipleStatusView;
    @Override
    public int getViewId() {
        return R.layout.activity_second;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        multipleStatusView =  findViewById(R.id.multiple_status_view);
        multipleStatusView.showEmpty();
        multipleStatusView.postDelayed(
                () -> multipleStatusView.showLoading()
                , 3000);
    }
}


