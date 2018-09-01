package me.gujun.android.taggroup.demo.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.hwangjr.rxbus.annotation.Produce;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.gujun.android.taggroup.demo.R;
import me.gujun.android.taggroup.demo.action.BusAction;
import me.gujun.android.taggroup.demo.base.BaseActivity;
import me.gujun.android.taggroup.demo.db.TagsManager;
import me.gujun.android.taggroup.demo.model.Book;
import me.gujun.android.taggroup.demo.service.presenter.BookPresenter;
import me.gujun.android.taggroup.demo.service.view.BookView;
import me.gujun.android.taggroup.demo.util.IconfontLayoutFactory;
import me.gujun.android.taggroup.demo.util.RxBus;
import xyz.bboylin.universialtoast.UniversalToast;


public class MainActivity extends BaseActivity implements BookView{
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.btn_flex)
    Button btnFlex;
    @BindView(R.id.btn_recycler)
    Button btn_recycler;
    TagsManager mTagsManager;
    @BindView(R.id.btn_login)
    Button btn_login;
    List<String> aTag = new ArrayList<>();
    private BookPresenter bookPresenter = new BookPresenter(this);

    public static String EAT_MORE = "eat_more";
    private Context mContext;
    @Override
    public int getViewId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initViews() {
        mContext = this;
        RxBus.get().register(this);
        bookPresenter.onCreate();
        bookPresenter.attachView(this);
        //rxbinding 防抖操作
        RxView.clicks(btn_login).throttleFirst(1, TimeUnit.SECONDS)//防抖操作
                .subscribe(v -> {
                    bookPresenter.getSearchBooks("金瓶梅", null, 0, 1);

                });
        mTagsManager = TagsManager.getInstance(getApplicationContext());
        new Handler().postDelayed(() -> postEvent(), 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            return true;
        }
        return false;
    }

    @OnClick({R.id.btn_next, R.id.btn_flex,R.id.btn_recycler})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                startActivity(new Intent(mContext, SecondActivity.class));
                break;
            case R.id.btn_flex:
                startActivity(new Intent(mContext, FlexBoxActivity.class));
                break;
            case R.id.btn_recycler:
                startActivity(new Intent(mContext, RecyclerViewSnapHelpActivity.class));
                break;
        }
    }

    @Override
    public void success(Book book) {
        LogUtils.e(book);
        UniversalToast.makeText(mContext, book.getBooks().get(0).getAuthor().get(0), UniversalToast.LENGTH_SHORT).show();
    }

    @Override
    public void failed(String result) {
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
    }

    public void postEvent() {
        RxBus.get().post("food");
        RxBus.get().post("tag_value", "return_value");
        RxBus.get().post(BusAction.EAT_TEST);
    }

    @Subscribe
    public void eat(String food) {
        LogUtils.e("内容:"+food);
        Log.e("food", food);
    }

    @Subscribe(tags = {@Tag("tag_value")})
    public void testRxBus(String params) {
        LogUtils.e("返回内容:"+params);
        LogUtils.e(params.equals("return_value"));
        LogUtils.e("返回内容:"+params);
        Log.e("aa", params);
    }

    @Produce(thread = EventThread.IO, tags = {@Tag(BusAction.EAT_TEST)})
    public ArrayList produceMoreFood() {
        ArrayList list = new ArrayList<>();
        list.add("This");
        list.add("is");
        list.add("breads!");
        return list;
    }

    @Subscribe(thread = EventThread.MAIN_THREAD, tags = {@Tag(BusAction.EAT_TEST)})
    public void eatMore(ArrayList foods) {
        // purpose
        Log.e("foods", foods.size() + "");
        Toast.makeText(this, "eatMore", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bookPresenter.onStop();
        RxBus.get().unregister(this);
    }


}

