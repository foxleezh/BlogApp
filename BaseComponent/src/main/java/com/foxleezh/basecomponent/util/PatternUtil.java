package com.foxleezh.basecomponent.util;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 与正则表达式相关的工具类，提供常用的正则判断
 */

public class PatternUtil {

    /**
     * 密码是否为6-18位的非空字符
     * @param input
     * @return
     */
    public static boolean isPassword(String input){
        String pattern = "^\\S{6,18}$";
        return Pattern.matches(pattern, input);
    }

    /**
     * 是否为11位以1开关的手机号
     * @param input
     * @return
     */
    public static boolean isPhone(String input){
        String pattern = "^1\\d{10}$";
        return Pattern.matches(pattern, input);
    }

    /**
     * 是否为正常的昵称，不包含转义字符，1~12位
     * @param input
     * @return
     */
    public static String isNickname(String input){
        if(input.contains("<")||input.contains(">")||input.contains("\\")){
            return "不能包含<>\\字符";
        }
        if(input.length()<1||input.length()>12){
            return "请输入1~12个字符";
        }
        if(TextUtils.isEmpty(input.trim())){
            return "不能只包含空字符";
        }
        return "";
    }

}
