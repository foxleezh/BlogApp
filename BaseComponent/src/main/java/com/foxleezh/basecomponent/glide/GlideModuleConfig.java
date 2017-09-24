package com.foxleezh.basecomponent.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;


/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 Glide配置文件
 */
public class GlideModuleConfig implements GlideModule {

    public static String GLIDE_IMG_DIR_NAME ="/image_cache/";

    //在这里创建设置内容,之前文章所提及的图片质量就可以在这里设置
    //还可以设置缓存池参数什么的
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565)
                .setDiskCache(new ExternalCacheDiskCacheFactory(context, GLIDE_IMG_DIR_NAME, 1024 * 1024 * 1024))
                .setMemoryCache(new LruResourceCache(3 * 1024 * 1024))
                .setBitmapPool(new LruBitmapPool(3 * 1024 * 1024));
    }

    //在这里注册ModelLoaders
    //可以在这里清除缓存什么的
    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.clearDiskCache();
    }
}
