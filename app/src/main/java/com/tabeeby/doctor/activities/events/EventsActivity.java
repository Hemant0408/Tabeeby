package com.tabeeby.doctor.activities.events;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.vedioupload.Main2Activity;
import com.tabeeby.doctor.adapter.EventAdapter;
import com.tabeeby.doctor.application.MyApplication;
import com.tabeeby.doctor.httpclient.API;
import com.tabeeby.doctor.model.Events;
import com.tabeeby.doctor.model.QuestionsModel;
import com.tabeeby.doctor.utils.ConnectionDetector;
import com.tabeeby.doctor.utils.ServerUtils;
import com.tabeeby.doctor.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsActivity extends AppCompatActivity {
    private EventAdapter findDoctorAdapter;
    private LinearLayoutManager linearLayoutManager;
    private API api;
    private ArrayList<Events> mArraylistEventslist;
    private RecyclerView recyclerView;
    private Context mContext;
    private Bundle bundle;


    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.fab)
    protected FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);
        setUpActionBar();
        mContext=this;
        bundle=savedInstanceState;

        api = MyApplication.getInstance().getHttpService();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.lv_events_list);


        if(ConnectionDetector.checkInternetConnection(mContext)) {
            getDisplayList();
        }
        else {
            Utils.ErrorMessage((Activity) mContext,bundle,getResources().getString(R.string.no_internetconnection));
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext,AddEventActivity.class));
            }
        });

    }


    private void getDisplayList()
    {
        Utils.ShowProgressDialog(mContext);
        Call<ResponseBody> responseBodyCall = api.EventListApi();
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == ServerUtils.STATUS_OK) {
                    try {
                        Utils.DismissProgressDialog();
                        if(response!=null) {
                            String responseBody = Utils.convertTypedBodyToString(response.body());
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Events>>() {
                            }.getType();


                            JSONObject jsonObject = new JSONObject(responseBody);
                            String data = jsonObject.getString("data");
                            JSONObject DataJsonObject = new JSONObject(data);
                            JSONArray jsonArray = DataJsonObject.getJSONArray("events");
                            mArraylistEventslist = new ArrayList<>();
                            mArraylistEventslist.clear();
                            recyclerView.removeAllViews();
                            mArraylistEventslist = gson.fromJson(jsonArray.toString(), type);


                            if (mArraylistEventslist.size() != 0) {
                                findDoctorAdapter = new EventAdapter(mContext, mArraylistEventslist);
                                linearLayoutManager = new LinearLayoutManager(mContext);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(findDoctorAdapter);
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0.2f);
        getSupportActionBar().setTitle(getString(R.string.events));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
