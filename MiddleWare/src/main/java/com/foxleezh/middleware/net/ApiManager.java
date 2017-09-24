package com.foxleezh.middleware.net;


import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 Api请求类，封装retrofit
 */
public class ApiManager {
    /**读超时时长，单位：毫秒*/
    private static final int READ_TIME_OUT = 15000;

    /**连接超时时长，单位：毫秒*/
    private static final int CONNECT_TIME_OUT = 15000;

    private Retrofit retrofit;
    private static HashMap<String,ApiManager> urlMaps = new HashMap<>();
    private HashMap<Class<?>,Object> services = new HashMap<>();

    //构造方法私有
    private ApiManager(String baseUrl) {
        //开启Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //增加头部信息
        Interceptor headerInterceptor =new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build();
                return chain.proceed(build);
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    /**
     * 网络请求，通过baseUrl和service获取
     * @param baseUrl
     * @param service
     * @return
     */
    public static <T> T doApi(String baseUrl,final Class<T> service) {
        ApiManager apiManager = urlMaps.get(baseUrl);
        if (apiManager == null) {
            apiManager = new ApiManager(baseUrl);
            urlMaps.put(baseUrl, apiManager);
        }
        Object object= apiManager.services.get(service);
        if(object==null){
            T sev=apiManager.retrofit.create(service);
            apiManager.services.put(service,sev);
            return sev;
        }else {
            return (T) object;
        }
    }

}