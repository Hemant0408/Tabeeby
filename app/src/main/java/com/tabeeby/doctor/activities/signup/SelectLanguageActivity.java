package com.tabeeby.doctor.activities.signup;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.application.application;
import com.tabeeby.doctor.utils.Utils;

import java.text.Bidi;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectLanguageActivity extends AppCompatActivity {

    private Context mContext;

    @Bind(R.id.btn_next_step)
    Button btn_next_step;

    @Bind(R.id.txt_lang_english)
    protected TextView mTextViewEnglish;

    @Bind(R.id.txt_lang_arebic)
    protected TextView mTextViewArebic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
        mContext = this;
        ButterKnife.bind(this);

        if(Utils.retrieveSharedPreference(mContext,"Language")!=null)
        {
            if(Utils.retrieveSharedPreference(mContext,"Language").equals("E"))
            {
                Log.i("Language","English");
            }
            else if(Utils.retrieveSharedPreference(mContext,"Language").equals("A"))
            {
                Log.i("Language","Arabic");
            }
        }
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
        changeEnglish();
    }

    public void SelectArebic(View view) {
        changeArabic();
    }


    /**
     * Method that Update UI for Arabic locale.
     */
    public void changeArabic() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                String app_locale = "ar";
                Locale locale = new Locale(app_locale);
                Locale.setDefault(locale);

                //Configuration to query the current layout direction.
                Configuration config = new Configuration();
                config.locale = locale;
                getResources().updateConfiguration(config,
                        getResources().getDisplayMetrics());
                Bidi bidi = new Bidi(app_locale,
                        Bidi.DIRECTION_DEFAULT_RIGHT_TO_LEFT);
                bidi.isRightToLeft();
                application.updateLanguage(mContext, "ar");
                Utils.storeSharedPreference(mContext,"Language","A");
                startActivity(new Intent(mContext, SelectCountryActivity.class));

                return null;
            }

        }.execute();
    }

    /**
     * Method that Update UI for Default(English) locale.
     */
    public void changeEnglish() {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                String app_locale = "en";
                Locale locale = new Locale(app_locale);
                Locale.setDefault(locale);

                //Configuration to query the current layout direction.
                Configuration config = new Configuration();
                config.locale = locale;
                getResources().updateConfiguration(config,
                        getResources().getDisplayMetrics());
                Bidi bidi = new Bidi(app_locale,
                        Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);
                bidi.isLeftToRight();
                application.updateLanguage(mContext, "en");

                Utils.storeSharedPreference(mContext,"Language","E");
                startActivity(new Intent(mContext, SelectCountryActivity.class));

                return null;
            }

        }.execute();
    }
}
