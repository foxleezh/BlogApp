package com.foxleezh.basecomponent.util;

import android.content.Context;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍  自动加密的SharePreferences的父类，只要继承该类，所有SharePreferences操作自动加密
 */
public class SafeAbstractPreference
{
	private Context context;
	protected SafeSharedPreferences settings;
	protected SafeSharedPreferences.Editor editor;

	public SafeAbstractPreference(Context context)
	{
		this.context = context;
		settings = new SafeSharedPreferences(context,SafeUtil.shortMD5(getClass().getSimpleName()),0);
		editor = settings.edit();
	}

	protected void save(){
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
			editor.apply();
		} else {
			editor.commit();
		}
	}
}
