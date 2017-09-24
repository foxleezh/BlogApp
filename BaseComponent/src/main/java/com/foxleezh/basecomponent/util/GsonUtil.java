package com.foxleezh.basecomponent.util;

import com.google.gson.Gson;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 全局通用的gson实例
 */

public class GsonUtil {
    private static Gson gson = new Gson();

    public static Gson getGsonIns() {
        return gson;
    }
}
