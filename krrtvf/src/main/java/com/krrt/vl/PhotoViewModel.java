package com.krrt.vl;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

public class PhotoViewModel extends ViewModel {

    LiveData<PagedList<Photo>> photoPagedList;
    LiveData<PageKeyedDataSource<Integer, Photo>> liveDataSource;

    public PhotoViewModel(){
        PhotoDataSourceFactory photoDataSourceFactory = new PhotoDataSourceFactory();
        liveDataSource = photoDataSourceFactory.getPhotoLiveDataSource();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PhotoDataSource.PageSize)
                .build();
        photoPagedList = (new LivePagedListBuilder(photoDataSourceFactory, config)).build();
    }

}
