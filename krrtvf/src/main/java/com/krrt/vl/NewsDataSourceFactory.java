package com.krrt.vl;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

public class NewsDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, News>> NewsLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {
        NewsDataSource newsDataSource = new NewsDataSource();
        NewsLiveDataSource.postValue(newsDataSource);
        return newsDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, News>> getNewsLiveDataSource() {
        return NewsLiveDataSource;
    }
}
