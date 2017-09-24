package com.foxlee.blog;

import android.databinding.Bindable;

import com.foxleezh.middleware.mvvm.BaseListener;

/**
 * Created by Administrator on 2017/9/24.
 */

public class MainViewModel extends BaseListener{

    @Bindable
    public boolean tabHomeSelected=true;
    @Bindable
    public boolean tabMeSelected;

}
