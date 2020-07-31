package com.krrt.vl;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/news/json/id")
    Call<News> getNewsId(@Query("id") Long id);
    @GET("/news/json/page")
    Call<List<News>> getNewsPage(@Query("page") Integer page, @Query("pagesize") Integer pagesize);
    @GET("/video/json/id")
    Call<Video> getVideoId(@Query("id") Long id);
    @GET("/video/json/page")
    Call<List<Video>> getVideoPage(@Query("page") Integer page, @Query("pagesize") Integer pagesize);
    @GET("/photo/json/id")
    Call<Photo> getPhotoId(@Query("id") Long id);
    @GET("/photo/json/page")
    Call<List<Photo>> getPhotoPage(@Query("page") Integer page, @Query("pagesize") Integer pagesize);
}
