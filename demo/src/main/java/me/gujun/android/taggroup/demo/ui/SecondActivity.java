package me.gujun.android.taggroup.demo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.classic.common.MultipleStatusView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.demo.R;

public class SecondActivity extends AppCompatActivity {

    MultipleStatusView multipleStatusView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        multipleStatusView = findViewById(R.id.multiple_status_view);
        multipleStatusView.showEmpty();
        multipleStatusView.postDelayed(
                () -> multipleStatusView.showLoading()
                , 3000);
    }

}
