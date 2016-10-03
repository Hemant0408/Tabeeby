package com.tabeeby.doctor.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tabeeby.doctor.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.support.v7.widget.Toolbar;

import okhttp3.ResponseBody;

/**
 * Created by Lenovo R61 on 8/2/2016.
 */
public class Utils {

    static ProgressDialog progressDialog;

    public static void storeSharedPreference(Context ctx, String key, String value) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(ctx.getString(R.string.shared_preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String retrieveSharedPreference(Context ctx, String key) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(ctx.getString(R.string.shared_preference_file_key), Context.MODE_PRIVATE);
        String value = sharedPref.getString(key, "false");
        return value;
    }

    public static void createToastShort(String message, Context ctx) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }

    public static void createToastLong(String message, Context ctx) {
        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
    }

    public static void removeSharedPreference(Context ctx, String key) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(ctx.getString(R.string.shared_preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.commit();
    }

    public static boolean isRTL(Context ctx) {
        Configuration config = ctx.getResources().getConfiguration();
        return config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
    }

    public static String convertTypedBodyToString(ResponseBody responseBody) {
        InputStream is;
        is = responseBody.byteStream();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder out = new StringBuilder();
            String newLine = System.getProperty("line.separator");
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void removePreference(Context mContext) {
        Utils.removeSharedPreference(mContext, mContext.getString(R.string.pref_access_token));
        Utils.removeSharedPreference(mContext, mContext.getString(R.string.pref_email));
        Utils.removeSharedPreference(mContext, mContext.getString(R.string.pref_picture));
        Utils.removeSharedPreference(mContext, mContext.getString(R.string.pref_user_type));
        Utils.removeSharedPreference(mContext, mContext.getString(R.string.pref_user_id));
    }

    public static void ErrorMessage(final Activity mContext, final Bundle bundle, String Msg) {
        mContext.setContentView(R.layout.error_page);
        Toolbar toolbar = (Toolbar) mContext.findViewById(R.id.toolbar);
        toolbar.setTitle(mContext.getResources().getString(R.string.app_name));
        TextView textView = (TextView) mContext.findViewById(R.id.mTextError);
        Button ButtonTryAgain = (Button) mContext.findViewById(R.id.btn_try_again);
        textView.setText(Msg);

        ButtonTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.finish();
                mContext.startActivity(new Intent(mContext, mContext.getClass()));
            }
        });
    }

    public static void ShowProgressDialog(Context mContext) {
        progressDialog = new ProgressDialog(mContext, R.style.MyTheme);
        progressDialog.setIndeterminateDrawable(mContext.getResources().getDrawable(R.drawable.rotate));
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progressDialog.show();
    }

    public static void DismissProgressDialog() {
        progressDialog.dismiss();
    }

    public static String ConvertDateUnixtoNormal(String unixdatetime, String format) {
        Date date = new Date(Long.parseLong(unixdatetime) * 1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat(format); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
}
