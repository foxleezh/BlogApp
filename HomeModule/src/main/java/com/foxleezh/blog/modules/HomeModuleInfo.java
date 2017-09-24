package com.foxleezh.blog.modules;

import com.foxleezh.middleware.mvvm.BaseRespModel;
import com.foxleezh.middleware.mvvm.BaseViewModel;
import com.foxleezh.middleware.mvvm.INetVM;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/16.
 */

public class HomeModuleInfo<T extends BaseViewModel & INetVM> extends BaseRespModel<HomeModuleInfo,T> {
    ArrayList<HomeModule> data;
}
