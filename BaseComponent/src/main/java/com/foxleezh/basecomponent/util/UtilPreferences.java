package com.foxleezh.basecomponent.util;

import android.content.Context;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 工具类需要储存的SharePreference
 */
public class UtilPreferences extends SafeAbstractPreference {

	private static UtilPreferences instance;
    private Context mcontext;

	public static UtilPreferences getInstance(Context context) {
		if (instance == null) {
			instance = new UtilPreferences(context);
		}
		return instance;
	}

	private UtilPreferences(Context context) {
		super(context);
        mcontext = context;
	}

	/**
	 * 设置Imei
	 * @param imei
	 */
	public void setIMEI(String imei) {
		editor.putString("imei_info", imei);
		save();
	}

	/**
	 * 获取Imei
	 * @return
	 */
	public String getIMEI() {
		return settings.getString("imei_info", "");
	}


	protected void save(){
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
			editor.apply();
		} else {
			editor.commit();
		}
	}
}
