package me.gujun.android.taggroup.demo.ui.bottom;
/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.NetworkUtils;
import com.bumptech.glide.Glide;
import com.classic.common.MultipleStatusView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.gujun.android.taggroup.demo.R;
import me.gujun.android.taggroup.demo.base.BaseActivity;
import wseemann.media.FFmpegMediaMetadataRetriever;

public class BottomTestActivity extends BaseActivity {
    @BindView(R.id.btn_frame)
    Button btnFrame;
    @BindView(R.id.iv_frame)
    ImageView ivFrame;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    private Context mContext;
    private RxPermissions premission;

    @Override
    public int getViewId() {
        return R.layout.activity_bottom_test;
    }

    @Override
    protected void initViews() {
        mContext = this;
        multipleStatusView.showLoading();
        premission = new RxPermissions(this);
        multipleStatusView.postDelayed(() -> {
            if (NetworkUtils.isConnected()) {
                multipleStatusView.showContent();
            } else {
                multipleStatusView.showError();
            }
        }, 1000);
    }

    @OnClick(R.id.btn_frame)
    public void onViewClicked() {

        premission.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    FFmpegMediaMetadataRetriever retriever = new FFmpegMediaMetadataRetriever();
                    try {
//                        retriever.setDataSource("/storage/emulated/0/test.mp4"); //file's path
                        retriever.setDataSource("http://xyj.xueyijia.com.cn//2018/7/14/%E6%89%93%E9%A2%86%E5%B8%A6%E7%9A%84%E7%8B%90%E7%8B%B8%E5%B0%BC%E5%85%8B_2018_08_14_19_02_52.mp4"); //file's path
                        Bitmap bitmap = retriever.getFrameAtTime(100000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                        Glide.with(mContext).load(drawable).into(ivFrame);

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        retriever.release();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
