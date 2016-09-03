package com.tabeeby.doctor.activities.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.login.LoginActivity;
import com.tabeeby.doctor.activities.maintabactivity.MainActivity;

public class SignUpActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mContext = this;
    }

    public void nextStep(View view) {
        startActivity(new Intent(mContext, OtpPageActivity.class));
    }

    public void alreadyMember(View view) {
        startActivity(new Intent(mContext, LoginActivity.class));
    }

    public void FaceBookLogin(View view) {
        startActivity(new Intent(mContext, MainActivity.class));
        finishAffinity();
    }
}
