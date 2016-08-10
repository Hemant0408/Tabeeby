package com.tabeeby.doctor.activities.signup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tabeeby.doctor.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectLanguageActivity extends AppCompatActivity {

    private Context mContext;

    @Bind(R.id.btn_next_step)
    Button btn_next_step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
        mContext = this;

        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_next_step})
    public void onClickNextButton() {
        startActivity(new Intent(mContext, SelectCountryActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void nextStep(View view) {
        startActivity(new Intent(mContext, SelectCountryActivity.class));
    }

    public void SelectArebic(View view) {
        startActivity(new Intent(mContext, SelectCountryActivity.class));
    }
}
