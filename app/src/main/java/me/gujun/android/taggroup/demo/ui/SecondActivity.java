package me.gujun.android.taggroup.demo.ui;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.classic.common.MultipleStatusView;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.demo.R;
import me.gujun.android.taggroup.demo.ui.fragment.FragmentOne;

public class SecondActivity extends AppCompatActivity {
    MultipleStatusView multipleStatusView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).init();
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        multipleStatusView = findViewById(R.id.multiple_status_view);
        multipleStatusView.showEmpty();
        multipleStatusView.postDelayed(
                () -> multipleStatusView.showLoading()
                , 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
