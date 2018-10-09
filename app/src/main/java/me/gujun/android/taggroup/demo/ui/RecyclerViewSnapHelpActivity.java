package me.gujun.android.taggroup.demo.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
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
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean loading = true;
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

        rv_container.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                visibleItemCount = linearLayoutManager.getChildCount(); //子数
                totalItemCount = linearLayoutManager.getItemCount(); // item总数
                pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition(); //当前屏幕 首个 可见的 Item 的position
                LogUtils.e("当前屏幕 可见的 Item 个数:"+visibleItemCount+",Item总共的个:"+totalItemCount+",当前屏幕 首个 可见的 Item 的position"+pastVisiblesItems);

                if (loading) {
                    if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        // 判断点
                        loading = false;
                        LogUtils.e("这是最后一个item啦");
                    }
                }

            }
        });
    }
}
