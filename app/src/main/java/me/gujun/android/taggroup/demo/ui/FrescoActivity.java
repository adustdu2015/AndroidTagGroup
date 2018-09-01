package me.gujun.android.taggroup.demo.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.apkfuns.logutils.Constant;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.demo.R;
import me.gujun.android.taggroup.demo.base.BaseActivity;

public class FrescoActivity extends BaseActivity {

    @BindView(R.id.my_image_view)
    SimpleDraweeView myImageView;
    @BindView(R.id.iv_picasso)
    ImageView iv_picasso;
    private Context mContext;

    @Override
    public int getViewId() {
        return R.layout.activity_fresco;
    }

    @Override
    protected void initViews() {
        mContext = this;
        Uri uri = Uri.parse("https://avatars0.githubusercontent.com/u/17478136?s=460&v=4");
        myImageView.setImageURI(uri);
        Picasso.with(mContext).load("https://avatars0.githubusercontent.com/u/17478136?s=460&v=4")
                .into(iv_picasso);
    }
}
