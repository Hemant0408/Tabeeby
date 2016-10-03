package com.tabeeby.doctor.activities.events;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.tabeeby.doctor.R;
import com.tabeeby.doctor.adapter.EventAdapter;
import com.tabeeby.doctor.application.MyApplication;
import com.tabeeby.doctor.httpclient.API;
import com.tabeeby.doctor.model.Events;
import com.tabeeby.doctor.utils.ServerUtils;
import com.tabeeby.doctor.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventDetailActivity extends AppCompatActivity {
    private Context mContext;
    private String mStringEventTitle,mStringDesc,mStringLocation,mStringDate,mStringUsername,mStringPath,mStringEventId;

    @Bind(R.id.txt_event_title)
    protected TextView mTextViewEventTitle;

    @Bind(R.id.txt_event_data_time)
    protected  TextView mTextViewEventDataTime;

    @Bind(R.id.txt_event_location)
    protected  TextView mTextViewEventLocation;

    @Bind(R.id.txt_event_place)
    protected TextView mTextViewEventPalce;

    @Bind(R.id.txt_event_description)
    protected  TextView mTextViewEventDescription;

    @Bind(R.id.img_event_banner)
    protected ImageView mImageViewBanner;

    @Bind(R.id.txt_attending)
    protected TextView mTextViewAttending;

    @Bind(R.id.txt_not_attending)
    protected TextView mTextViewNotAttending;

    @Bind(R.id.txt_may_be)
    protected TextView mTextViewMayBe;

    @Bind(R.id.bottom_toolbar_attinging)
    protected LinearLayout mLinearLayoutAttending;

    @Bind(R.id.bottom_toolbar_not_attending)
    protected LinearLayout mLinearLayoutNotAttending;

    @Bind(R.id.bottom_toolbar_may_be)
    protected LinearLayout mLinearLayoutMayBe;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    API api;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);
        setUpActionBar();
        mContext=this;

        api = MyApplication.getInstance().getHttpService();

        if(getIntent()!=null)
        {
            mStringEventTitle  = getIntent().getStringExtra("EventTitle");
            mStringDate        = getIntent().getStringExtra("EventDate");
            mStringDesc        = getIntent().getStringExtra("EventDesc");
            mStringLocation    = getIntent().getStringExtra("EventLocation");
            mStringUsername    = getIntent().getStringExtra("EventUserName");
            mStringPath        = getIntent().getStringExtra("EventBannerPath");
            mStringEventId     = getIntent().getStringExtra("EventId");

            mTextViewEventTitle.setText(mStringEventTitle);
            mTextViewEventDescription.setText(mStringDesc);
            mTextViewEventLocation.setText(mStringLocation);
            mTextViewEventPalce.setText(mStringUsername);

            mTextViewEventDataTime.setText(Utils.ConvertDateUnixtoNormal(mStringDate,"dd MMM")+" to "+
                    Utils.ConvertDateUnixtoNormal(mStringDate,"dd MMM")+" | "+
                    Utils.ConvertDateUnixtoNormal(mStringDate,"hh a")+" to "+
                    Utils.ConvertDateUnixtoNormal(mStringDate,"hh a"));

            Picasso.with(mContext)
                    .load(mStringPath)
                    .placeholder(R.drawable.default_profile)
                    .into(mImageViewBanner);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTextViewAttending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Attinding("1");
            }
        });
        mTextViewNotAttending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Attinding("3");
            }
        });
        mTextViewMayBe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Attinding("2");
            }
        });

        mLinearLayoutAttending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Attinding("1");
            }
        });

        mLinearLayoutNotAttending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Attinding("3");
            }
        });

        mLinearLayoutMayBe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Attinding("2");
            }
        });

    }

    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0.2f);
        getSupportActionBar().setTitle("Event Details");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }



    public void Attinding(String mStatus)
    {
        Utils.ShowProgressDialog(mContext);
        Log.i("EventId",mStringEventId);
        Log.i("UserId",Utils.retrieveSharedPreference(mContext,"id"));
        String header = "Bearer " + Utils.retrieveSharedPreference(mContext, getString(R.string.pref_access_token));
        Call<ResponseBody> responseBodyCall = api.attendEventApi(header,Utils.retrieveSharedPreference(mContext,"id"),mStringEventId,mStatus);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Utils.DismissProgressDialog();
                if (response.code() == ServerUtils.STATUS_OK) {
                    try {

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
}
