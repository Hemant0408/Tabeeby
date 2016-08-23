package com.tabeeby.doctor.activities.upoladpicture;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.maintabactivity.MainActivity;

public class UploadProfilePicture extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile_picture);
        mContext = this;
    }

    public void nextStep(View view) {
        startActivity(new Intent(mContext, MainActivity.class));
        finish();
    }

    public void skip(View view) {
        startActivity(new Intent(mContext, MainActivity.class));
        finish();
    }
}
