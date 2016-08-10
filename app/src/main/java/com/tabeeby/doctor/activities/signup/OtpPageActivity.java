package com.tabeeby.doctor.activities.signup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.maintabactivity.MainActivity;
import com.tabeeby.doctor.activities.upoladpicture.UploadProfilePicture;

public class OtpPageActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);
        mContext = this;
    }

    public void nextStep(View view) {
        startActivity(new Intent(mContext, UploadProfilePicture.class));
        finishAffinity();
    }
}
