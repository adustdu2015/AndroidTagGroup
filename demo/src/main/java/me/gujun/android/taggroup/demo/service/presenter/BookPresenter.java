package me.gujun.android.taggroup.demo.service.presenter;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;

import me.gujun.android.taggroup.demo.Book;
import me.gujun.android.taggroup.demo.service.manager.DataManager;
import me.gujun.android.taggroup.demo.service.view.BookView;
import me.gujun.android.taggroup.demo.service.view.View;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BookPresenter implements Presenter {
    private DataManager manager;
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

    @Override
    public void onStop() {
        if(mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

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
