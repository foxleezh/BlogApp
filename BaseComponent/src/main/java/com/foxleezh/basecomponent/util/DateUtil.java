package com.foxleezh.basecomponent.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 日期转换相关工具类
 */
public class DateUtil {


    /**
     * 获取时间间隔的中文表示
     */
    public static String getTimeDisplay(long getTime) {

        if(getTime==0){
            return "";
        }

        final long currTime = System.currentTimeMillis();
        final Date formatSysDate = new Date(currTime);

        // 判断当前总天数
        final int sysMonth = formatSysDate.getMonth() + 1;
        final int sysYear = formatSysDate.getYear();

        // 计算服务器返回时间与当前时间差值
        final long seconds = (currTime - getTime) / 1000;
        final long minute = seconds / 60;
        final long hours = minute / 60;
        final long day = hours / 24;
        final long month = day / calculationDaysOfMonth(sysYear, sysMonth);
        final long year = month / 12;

        if (year > 0) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm", Locale.getDefault());
            return simpleDateFormat.format(new Date(getTime));
        } else if (month > 0 || day > 30) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "MM-dd HH:mm", Locale.getDefault());
            return simpleDateFormat.format(new Date(getTime));
        } else if (day > 0) {
            return day + "天前";
        } else if (hours > 0) {
            return hours + "小时前";
        } else if (minute > 0) {
            return minute + "分钟前";
        } else {
            return "刚刚"; //都换成分钟前
        }
    }

    /**
     * 计算一个月有多少天
     *
     */
    private static int calculationDaysOfMonth(int year, int month) {
        int day = 0;
        switch (month) {
            // 31天
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            // 30天
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;
            // 计算2月天数
            case 2:
                day = year % 100 == 0 ? year % 400 == 0 ? 29 : 28
                        : year % 4 == 0 ? 29 : 28;
                break;
        }

        return day;
    }


    /**
     * 将时间戳转为字符串
     *
     * @param time  时间戳
     * @param style 时间格式 (类似于"yyyy-MM-dd HH:mm")
     */
    public static String getStrTime(long time, String style) {

        if(time==0){
            return "--";
        }
        String re_StrTime;

        SimpleDateFormat sdf = new SimpleDateFormat(style, Locale.getDefault());

        re_StrTime = sdf.format(new Date(time));

        return re_StrTime;

    }


    /**
     * 将字符串转为时间戳
     *
     * @param style   字符串时间的时间格式(类似于"yyyy-MM-dd HH:mm:ss")
     */
    public static long strTimeToTimestamp(String style, String strTime) {
        long re_time = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(style, Locale.getDefault());
        Date d;
        try {
            d = sdf.parse(strTime);
            re_time = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }


}
