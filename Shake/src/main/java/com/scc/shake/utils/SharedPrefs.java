package com.scc.shake.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.scc.shake.BuildConfig;

import java.util.Set;

/**
 * Utility class for SharedPreferences
 */

public class SharedPrefs {

    public static final String LOCAL_DATA = "local_data";


    private static SharedPreferences mPreferences;

    private static SharedPreferences getInstance(Context context) {
        if (mPreferences == null) {
            mPreferences = context.getApplicationContext()
                    .getSharedPreferences("_data", Context.MODE_PRIVATE);
        }
        return mPreferences;
    }

    public static boolean contain(Context context, String key) {
        return getInstance(context).contains(key);
    }

    public static void clearPrefs(Context context) {
        getInstance(context).edit().clear().apply();
    }


    //region Booleans
    public static void setBoolean(Context context, String key, boolean value) {
        getInstance(context).edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key) {
        return getInstance(context).getBoolean(key, false);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getInstance(context).getBoolean(key, defaultValue);
    }
    //endregion

    //region Strings
    public static void setString(Context context, String key, String value) {
        getInstance(context).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key) {
        return getInstance(context).getString(key, "");
    }

    public static String getString(Context context, String key, String defaultValue) {
        return getInstance(context).getString(key, defaultValue);
    }
    //endregion

    //region Integers
    public static void setInt(Context context, String key, int value) {
        getInstance(context).edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key) {
        return getInstance(context).getInt(key, 0);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return getInstance(context).getInt(key, defaultValue);
    }
    //endregion

    //region Floats
    public static void setFloat(Context context, String key, float value) {
        getInstance(context).edit().putFloat(key, value).apply();
    }

    public static float getFloat(Context context, String key) {
        return getInstance(context).getFloat(key, 0);
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        return getInstance(context).getFloat(key, defaultValue);
    }
    //endregion

    //region Longs
    public static void setLong(Context context, String key, long value) {
        getInstance(context).edit().putLong(key, value).apply();
    }

    public static long getLong(Context context, String key) {
        return getInstance(context).getLong(key, 0);
    }

    public static long getLong(Context context, String key, long defaultValue) {
        return getInstance(context).getLong(key, defaultValue);
    }
    //endregion

    //region Double
    public static void setDouble(Context context, String key, double value) {
        getInstance(context).edit().putLong(key, Double.doubleToRawLongBits(value)).apply();
    }

    public static Double getDouble(Context context, String key) {
        return Double.longBitsToDouble(getInstance(context).getLong(key, Double.doubleToRawLongBits(0)));
    }

    public static Double getDouble(Context context, String key, Double defValue) {
        return Double.longBitsToDouble(getInstance(context).getLong(key, Double.doubleToRawLongBits(defValue)));
    }
    //endregion

    public static void remove(Context context, String key) {
        getInstance(context).edit().remove(key).apply();
    }

    public static void setStringSet(Context context, String key, Set<String> mSet) {
        getInstance(context).edit().putStringSet(key, mSet).apply();
    }

    public static Set<String> getStringSet(Context context, String key) {
        return getInstance(context).getStringSet(key, null);
    }

}
