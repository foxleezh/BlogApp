/*
 * Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.foxleezh.middleware.mvvm;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

/**
 * 基类ViewModel，作为MVVM中的VM的父类
 * @param <V>
 * @param <P>
 */
public class BaseViewModel<V extends Fragment, P extends BaseModel> extends BaseListener{
    protected Context mContext;
    private WeakReference<V> viewRef;
    private P data;

    /**
     * must be Application context
     */
    public BaseViewModel(Context context) {
        if (context instanceof Application) {
            this.mContext = context;
        } else {
            throw new IllegalArgumentException("context must be ApplicationContext");
        }
    }

    public void attachView(V view) {
        viewRef = new WeakReference<V>(view);
        setActivity(view.getActivity());
    }

    /**
     * Get the attached view. You should always call {@link #isViewAttached()} to check if the view
     * is
     * attached to avoid NullPointerExceptions.
     *
     * @return <code>null</code>, if view is not attached, otherwise the concrete view instance
     */
    @Nullable
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    public P getModelData() {
        return data;
    }

    public void setModelData(P data) {
        this.data=data;
    }


    /**
     * Checks if a view is attached to this presenter. You should always call this method before
     * calling {@link #getView()} to get the view instance.
     */
    protected boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
            weakReference.clear();
            weakReference = null;
        }
    }

    public void finish(){
        Activity activity=getActivity();
        if(activity!=null){
            activity.finish();
        }
    }

}
