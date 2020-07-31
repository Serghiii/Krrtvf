package com.krrt.vl;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

public class VideoViewModel extends ViewModel {

    LiveData<PagedList<Video>> videoPagedList;
    LiveData<PageKeyedDataSource<Integer, Video>> liveDataSource;

    public VideoViewModel(){
        VideoDataSourceFactory videoDataSourceFactory = new VideoDataSourceFactory();
        liveDataSource = videoDataSourceFactory.getVideoLiveDataSource();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(VideoDataSource.PageSize)
                .build();
        videoPagedList = (new LivePagedListBuilder(videoDataSourceFactory, config)).build();
    }
}
