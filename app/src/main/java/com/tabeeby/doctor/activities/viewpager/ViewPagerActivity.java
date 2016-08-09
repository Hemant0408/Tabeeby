package com.tabeeby.doctor.activities.viewpager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.BuildConfig;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.login.LoginActivity;
import com.tabeeby.doctor.activities.signup.RegistrationScreen1Activity;
import com.tabeeby.doctor.activities.signup.SelectLanguageActivity;
import com.tabeeby.doctor.adapter.ViewPagerAdapter;


public class ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    protected View view;
    private ViewPager intro_images;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPagerAdapter mAdapter;
    private TextView tv_skip;
    private Context mContext;


    public static Integer[] mImageResources ;

    public static Integer[] mImage;

    public static Integer[] mImageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // To make activity full screen.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mContext = this;


        if(com.tabeeby.doctor.BuildConfig.VERSION) {
            mImageResources = new Integer[]{
                    R.string.manege_patients,
                    R.string.manege_appointements,
                    R.string.get_answers
            };

            mImage = new Integer[]{R.string.dummy_text,
                    R.string.dummy_text,
                    R.string.dummy_text
            };

            mImageText = new Integer[]{
                    R.drawable.pager1,
                    R.drawable.pager2,
                    R.drawable.pager3
            };
        }
        else
        {
            mImageResources = new Integer[]{
                    R.string.manege_doctors,
                    R.string.book_appointements,
                    R.string.get_answers
            };

            mImage = new Integer[]{R.string.dummy_text,
                    R.string.dummy_text,
                    R.string.dummy_text
            };

            mImageText = new Integer[]{
                    R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher
            };
        }


        setReference();
    }

    public void setReference() {
        intro_images = (ViewPager) findViewById(R.id.pager_introduction);
        tv_skip = (TextView) findViewById(R.id.tv_skip);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        tv_skip.setOnClickListener(this);
        mAdapter = new ViewPagerAdapter(mContext, mImageResources);
        intro_images.setAdapter(mAdapter);
        intro_images.setCurrentItem(0);
        intro_images.setOnPageChangeListener(this);
        setUiPageViewController();
        if (intro_images.getCurrentItem() == 0) {
        }
    }

    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);
            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_skip:
                Intent i = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }
        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void nextStep(View view) {
        startActivity(new Intent(mContext, SelectLanguageActivity.class));
    }
}