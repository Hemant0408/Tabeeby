package com.tabeeby.doctor.activities.events;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tabeeby.doctor.R;
import com.tabeeby.doctor.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;


public class EventDetailActivity extends AppCompatActivity {
    private Context mContext;
    private String mStringEventTitle,mStringDesc,mStringLocation,mStringDate,mStringUsername,mStringPath;

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

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);
        setUpActionBar();
        mContext=this;

        if(getIntent()!=null)
        {
            mStringEventTitle  = getIntent().getStringExtra("EventTitle");
            mStringDate        = getIntent().getStringExtra("EventDate");
            mStringDesc        = getIntent().getStringExtra("EventDesc");
            mStringLocation    = getIntent().getStringExtra("EventLocation");
            mStringUsername    = getIntent().getStringExtra("EventUserName");
            mStringPath        = getIntent().getStringExtra("EventBannerPath");

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
    }

    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0.2f);
        getSupportActionBar().setTitle("Event Details");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }
}
