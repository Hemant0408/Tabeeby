package com.tabeeby.doctor.activities.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.facebook.FacebookSdk;
import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.maintabactivity.MainActivity;
import com.tabeeby.doctor.activities.signup.OtpPageActivity;
import com.tabeeby.doctor.activities.signup.SelectLanguageActivity;
import com.tabeeby.doctor.application.application;
import com.tabeeby.doctor.httpclient.API;
import com.tabeeby.doctor.utils.ServerUtils;
import com.tabeeby.doctor.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Context mContext;
    API api;

    @Bind(R.id.edtUserName)
    protected EditText edtUserName;

    @Bind(R.id.edtPassword)
    protected EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext = this;
        api = application.getInstance().getHttpService();
    }

    public void needAnAccount(View view) {
        startActivity(new Intent(mContext, SelectLanguageActivity.class));
        finishAffinity();
    }

    public void nextStep(View view) {
       /* startActivity(new Intent(mContext, MainActivity.class));
        finishAffinity();*/
        makeHTTPcall();
    }

    private void makeHTTPcall() {
        Call<ResponseBody> responseBodyCall = api.loginApi(edtUserName.getText().toString().trim(), edtPassword.getText().toString().trim());
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.code() == ServerUtils.STATUS_OK) {
                    try {
                        if(response!=null) {
                            String responseBody = Utils.convertTypedBodyToString(response.body());
                            JSONObject jsonObject = new JSONObject(responseBody);
                            String data = jsonObject.getString("data");
                            if (data != null) {
                                JSONObject DataJsonObject = new JSONObject(data);
                                if (DataJsonObject.getString("status").equals("10")) {
                                    Utils.storeSharedPreference(mContext, getString(R.string.pref_access_token), DataJsonObject.getString(getString(R.string.pref_access_token)).trim());
                                    Utils.storeSharedPreference(mContext, getString(R.string.pref_email), DataJsonObject.getString(getString(R.string.pref_email)).trim());
                                    Utils.storeSharedPreference(mContext, getString(R.string.pref_picture), DataJsonObject.getString(getString(R.string.pref_picture)).trim());
                                    Utils.storeSharedPreference(mContext, getString(R.string.pref_user_type), DataJsonObject.getString(getString(R.string.pref_user_type)).trim());
                                    Utils.storeSharedPreference(mContext, getString(R.string.pref_login_type), DataJsonObject.getString(getString(R.string.pref_login_type)).trim());
                                    Utils.storeSharedPreference(mContext, getString(R.string.pref_user_id), DataJsonObject.getString(getString(R.string.pref_user_id)).trim());
                                    Utils.createToastLong("Welcome to Tabeeby", mContext);
                                    startActivity(new Intent(mContext, MainActivity.class));
                                } else {
                                    Utils.createToastShort("User Not Verified", mContext);
                                }
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

}
