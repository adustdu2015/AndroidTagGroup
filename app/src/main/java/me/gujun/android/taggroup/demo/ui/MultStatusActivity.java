package me.gujun.android.taggroup.demo.ui;

import com.blankj.utilcode.util.NetworkUtils;
import com.classic.common.MultipleStatusView;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import me.gujun.android.taggroup.demo.R;
import me.gujun.android.taggroup.demo.base.BaseActivity;

public class MultStatusActivity extends BaseActivity {
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    @Override
    public int getViewId() {
        return R.layout.activity_second;
    }
    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        multipleStatusView.showLoading();
        multipleStatusView.postDelayed(
                () -> {
                    multipleStatusView.showError();
                    if(NetworkUtils.isConnected()){
                        multipleStatusView.showContent();
                    }
                }
                , 3000);
    }
}


