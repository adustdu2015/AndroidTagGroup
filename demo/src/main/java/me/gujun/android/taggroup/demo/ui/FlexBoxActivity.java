package me.gujun.android.taggroup.demo.ui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.demo.R;
import me.gujun.android.taggroup.demo.adapter.RecyclerViewSnapHelpAdapter;
import me.gujun.android.taggroup.demo.util.DensityUtil;

public class FlexBoxActivity extends AppCompatActivity {

    @BindView(R.id.flexbox_layout)
    FlexboxLayout flexboxLayout;
    @BindView(R.id.emptyView)
    QMUIEmptyView emptyView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RecyclerViewSnapHelpAdapter adapter;

    private Context mContext;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_box);
        ButterKnife.bind(this);
        mContext = this;
        // 通过代码向FlexboxLayout添加View
        TextView textView = new TextView(this);
        textView.setText("TestLabel");
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundResource(R.drawable.label_bg_shape);
        textView.setPadding(DensityUtil.dip2px(mContext, 15), 0, DensityUtil.dip2px(mContext, 15), 0);
        textView.setTextColor(getResources().getColor(R.color.text_color));
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, DensityUtil.dip2px(mContext, 40)));
        flexboxLayout.addView(textView);
        //通过FlexboxLayout.LayoutParams 设置子元素支持的属性
//        ViewGroup.LayoutParams params = textView.getLayoutParams();
//        if(params instanceof FlexboxLayout.LayoutParams){
//            FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) params;
//            layoutParams.setFlexBasisPercent(0.5f);
//        }

        adapter = new RecyclerViewSnapHelpAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        emptyView.setLoadingShowing(true);
        List<String> list = new ArrayList<>();
        for(int i =0 ;i <10 ;i ++){
            list.add("测试数据:"+i);
        }


        refreshLayout.setOnRefreshListener(refreshlayout -> {
            Toast.makeText(mContext,"刷新完成",Toast.LENGTH_SHORT).show();
            adapter.addData(list);
            refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
        });
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            Toast.makeText(mContext,"载入更多",Toast.LENGTH_SHORT).show();
            adapter.addData(list);
            refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
        });
        //添加redreshLayout的自动刷新功能
        refreshLayout.autoRefresh();

    }

}
