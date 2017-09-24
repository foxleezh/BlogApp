package com.foxleezh.basecomponent.glide;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 Glide加载回调
 */
public abstract class TagSimpleTaget<Z,M> extends SimpleTarget<Z>{

    public M target;

    public TagSimpleTaget(M target) {
        super();
        this.target=target;
    }

    public TagSimpleTaget(M target,int width,int height) {
        super(width,height);
        this.target=target;
    }

    @Override
    public void onResourceReady(Z resource, GlideAnimation<? super Z> glideAnimation) {
        onResourceReady(target,resource,glideAnimation);
    }

    public abstract void onResourceReady(M target,Z resource, GlideAnimation<? super Z> glideAnimation);

}
