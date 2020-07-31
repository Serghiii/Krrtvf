package com.krrt.vl;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsHomeDataSource extends PageKeyedDataSource<Integer, News> {

    public static final int FirstPage = 1;
    public static final int PageSize = 3;

    public NewsHomeDataSource() {}

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, News> callback) {
        NetworkService.getInstance()
                .getJSONApi()
                .getNewsPage(FirstPage, PageSize)
                .enqueue(new Callback<List<News>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                        if (!response.body().isEmpty()) {
                            callback.onResult(response.body(), null, FirstPage+1);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, News> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, News> callback) {

    }
}
