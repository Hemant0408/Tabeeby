package com.tabeeby.doctor.activities.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.maintabactivity.MainActivity;
import com.tabeeby.doctor.activities.signup.RegistrationScreen1Activity;
import com.tabeeby.doctor.activities.signup.SelectLanguageActivity;

public class LoginActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
    }

    public void needAnAccount(View view) {
        startActivity(new Intent(mContext, SelectLanguageActivity.class));
        finishAffinity();
    }

    public void nextStep(View view) {
        startActivity(new Intent(mContext, MainActivity.class));
        //finishAffinity();
    }
}
