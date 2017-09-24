package com.foxleezh.blog.viewmodels;

import android.content.Context;
import android.databinding.Bindable;

import com.foxleezh.blog.fragments.HomeFragment;
import com.foxleezh.blog.ivms.IHomeVM;
import com.foxleezh.blog.modules.HomeModuleInfo;
import com.foxleezh.middleware.mvvm.BaseViewModel;

/**
 * Created by zhq on 2016/6/29.
 */
public class HomeViewModel extends BaseViewModel<HomeFragment,HomeModuleInfo> implements IHomeVM<HomeModuleInfo> {


    @Bindable
    public String text="主页";

    public HomeViewModel(Context context) {
        super(context);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void setData(HomeModuleInfo data) {

    }

    @Override
    public void showError(String msg, Object agrs) {

    }
}
