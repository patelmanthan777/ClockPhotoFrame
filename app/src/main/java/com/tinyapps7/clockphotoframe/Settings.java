package com.tinyapps7.clockphotoframe;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Blurpixel on 4/20/2017.
 */

public class Settings
{
    private static SharedPreferences mSharedPref;
    public static final String Clock = "clock";
    public static final String Seconds = "second";
    public static final String Minutes = "minutes";
    public  static  final String Hours="hours";

   public Settings()
    {

    }

    public static void init(Context context)
    {
        if(mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static String Clock_read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void Clock_write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static String Secnd_read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void Secnd_write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public static String Minute_read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void Minute_write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value).commit();
    }
}
