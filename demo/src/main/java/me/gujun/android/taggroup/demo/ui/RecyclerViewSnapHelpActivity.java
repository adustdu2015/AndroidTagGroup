package me.gujun.android.taggroup.demo.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.demo.R;
import me.gujun.android.taggroup.demo.adapter.RecyclerViewSnapHelpAdapter;

public class RecyclerViewSnapHelpActivity extends AppCompatActivity {
    @BindView(R.id.rv_container)
    RecyclerView rv_container;
    private Context mContext;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_snap_help);
        ButterKnife.bind(this);
        mContext = this;
        RecyclerViewSnapHelpAdapter adapter = new RecyclerViewSnapHelpAdapter(mContext);
        for (int i = 0; i < 18; i++) {
            list.add("单步渐进测试数据" + i);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rv_container.setLayoutManager(linearLayoutManager);
        rv_container.setAdapter(adapter);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(rv_container);
        new Handler().postDelayed(() ->
                adapter.addData(list), 1000);
    }
}
