package com;


/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍  本地调用接口
 */
public class A {

    /**
     * 数据加密
     */
    public static native byte[] a(byte[] b);

    /**
     * 数据解密
     */
    public static native byte[] b(byte[] b);

    /**获取随机24位密钥*/
    public static native String c();

}
