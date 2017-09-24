package com.foxlee.blog;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ApiService{

    @GET("blog/blogList")
    Observable<NewsModuleInfo> getNews();

}
