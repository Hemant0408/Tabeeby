package com.tabeeby.doctor.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lenovo R61 on 8/6/2016.
 */
public class LocalSharedManager {

    Context context;
    SharedPreferences sharedPreferences;

    public LocalSharedManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("pref",
                Context.MODE_PRIVATE);
    }

    public SharedPreferences getGcmPreferences() {

        return sharedPreferences;
    }

    public String GetValueFromSharedPrefs(String KeyName) {
        return sharedPreferences.getString(KeyName, "");
    }

    public String SaveValueToSharedPrefs(String KeyName, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KeyName, value);
        editor.commit();
        return value;
    }
}