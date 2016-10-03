package com.tabeeby.doctor.activities.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.maintabactivity.MainActivity;
import com.tabeeby.doctor.activities.signup.SelectLanguageActivity;
import com.tabeeby.doctor.application.MyApplication;
import com.tabeeby.doctor.httpclient.API;
import com.tabeeby.doctor.utils.ServerUtils;
import com.tabeeby.doctor.utils.Utils;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
    API api;

    @Bind(R.id.edtUserName)
    protected EditText edtUserName;

    @Bind(R.id.edtPassword)
    protected EditText edtPassword;

    @Bind(R.id.tv_forgot_password)
    protected TextView tv_forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext = this;
        api = MyApplication.getInstance().getHttpService();
        tv_forgot_password.setOnClickListener(this);
    }

    public void needAnAccount(View view) {
        startActivity(new Intent(mContext, SelectLanguageActivity.class));
        finishAffinity();
    }

    public void nextStep(View view) {
        makeHTTPcall();
    }

    private void makeHTTPcall() {
        Utils.ShowProgressDialog(mContext);
        Call<ResponseBody> responseBodyCall = api.loginApi(edtUserName.getText().toString().trim(), edtPassword.getText().toString().trim());
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Utils.DismissProgressDialog();
                String responseBody = Utils.convertTypedBodyToString(response.body());
                if (response.code() == ServerUtils.STATUS_OK) {
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody);
                        String data = jsonObject.getString("data");
                        if (data != null) {
                            JSONObject DataJsonObject = new JSONObject(data);
                            JSONObject UserJsonObjct=DataJsonObject.getJSONObject("user");

                            if (UserJsonObjct.getString("status").equals("10")) {
                                Utils.storeSharedPreference(mContext, getString(R.string.pref_access_token), UserJsonObjct.getString(getString(R.string.pref_access_token)).trim());
                                Utils.storeSharedPreference(mContext, getString(R.string.pref_email), UserJsonObjct.getString(getString(R.string.pref_email)).trim());
                                Utils.storeSharedPreference(mContext, getString(R.string.pref_picture), UserJsonObjct.getString(getString(R.string.pref_picture)).trim());
                                Utils.storeSharedPreference(mContext, getString(R.string.pref_user_type), UserJsonObjct.getString(getString(R.string.pref_user_type)).trim());
                                Utils.storeSharedPreference(mContext, getString(R.string.pref_login_type), UserJsonObjct.getString(getString(R.string.pref_login_type)).trim());
                                Utils.storeSharedPreference(mContext, getString(R.string.pref_user_id), UserJsonObjct.getString(getString(R.string.pref_user_id)).trim());
                                Utils.createToastLong("Welcome to Tabeeby", mContext);
                                startActivity(new Intent(mContext, MainActivity.class));
                            } else {
                                Utils.createToastShort("User Not Verified", mContext);
                            }
                        }
                    } catch (Exception e) {
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

    private Intent intent;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_forgot_password:
                intent = new Intent(this, ForgotPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
