package me.gujun.android.taggroup.demo.base;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.apkfuns.logutils.LogUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.ButterKnife;
import me.gujun.android.taggroup.demo.R;
import me.gujun.android.taggroup.demo.util.IconfontLayoutFactory;
import me.gujun.android.taggroup.demo.util.NetWorkStateReceiver;

public abstract class BaseActivity  extends AppCompatActivity{
    private ImmersionBar mImmersionBar;
    //网络状态监听库
    NetWorkStateReceiver netWorkStateReceiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        iconfont的统一使用
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconfontLayoutFactory(this, getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(getViewId());
        mImmersionBar = ImmersionBar.with(this).statusBarColor(R.color.colorPrimary);
        mImmersionBar.init();   //所有子类都将继承这些相同的属性
        ButterKnife.bind(this);
        initViews();

    }
    public abstract int getViewId();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }
    protected abstract void initViews();
    @Override
    protected void onResume() {
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        LogUtils.e("注册");
        super.onResume();
    }

    //onPause()方法注销
    @Override
    protected void onPause() {
        unregisterReceiver(netWorkStateReceiver);
        LogUtils.e("注销");
        super.onPause();
    }

}
