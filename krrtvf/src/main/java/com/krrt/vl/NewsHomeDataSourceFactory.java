package com.krrt.vl;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

public class NewsHomeDataSourceFactory  extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, News>> NewsLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {
        NewsHomeDataSource newsHomeDataSource = new NewsHomeDataSource();
        NewsLiveDataSource.postValue(newsHomeDataSource);
        return newsHomeDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, News>> getNewsLiveDataSource() {
        return NewsLiveDataSource;
    }
}
