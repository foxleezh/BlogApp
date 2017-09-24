package com.foxlee.blog;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService{

    @GET("blog/blogList")
    Observable<NewsModuleInfo> getNews(@Query("t")long t);

}
