package com.krrt.vl;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

public class NewsViewModel extends ViewModel {

    LiveData<PagedList<News>> newsPagedList;
    LiveData<PageKeyedDataSource<Integer, News>> liveDataSource;

    public NewsViewModel(){
        NewsDataSourceFactory newsDataSourceFactory = new NewsDataSourceFactory();
        liveDataSource = newsDataSourceFactory.getNewsLiveDataSource();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(NewsDataSource.PageSize)
                .build();
        newsPagedList = (new LivePagedListBuilder(newsDataSourceFactory, config)).build();
    }
}
