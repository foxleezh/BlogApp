package com.foxleezh.basecomponent.util;

import android.text.TextUtils;

import com.A;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 加密保护相关工具类
 */
public class SafeUtil {

    /**
     * 获取MD5值
     * @param s 需要转换的String
     * @return
     */
    public static String MD5(String s) {
        if(TextUtils.isEmpty(s)){
            return "";
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 缩小的16位md5值
     * @param s
     * @return
     */
    public static String shortMD5(String s) {
        String md5=MD5(s);
        if(!TextUtils.isEmpty(md5)&&md5.length()>23) {
            return md5.substring(8, 24);
        }else {
            return s;
        }
    }

    /**
     * 将byte数组转换为16进制字符串
     * @param data 需要转换的数组
     * @return
     */
    public static String toHexString(byte[] data){
        StringBuilder sb = new StringBuilder(data.length * (2));
        int pb = 0;
        while(pb < data.length){
            byte b = data[pb++];
            int h = ((0xF0 & b) >> 4);
            int l = (0x0F & b);
            if(h < 10){
                sb.append((char)('0' + h));
            }else{
                sb.append((char)('a' + h - 10));
            }
            if(l < 10){
                sb.append((char)('0' + l));
            }else{
                sb.append((char)('a' + l - 10));
            }
        }

        return sb.toString();
    }

    /**
     * 将十六进制字符串表示的字节数据还原成字节数组
     * @param text 被还原的字符串
     * @return 还原之后的字节数组
     */
    public static byte[] fromHexString(String text){
        if(text == null)
            return new byte[0];

        byte[] result = new byte[text.length() / 2];

        text = text.toLowerCase();
        int pb = 0;
        int ps = 0;
        while(pb < result.length && ps < text.length()){
            char ch = text.charAt(ps++);
            char cl = text.charAt(ps++);

            int a = 0;
            int b = 0;
            if('0' <= ch && ch <= '9'){
                a = ch - '0';
            }else{
                a = ch - 'a' + 10;
            }
            if('0' <= cl && cl <= '9'){
                b = cl - '0';
            }else{
                b = cl - 'a' + 10;
            }

            result[pb++] = (byte)((a << 4) + b);
        }

        return result;
    }


    /**
     * 将字符串本地加密
     * @param s 需要加密的字符串
     * @return
     */
    public static String Enctry(String s) {
        try {
            byte[] b= A.a(s.getBytes("utf-8"));
            s= Base64Encoder.encode(b);
            return s;
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 将字符串本地解密
     * @param s 需要解密的字符串
     * @return
     */
    public static String Dectry(String s) {
        try {
            byte[] b= Base64Decoder.decodeToBytes(s);
            s=new String(A.b(b),"utf-8");
            return s;
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

}
