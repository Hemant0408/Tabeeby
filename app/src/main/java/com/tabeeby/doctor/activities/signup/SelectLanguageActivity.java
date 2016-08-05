package com.tabeeby.doctor.activities.signup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tabeeby.doctor.R;

public class SelectLanguageActivity extends AppCompatActivity {
private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
        mContext=this;
    }

    public void nextStep(View view) {startActivity(new Intent(mContext, SelectCountryActivity.class));}

    public void SelectArebic(View view) {startActivity(new Intent(mContext, SelectCountryActivity.class));}
}
