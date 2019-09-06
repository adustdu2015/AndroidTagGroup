package me.gujun.android.taggroup.demo.service.manager;

import android.content.Context;

import me.gujun.android.taggroup.demo.model.Book;
import me.gujun.android.taggroup.demo.network.RetrofitHelper;
import me.gujun.android.taggroup.demo.network.RetrofitService;
import rx.Observable;

public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }
    public Observable<Book> getSearchBooks(String name, String tag, int start, int count){
        return mRetrofitService.getSearchBooks(name,tag,start,count);
    }

}
