package com.tabeeby.doctor.activities.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.signup.OtpPageActivity;
import com.tabeeby.doctor.activities.upoladpicture.UploadProfilePicture;
import com.tabeeby.doctor.activities.maintabactivity.MainActivity;
import com.tabeeby.doctor.activities.viewpager.ViewPagerActivity;
import com.tabeeby.doctor.utils.Utils;


public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    private Context mContext;
    private ProgressBar pbHeaderProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mContext = this;
        pbHeaderProgress = (ProgressBar) findViewById(R.id.progress_bar);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!Utils.retrieveSharedPreference(mContext, getString(R.string.pref_access_token)).equals("false")) {
                    startActivity(new Intent(mContext, MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(mContext, ViewPagerActivity.class));
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}
