package me.gujun.android.taggroup.demo.ui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.demo.R;
import me.gujun.android.taggroup.demo.adapter.RecyclerViewSnapHelpAdapter;
import me.gujun.android.taggroup.demo.base.BaseActivity;
import me.gujun.android.taggroup.demo.util.DensityUtil;

public class FlexBoxActivity extends BaseActivity {

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
    @BindView(R.id.vp_one)
    ViewPager viewPager;
    @Override
    public int getViewId() {
        return R.layout.activity_flex_box;
    }
    @Override
    protected void initViews() {
        mContext = this;
        ImmersionBar.with(this).init();

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
        Context context = this;
        context.getDir("",0).list(new filenameFilter());
    }
    static class filenameFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            if (name.toLowerCase().endsWith(".hs")) {
                return true;
            }
            return false;
        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }


}
