package com.foxleezh.middleware.mvvm;

import android.app.Activity;
import android.databinding.BaseObservable;

import java.lang.ref.WeakReference;

/**
 * Created by foxleezh on 2017/9/8.
 * 该类介绍 父类监听器，BaseViewModel和ItemViewModel的父类
 */

public class BaseListener extends BaseObservable{

    protected WeakReference<Activity> weakReference;

    protected void setActivity(Activity activity){
        weakReference=new WeakReference<>(activity);
    }

    protected Activity getActivity(){
        return weakReference!=null?weakReference.get():null;
    }

}
