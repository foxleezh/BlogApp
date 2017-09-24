package com.foxleezh.basecomponent.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 自动加密的SharePreferences
 */
final class SafeSharedPreferences {

    protected SafeSharedPreferences.Editor editor;
    protected SafeSharedPreferences settings;
    private SharedPreferences psettings;
    private SharedPreferences.Editor peditor;
    private Context context;

    public SafeSharedPreferences(Context context, String name, int mode) {
        psettings = context.getSharedPreferences(name, mode);
        peditor = psettings.edit();
    }

    public class Editor {

        public Editor putString(String key, String value) {
            try {
                key = SafeUtil.shortMD5(key);
                value = SafeUtil.Enctry(value);
                peditor.putString(key, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return editor;
        }


        public Editor putInt(String key, int i) {
            String value = Integer.toString(i);
            key = SafeUtil.shortMD5(key);
            value = SafeUtil.Enctry(value);
            peditor.putString(key, value);
            return editor;
        }

        public Editor putLong(String key, long l) {
            String value = Long.toString(l);
            key = SafeUtil.shortMD5(key);
            value = SafeUtil.Enctry(value);
            peditor.putString(key, value);
            return editor;
        }

        public Editor putFloat(String key, float f) {
            String value = Float.toString(f);
            key = SafeUtil.shortMD5(key);
            value = SafeUtil.Enctry(value);
            peditor.putString(key, value);
            return editor;
        }

        public Editor putBoolean(String key, boolean b) {
            String value = Boolean.toString(b);
            key = SafeUtil.shortMD5(key);
            value = SafeUtil.Enctry(value);
            peditor.putString(key, value);
            return editor;
        }

        public Editor remove(String key) {
            key = SafeUtil.shortMD5(key);
            peditor.remove(key);
            return editor;
        }

        public Editor clear() {
            peditor.clear();
            return editor;
        }

        public boolean commit() {
            return peditor.commit();
        }

        public void apply() {
            peditor.apply();
        }
    }

    public String getString(String key, String defValue) {
        key = SafeUtil.shortMD5(key);
        String value = psettings.getString(key, "");
        if ("".equals(value)) {
            return defValue;
        } else {
            try {
                value = SafeUtil.Dectry(value);
            } catch (Exception e) {
                return defValue;
            }
        }
        return value;
    }


    public int getInt(String key, int defValue) {
        key = SafeUtil.shortMD5(key);
        String value = psettings.getString(key, "");
        if ("".equals(value)) {
            return defValue;
        } else {
            try {
                value = SafeUtil.Dectry(value);
                defValue = Integer.parseInt(value);
            } catch (Exception e) {
                return defValue;
            }
        }
        return defValue;
    }


    public long getLong(String key, long defValue) {
        key = SafeUtil.shortMD5(key);
        String value = psettings.getString(key, "");
        if ("".equals(value)) {
            return defValue;
        } else {
            try {
                value = SafeUtil.Dectry(value);
                defValue = Long.parseLong(value);
            } catch (Exception e) {
                return defValue;
            }
        }
        return defValue;
    }


    public float getFloat(String key, float defValue) {
        key = SafeUtil.shortMD5(key);
        String value = psettings.getString(key, "");
        if ("".equals(value)) {
            return defValue;
        } else {
            try {
                value = SafeUtil.Dectry(value);
                defValue = Float.parseFloat(value);
            } catch (Exception e) {
                return defValue;
            }
        }
        return defValue;
    }


    public boolean getBoolean(String key, boolean defValue) {
        key = SafeUtil.shortMD5(key);
        String value = psettings.getString(key, "");
        if ("".equals(value)) {
            return defValue;
        } else {
            try {
                value = SafeUtil.Dectry(value);
                defValue = Boolean.parseBoolean(value);
            } catch (Exception e) {
                return defValue;
            }
        }
        return defValue;
    }

    public boolean contains(String key) {
        key = SafeUtil.shortMD5(key);
        return psettings.contains(key);
    }


    public Editor edit() {
        if (this.editor == null) {
            this.editor = new SafeSharedPreferences.Editor();
        }
        return editor;
    }
}
