package me.gujun.android.taggroup.demo.service.view;

import me.gujun.android.taggroup.demo.model.Book;

public interface BookView extends View{
    void success(Book book);
    void failed(String result);
}
