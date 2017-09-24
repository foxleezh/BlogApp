package com.foxleezh.basecomponent.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 与Context相关的工具类
 */
public class ContextUtil {
    /**
     * Toast提示
     *
     * @param c   Context
     * @param msg String
     */
    public static void toastTips(Context c, String msg) {
        if (c == null || TextUtils.isEmpty(msg))
            return;
        Toast.makeText(c.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 传入dp值，转换为px值
     *
     * @param dp dp值
     * @return px
     */
    public static float dp2px(Context context, float dp) {
        DisplayMetrics metrics =context.getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    /**
     * 传入px值，转换成dp值
     *
     * @param px px值
     * @return dp值
     */
    public static float px2dp(Context context, float px) {
        DisplayMetrics metrics =context.getResources().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }


    /**
     *  获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context){
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = (int)dp2px(context,25);
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    /**
     * 获取底部导航栏高度
     * @param activity
     * @return
     */
    public static int getNavigationBarHeight(Activity activity) {
//        boolean hasMenuKey;
//        if(Build.VERSION.SDK_INT>=14) {
//            hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
//        }else {
//            return 0;
//        }
//        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
//        GLog.d("foxlee++++", hasMenuKey+" <-- hasMenuKey");
//        GLog.d("foxlee++++", hasBackKey+" <-- hasBackKey");
//
//        GLog.d("foxlee++++", height+" <-- resources.getDimensionPixelSize(resourceId)");
        if (navigationBarExist(activity)) {
            //获取NavigationBar的高度
            Resources resources = activity.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            return resources.getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

    /**
     * 此方法在模拟器还是在真机都是完全正确
     *
     * @param activity
     * @return
     */
    public static boolean navigationBarExist(Activity activity) {
        WindowManager windowManager = activity.getWindowManager();
        Display d = windowManager.getDefaultDisplay();

        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            d.getRealMetrics(realDisplayMetrics);
        }

        int realHeight = realDisplayMetrics.heightPixels;
        int realWidth = realDisplayMetrics.widthPixels;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        d.getMetrics(displayMetrics);

        int displayHeight = displayMetrics.heightPixels;
        int displayWidth = displayMetrics.widthPixels;

        return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
    }

    /**
     * 隐藏键盘
     * @param activity
     */
    public static void hideSoftKeyboard(Activity activity){
        try {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static String getPackageVersion(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi;
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "UNKNOW";
        }
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static int getPackageCode(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi;
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }

    /**
     * 获取手机的Device(注册时使用)
     *
     * @return
     */
    public static String getDevice() {
        String device = Build.MODEL + "_ANDROID_" + Build.VERSION.RELEASE;
        device = device.replace(" ", "_").replace(".", "_");
        return device;
    }

    /**
     * 获取手机的imei，该方法在com.A中有相同方法e，jni底层会调用，请一并修改
     *
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        String imei = UtilPreferences.getInstance(context).getIMEI();
        if (TextUtils.isEmpty(imei)) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            try {
                imei=getImei(telephonyManager.getDeviceId().substring(2,15));
                UtilPreferences.getInstance(context).setIMEI(imei);
            } catch (Exception e) {
                imei=getImei("");
                UtilPreferences.getInstance(context).setIMEI(imei);
            }
        }
        return imei;
    }


    /**
     * 随机生成的imei
     *
     * @param
     * @return
     */
    private static String getImei(String imei) {
        Random random = new Random();
        StringBuilder sb_imei = new StringBuilder();
        if(TextUtils.isEmpty(imei)) {
            for (int i = 0; i < 13; i++) {
                sb_imei.append(random.nextInt(10));
            }
        }else {
            sb_imei.append(imei);
        }
        sb_imei.append("1");
        String time = (System.currentTimeMillis() + "").substring(0, 10);
        sb_imei.append(time);
        sb_imei.append(random.nextInt(10));
        sb_imei.append(random.nextInt(10));
        return sb_imei.toString();
    }

    /**
     * 获取Rom信息
     *
     * @return
     */
    public static String getRom() {
        String rom = "";
        try {
            //DISPLAY Rom的名字 例如 Flyme 1.1.2（魅族rom）  JWR66V（Android nexus系列原生4.3rom）
            rom = android.os.Build.DISPLAY;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return rom == null ? "" : rom;
    }

    /**
     * 判断服务是否后台运行
     *
     * @param mContext  Context
     * @param className 判断的服务名字
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceRun(Context mContext, String className) {
        boolean isRun = false;
        try {
            ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(200);
            if (serviceList == null) {
                return false;
            }
            int size = serviceList.size();
            for (int i = 0; i < size; i++) {
                if (serviceList.get(i).service.getClassName().equals(className)) {
                    isRun = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isRun;
    }


}
