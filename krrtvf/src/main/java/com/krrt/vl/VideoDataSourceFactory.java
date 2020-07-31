package com.krrt.vl;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

public class VideoDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Video>> VideoLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {
        VideoDataSource videoDataSource = new VideoDataSource();
        VideoLiveDataSource.postValue(videoDataSource);
        return videoDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Video>> getVideoLiveDataSource() {
        return VideoLiveDataSource;
    }

}
