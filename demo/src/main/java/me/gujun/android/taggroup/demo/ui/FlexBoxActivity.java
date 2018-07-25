package me.gujun.android.taggroup.demo.ui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.qmuiteam.qmui.widget.QMUIEmptyView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.demo.R;
import me.gujun.android.taggroup.demo.util.DensityUtil;

public class FlexBoxActivity extends AppCompatActivity {

    @BindView(R.id.flexbox_layout)
    FlexboxLayout flexboxLayout;
    @BindView(R.id.emptyView)
    QMUIEmptyView emptyView;
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
        textView.setPadding(DensityUtil.dip2px(mContext,15),0,DensityUtil.dip2px(mContext,15),0);
        textView.setTextColor(getResources().getColor(R.color.text_color));
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,DensityUtil.dip2px(mContext,40)));
        flexboxLayout.addView(textView);
        //通过FlexboxLayout.LayoutParams 设置子元素支持的属性
//        ViewGroup.LayoutParams params = textView.getLayoutParams();
//        if(params instanceof FlexboxLayout.LayoutParams){
//            FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) params;
//            layoutParams.setFlexBasisPercent(0.5f);
//        }

        emptyView.setLoadingShowing(true);
    }

}
