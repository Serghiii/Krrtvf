package com.krrt.vl;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoDataSource extends PageKeyedDataSource<Integer, Video> {

    public static final int FirstPage = 1;
    public static final int PageSize = 5;

    public VideoDataSource() {
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Video> callback) {
        NetworkService.getInstance()
                .getJSONApi()
                .getVideoPage(FirstPage, PageSize)
                .enqueue(new Callback<List<Video>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Video>> call, @NonNull Response<List<Video>> response) {
                        if (!response.body().isEmpty()) {
                            callback.onResult(response.body(), null, FirstPage + 1);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Video>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Video> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Video> callback) {
        NetworkService.getInstance()
                .getJSONApi()
                .getVideoPage(params.key, PageSize)
                .enqueue(new Callback<List<Video>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Video>> call, @NonNull Response<List<Video>> response) {
                        if (!response.body().isEmpty()) {
                            callback.onResult(response.body(),  params.key + 1);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Video>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
