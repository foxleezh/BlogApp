package com.foxleezh.middleware.mvvm;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 基类Fragment，作为MVVM中V的父类
 */
public abstract class BaseFragment<T extends BaseViewModel,B extends ViewDataBinding> extends RxFragment {
    protected T viewModel;
    protected B binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
            viewModel =bindViewModel(binding);
            init();
        } else {
            if (binding.getRoot().getParent() != null) {
                ((ViewGroup) binding.getRoot().getParent()).removeView(binding.getRoot());
            }
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.attachView(this);
    }

    public boolean isFirstLoad=true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser&&isFirstLoad) {
            isFirstLoad = false;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getData();
                }
            },50);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Return the layout resource like R.layout.my_layout
     *
     * @return the layout resource or zero ("0"), if you don't want to have an UI
     */
    protected abstract int getLayoutRes();

    protected abstract T bindViewModel(B binding);

    public void onClick(View view){

    }

    protected void init(){

    }
    protected void getData(){

    }

    public void finish(){

    }

    public void onBackPressed() {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void onRelease(){

    }

}
