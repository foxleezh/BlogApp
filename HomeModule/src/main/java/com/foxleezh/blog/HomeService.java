package com.foxleezh.blog;

import com.foxleezh.blog.modules.HomeModuleInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface HomeService {

    @GET("blog/blogList")
    Observable<HomeModuleInfo> getNews(@Query("t")long t);

}
