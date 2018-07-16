package me.gujun.android.taggroup.demo;

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
import com.jacksen.taggroup.ITagBean;
import com.jacksen.taggroup.SuperTagGroup;
import com.jacksen.taggroup.SuperTagUtil;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.gujun.android.taggroup.TagGroup;
import me.gujun.android.taggroup.demo.db.TagsManager;
import me.gujun.android.taggroup.demo.service.presenter.BookPresenter;
import me.gujun.android.taggroup.demo.service.view.BookView;
import me.gujun.android.taggroup.demo.ui.FlexBoxActivity;
import me.gujun.android.taggroup.demo.util.IconfontLayoutFactory;
import me.gujun.android.taggroup.demo.util.RxBus;
import me.next.tagview.TagCloudView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xyz.bboylin.universialtoast.UniversalToast;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.btn_flex)
    Button btnFlex;
    private TextView mPromptText;

    private TagGroup mDefaultTagGroup;
    private TagGroup mSmallTagGroup;
    private TagGroup mLargeTagGroup;
    private TagGroup mBeautyTagGroup;
    private TagGroup mBeautyInverseTagGroup;
    TagCloudView tag_cloud_view_8;
    private TagsManager mTagsManager;
    Button btn_login;
    List<String> aTag = new ArrayList<>();


    private BookPresenter bookPresenter = new BookPresenter(this);

    @BindView(R.id.tag_groups)
    public SuperTagGroup tag_groups;
    private TagGroup.OnTagClickListener mTagClickListener = new TagGroup.OnTagClickListener() {
        @Override
        public void onTagClick(String tag) {
            Toast.makeText(MainActivity.this, tag, Toast.LENGTH_SHORT).show();
        }
    };
    public static String EAT_MORE = "eat_more";
    private Context mContext;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        bookPresenter.onStop();
        RxBus.get().unregister(this);

    }

    public void addOneNewTag() {
        ITagBean.Builder builder = new ITagBean.Builder();
        ITagBean tagBean = builder.setTag("测试君")
                .setCornerRadius(SuperTagUtil.dp2px(this, 0.5f))
                .setHorizontalPadding(SuperTagUtil.dp2px(this, 10))
                .setVerticalPadding(SuperTagUtil.dp2px(this, 4))
                .setBgColor(Color.parseColor("#4bc0c3"))
                .create();
        tag_groups.appendTag(tagBean);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconfontLayoutFactory(this, getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarColor(R.color.colorPrimary).
                init(); //初始化，默认透明状态栏和黑色导航栏

//        addOneNewTag();

        mContext = this;
        RxBus.get().register(this);
        btn_login = findViewById(R.id.btn_login);

        for (int i = 0; i < 20; i++) {
            aTag.add("标签" + i);
        }
        tag_cloud_view_8 = findViewById(R.id.tag_cloud_view_8);
        tag_cloud_view_8.setTags(aTag);

        RxView.clicks(btn_login).throttleFirst(1, TimeUnit.SECONDS)//防抖操作
                .subscribe(v -> {

                    bookPresenter.getSearchBooks("金瓶梅", null, 0, 1);
                    UniversalToast.makeText(mContext, "点击了按钮", UniversalToast.LENGTH_SHORT).show();
                });

        bookPresenter.onCreate();
        bookPresenter.attachView(mBookView);

        mTagsManager = TagsManager.getInstance(getApplicationContext());
        String[] tags = mTagsManager.getTags();

        mPromptText = (TextView) findViewById(R.id.tv_prompt);
        mPromptText.setVisibility((tags == null || tags.length == 0) ? View.VISIBLE : View.GONE);
        mPromptText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchTagEditorActivity();
            }
        });

        mDefaultTagGroup = (TagGroup) findViewById(R.id.tag_group);
        mSmallTagGroup = (TagGroup) findViewById(R.id.tag_group_small);
        mLargeTagGroup = (TagGroup) findViewById(R.id.tag_group_large);
        mBeautyTagGroup = (TagGroup) findViewById(R.id.tag_group_beauty);
        mBeautyInverseTagGroup = (TagGroup) findViewById(R.id.tag_group_beauty_inverse);
        if (tags != null && tags.length > 0) {
            mDefaultTagGroup.setTags(tags);
            mSmallTagGroup.setTags(tags);
            mLargeTagGroup.setTags(tags);
            mBeautyTagGroup.setTags(tags);
            mBeautyInverseTagGroup.setTags(tags);
        }

        MyTagGroupOnClickListener tgClickListener = new MyTagGroupOnClickListener();

        mDefaultTagGroup.setOnClickListener(tgClickListener);
        mSmallTagGroup.setOnClickListener(tgClickListener);
        mLargeTagGroup.setOnClickListener(tgClickListener);
        mBeautyTagGroup.setOnClickListener(tgClickListener);
        mBeautyInverseTagGroup.setOnClickListener(tgClickListener);

        mDefaultTagGroup.setOnTagClickListener(mTagClickListener);
        mSmallTagGroup.setOnTagClickListener(mTagClickListener);
        mLargeTagGroup.setOnTagClickListener(mTagClickListener);
        mBeautyTagGroup.setOnTagClickListener(mTagClickListener);
        mBeautyInverseTagGroup.setOnTagClickListener(mTagClickListener);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                postEvent();
            }
        }, 2000);

    }

    private BookView mBookView = new BookView() {

        @Override
        public void success(Book book) {
            LogUtils.e(book);
        }

        @Override
        public void failed(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        String[] tags = mTagsManager.getTags();
        mPromptText.setVisibility((tags == null || tags.length == 0) ? View.VISIBLE : View.GONE);
        mDefaultTagGroup.setTags(tags);
        mSmallTagGroup.setTags(tags);
        mLargeTagGroup.setTags(tags);
        mBeautyTagGroup.setTags(tags);
        mBeautyInverseTagGroup.setTags(tags);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            launchTagEditorActivity();
            return true;
        }
        return false;
    }

    protected void launchTagEditorActivity() {
        Intent intent = new Intent(MainActivity.this, TagEditorActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.btn_next, R.id.btn_flex})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                startActivity(new Intent(mContext, SecondActivity.class));
                break;
            case R.id.btn_flex:
                startActivity(new Intent(mContext, FlexBoxActivity.class));
                break;
        }
    }

    class MyTagGroupOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            launchTagEditorActivity();
        }
    }

    public void postEvent() {
        RxBus.get().post("food");
        RxBus.get().post("tag_value", "return_value");
        RxBus.get().post(BusAction.EAT_TEST);
    }

    @Subscribe
    public void eat(String food) {
        Log.e("food", food);
    }

    @Subscribe(tags = {@Tag("tag_value")})
    public void testRxBus(String params) {
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


    void doHttp() {
        /**
         * 网络请求
         */
        Observable<Book> observable = new RetrofitHelper(mContext).getServer().getSearchBooks("金瓶梅", null, 0, 1);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Book>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Book book) {
                        LogUtils.e(book);
                    }
                });
    }

}

