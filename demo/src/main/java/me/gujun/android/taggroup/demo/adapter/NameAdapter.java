package me.gujun.android.taggroup.demo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.gujun.android.taggroup.demo.R;

public class NameAdapter extends BaseQuickAdapter<Home.HomeItem, BaseViewHolder> {
   public NameAdapter(int layoutResId, List data) {
	  super(layoutResId, data);
   }

   @Override
   protected void convert(BaseViewHolder helper, Home.HomeItem item) {
	  helper.setText(R.id.text, item.getName());
   }
}

