package me.gujun.android.taggroup.demo.service.presenter;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;

import me.gujun.android.taggroup.demo.model.Book;
import me.gujun.android.taggroup.demo.service.manager.DataManager;
import me.gujun.android.taggroup.demo.service.view.BookView;
import me.gujun.android.taggroup.demo.service.view.View;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 请求book的presenter,继承子Presenter
 * 主要implements三个方法
 * onCreate用于初始化数据内容
 * onStop  如果Activity finish啦，则将订阅事件取消掉。
 * attachView用于绑定视图
 * BookView只有两个方法，一个是成功的回调，一个是失败的回调。
 */
public class BookPresenter implements Presenter {
    private DataManager manager;
    //这是rxjava 1的操作。将多个请求统一取消掉。
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private BookView mBookView;
    private Book mBook;
    public BookPresenter (Context mContext){
        this.mContext = mContext;
    }
    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    /**
     * 如果activity销毁了，则取消订阅
     */
    @Override
    public void onStop() {
        if(mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 绑定视图
     * @param view
     */
    @Override
    public void attachView(View view) {
        mBookView = (BookView)view;
    }

    public void getSearchBooks(String name,String tag,int start,int count){
        mCompositeSubscription.add(manager.getSearchBooks(name,tag,start,count).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Book>() {
            @Override
            public void onCompleted() {
                if(mBook !=null){
                    LogUtils.e(mBook);
                    mBookView.success(mBook);
                }
            }

            @Override
            public void onError(Throwable e) {
                mBookView.failed("请求失败！！");
            }

            @Override
            public void onNext(Book book) {
                mBook = book;
            }
        }));
    }
}
