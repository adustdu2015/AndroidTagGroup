package me.gujun.android.taggroup.demo.util;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class IconfontLayoutFactory implements LayoutInflaterFactory {
   private Typeface mTypeface;
   private AppCompatDelegate mAppCompatDelegate;

   public IconfontLayoutFactory( Context mContext, final AppCompatDelegate appCompatDelegate ) {
	  if ( mTypeface == null ) {
		 mTypeface = Typeface.createFromAsset(mContext.getAssets(), "iconfont.ttf");
		 mAppCompatDelegate = appCompatDelegate;
	  }
   }

   @Override
   public View onCreateView( final View parent, final String name, final Context context, final AttributeSet attrs ) {
	  View view = mAppCompatDelegate.createView(parent,name,context,attrs);
	  if(view instanceof TextView){
		 ((TextView)view).setTypeface(mTypeface);
	  }
	  return view;
   }
}
