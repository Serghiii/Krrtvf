package com.krrt.vl;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

public class NewsHomeViewModel extends ViewModel {

    LiveData<PagedList<News>> newsPagedList;
    LiveData<PageKeyedDataSource<Integer, News>> liveDataSource;

    public NewsHomeViewModel(){
        NewsHomeDataSourceFactory newsHomeDataSourceFactory = new NewsHomeDataSourceFactory();
        liveDataSource = newsHomeDataSourceFactory.getNewsLiveDataSource();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(NewsHomeDataSource.PageSize)
                .build();
        newsPagedList = (new LivePagedListBuilder(newsHomeDataSourceFactory, config)).build();
    }}
