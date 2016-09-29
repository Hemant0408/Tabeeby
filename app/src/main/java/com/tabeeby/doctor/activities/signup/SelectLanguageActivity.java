package com.tabeeby.doctor.activities.signup;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.application.MyApplication;
import com.tabeeby.doctor.utils.Utils;

import java.text.Bidi;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectLanguageActivity extends AppCompatActivity {

    @Bind(R.id.txt_lang_english)
    protected TextView mTextViewEnglish;

    @Bind(R.id.txt_lang_arebic)
    protected TextView mTextViewArebic;

    @Bind(R.id.btn_next_step)
    Button btn_next_step;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
        mContext = this;
        ButterKnife.bind(this);

        if (Utils.retrieveSharedPreference(mContext, "Language") != null) {
            if (Utils.retrieveSharedPreference(mContext, "Language").equals("en")) {
                mTextViewEnglish.setCompoundDrawables(getResources().getDrawable(R.drawable.ic_gallery_white_48dp), null, null, null);
                mTextViewArebic.setCompoundDrawables(null, null, null, null);
            } else {
                if (Utils.retrieveSharedPreference(mContext, "Language").equals("ar")) {
                    mTextViewArebic.setCompoundDrawables(getResources().getDrawable(R.drawable.ic_gallery_white_48dp), null, null, null);
                    mTextViewEnglish.setCompoundDrawables(null, null, null, null);
                }
            }
        }
    }

    @OnClick({R.id.btn_next_step})
    public void onClickNextButton() {
        startActivity(new Intent(mContext, SelectCountryActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void nextStep(View view) {
        changeLanguage("en", "L");
    }

    public void SelectArebic(View view) {
        changeLanguage("ar", "R");
    }

    /**
     * Method that Update UI for Arabic locale.
     */
    public void changeLanguage(final String app_local, final String flag) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                // String app_locale = "ar";
                Locale locale = new Locale(app_local);
                Locale.setDefault(locale);

                //Configuration to query the current layout direction.
                Configuration config = new Configuration();
                config.locale = locale;
                getResources().updateConfiguration(config,
                        getResources().getDisplayMetrics());
                if (flag.equals("R")) {
                    Bidi bidi = new Bidi(app_local,
                            Bidi.DIRECTION_DEFAULT_RIGHT_TO_LEFT);
                    bidi.isRightToLeft();

                    Utils.storeSharedPreference(mContext, "Language", "ar");
                } else {
                    Bidi bidi = new Bidi(app_local,
                            Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);
                    bidi.isLeftToRight();
                    Utils.storeSharedPreference(mContext, "Language", "en");
                    // MyApplication.updateLanguage(mContext, "en");
                }
                MyApplication.updateLanguage(mContext, app_local);
                startActivity(new Intent(mContext, SelectCountryActivity.class));
                finish();
                return null;
            }

        }.execute();
    }

}
