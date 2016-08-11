package com.tabeeby.doctor.activities.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.adapter.FindPatientAdapter;
import com.tabeeby.doctor.adapter.NotificationAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity {
    ArrayList<String> arrayList;
    NotificationAdapter findDoctorAdapter;
    LinearLayoutManager linearLayoutManager;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        ButterKnife.bind(this);
        setUpActionBar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lv_notification_list);
        arrayList = new ArrayList<>();

        //this is for six cards
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");

        findDoctorAdapter = new NotificationAdapter(this, arrayList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(findDoctorAdapter);

    }

    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0.2f);
        getSupportActionBar().setTitle("Notification");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
