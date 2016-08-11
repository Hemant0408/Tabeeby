package com.tabeeby.doctor.activities.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.tabeeby.doctor.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DoctorProfileActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.linear_appointment_book)
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_profile);

        ButterKnife.bind(this);

        setUpActionBar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(com.tabeeby.doctor.BuildConfig.VERSION) {
            linearLayout.setVisibility(View.GONE);
        }else
        {
            linearLayout.setVisibility(View.VISIBLE);
        }
    }

    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0.2f);
        getSupportActionBar().setTitle("Profile");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
