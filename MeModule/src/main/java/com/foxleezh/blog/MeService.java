package com.foxleezh.blog;

import com.foxleezh.blog.modules.MeModuleInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MeService {

    @GET("blog/blogList")
    Observable<MeModuleInfo> getNews(@Query("t")long t);

}
