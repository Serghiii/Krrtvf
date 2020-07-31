package com.krrt.vl;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

public class PhotoDataSourceFactory  extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Photo>> PhotoLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {
        PhotoDataSource photoDataSource = new PhotoDataSource();
        PhotoLiveDataSource.postValue(photoDataSource);
        return photoDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Photo>> getPhotoLiveDataSource() {
        return PhotoLiveDataSource;
    }

}
