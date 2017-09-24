package com.foxleezh.blog.viewmodels;

import android.content.Context;
import android.databinding.Bindable;

import com.foxleezh.blog.fragments.MeFragment;
import com.foxleezh.blog.ivms.IMeVM;
import com.foxleezh.blog.modules.MeModuleInfo;
import com.foxleezh.middleware.mvvm.BaseViewModel;

/**
 * Created by zhq on 2016/6/29.
 */
public class MeViewModel extends BaseViewModel<MeFragment,MeModuleInfo> implements IMeVM<MeModuleInfo> {


    @Bindable
    public String text="我的";

    public MeViewModel(Context context) {
        super(context);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void setData(MeModuleInfo data) {

    }

    @Override
    public void showError(String msg, Object agrs) {

    }
}
