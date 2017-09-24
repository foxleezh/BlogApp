package com.foxleezh.middleware.mvvm;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 基类Model，作为MVVM中M的父类
 */
public class BaseModel implements Parcelable {


    protected BaseModel(Parcel in) {
    }

    protected BaseModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }


}
