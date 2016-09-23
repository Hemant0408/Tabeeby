package com.tabeeby.doctor.application;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.tabeeby.doctor.BuildConfig;
import com.tabeeby.doctor.httpclient.API;
import com.tabeeby.doctor.httpclient.ServiceGenerator;
import com.tabeeby.doctor.utils.LocalSharedManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * Created by Lenovo R61 on 8/6/2016.
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;
    private API api;

    public static void updateLanguage(Context ctx, String lang) {

        Configuration cfg = new Configuration();
        LocalSharedManager manager = new LocalSharedManager(ctx);
        String language = manager.GetValueFromSharedPrefs("force_locale");

        if (TextUtils.isEmpty(language) && lang == null) {
            cfg.locale = Locale.getDefault();
            String tmp_locale = "";
            tmp_locale = Locale.getDefault().toString().substring(0, 2);
            manager.SaveValueToSharedPrefs("force_locale", tmp_locale);

        } else if (lang != null) {
            cfg.locale = new Locale(lang);
            manager.SaveValueToSharedPrefs("force_locale", lang);

        } else if (!TextUtils.isEmpty(language)) {
            cfg.locale = new Locale(language);
        }
        ctx.getResources().updateConfiguration(cfg, null);
    }

    @Override
    public void onCreate() {
        updateLanguage(this, null);
        super.onCreate();
        mInstance = this;
        printHashKey();
    }

    public void printHashKey() {
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.tabeeby.doctor",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public API getHttpService() {
        if (api == null) {
            try {
                boolean isDebug = BuildConfig.DEBUG;
                api = ServiceGenerator.createService(API.class, isDebug, this);
            } catch (Exception e) {

            }
        }
        return api;
    }

}
