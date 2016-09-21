package com.tabeeby.doctor.activities.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.upoladpicture.UploadProfilePicture;
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

        // pbHeaderProgress.getIndeterminateDrawable().setColorFilter(mContext.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Utils.storeSharedPreference(mContext, "Type", "D");
                startActivity(new Intent(mContext, ViewPagerActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
