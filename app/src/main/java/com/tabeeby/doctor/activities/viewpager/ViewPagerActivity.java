package com.tabeeby.doctor.activities.viewpager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.login.LoginActivity;
import com.tabeeby.doctor.activities.signup.SelectLanguageActivity;
import com.tabeeby.doctor.adapter.ViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    public static Integer[] mImageResources;
    public static Integer[] mImage;
    public static Integer[] mImageText;
    protected View view;

    @Bind(R.id.pager_introduction)
    protected ViewPager intro_images;

    @Bind(R.id.viewPagerCountDots)
    protected LinearLayout pager_indicator;

    @Bind(R.id.tv_skip)
    protected TextView tv_skip;

    private int dotsCount;
    private ImageView[] dots;
    private ViewPagerAdapter mAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // To make activity full screen.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mContext = this;
        ButterKnife.bind(this);

        if (com.tabeeby.doctor.BuildConfig.VERSION) {
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
                    R.drawable.ic_patient_intro_48dp,
                    R.drawable.ic_appointment_intro_48dp,
                    R.drawable.ic_qna_intro_48dp
            };
        } else {
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
                    R.drawable.ic_dr_intro_48dp,
                    R.drawable.ic_appointment_intro_48dp,
                    R.drawable.ic_qna_intro_48dp
            };
        }

        setReference();
    }

    public void setReference() {
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

            params.setMargins(8, 0, 8, 0);
            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_skip:
                Intent i = new Intent(mContext, LoginActivity.class);
                startActivity(i);
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