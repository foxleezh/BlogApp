package com.foxlee.blog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(
                message -> Log.d("zcb","OkHttp====Message:"+message));
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://owegaqfyy.bkt.clouddn.com/")
                        //增加返回值为Gson的支持(以实体类返回)
                        .addConverterFactory(GsonConverterFactory.create())
                        //增加返回值为Oservable<T>的支持
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(getOkHttpClient())
                        .build();
                Observable<Notification<NewsModuleInfo>> combineRequestOb = retrofit.create(ApiService.class).getNews()
                        .map(newsModuleInfo -> {
                            Log.d("foxlee++++++++",Thread.currentThread().getName());
                            return newsModuleInfo;
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .materialize().share();

                combineRequestOb.filter(Notification::isOnNext)
                        .subscribe(m ->Log.d("foxlee++++++++",Thread.currentThread().getName()));
                combineRequestOb.filter(Notification::isOnError)
                        .subscribe(m ->Log.d("foxlee++++++++",Thread.currentThread().getName()));
            }
        });
    }


}
