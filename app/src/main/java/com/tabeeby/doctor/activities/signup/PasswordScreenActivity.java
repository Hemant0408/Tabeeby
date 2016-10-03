package com.tabeeby.doctor.activities.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.maintabactivity.MainActivity;
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

public class PasswordScreenActivity extends AppCompatActivity {
    private Context mContext;
    private API api;

    @Bind(R.id.edtUserPassword)
    protected EditText mUserPassword;

    @Bind(R.id.edtUserConformPassword)
    protected EditText mConfirmUserPassword;

    @Bind(R.id.chkTermsandCondition)
    protected CheckBox mTermsAndCondition;

    @Bind(R.id.textInputPassword)
    protected TextInputLayout mTextInputPassword;

    @Bind(R.id.textInputConfirmPassword)
    protected TextInputLayout mTextInputComfirmPassword;

    @Bind(R.id.textInputUserName)
    protected TextInputLayout mTextInputUserName;

    @Bind(R.id.edtUserName)
    protected TextView mUserName;

    private String mEmail, mFirstName, mLastName, mUserType, mLoginType, mTerms, mMobileNumber, title, mFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_screen);
        mContext = this;
        ButterKnife.bind(this);
        api = MyApplication.getInstance().getHttpService();

        mUserPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTextInputPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mConfirmUserPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTextInputComfirmPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTextInputUserName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mUserName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!TextUtils.isEmpty(mUserName.getText().toString()))
                        makeHttpCallToCheckUserName();
                }

                return false;
            }
        });
    }

    private void makeHttpCallToCheckUserName() {
        Call<ResponseBody> responseBodyCall = api.getUserName(mUserName.getText().toString().trim());

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String responseBody = Utils.convertTypedBodyToString(response.body());
                if (response.code() == ServerUtils.STATUS_OK) {
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody);
                        if (jsonObject != null) {

                            // Error
                            /* {
  "error": {
    "username": [
      "Username not available"
    ]
  },
  "status": false,
  "message": "Username invalid/unavailable.",
  "code": 395
} */

// Success
                            /* {
  "data": [],
  "status": true,
  "message": "Username available!",
  "code": 397
} */

                            if (!jsonObject.getBoolean("status")) {
                                JSONObject errorJSONObject = jsonObject.getJSONObject("error");
                                if (errorJSONObject.has("username")) {
                                    mTextInputUserName.setError(errorJSONObject.getString("username").substring(1, errorJSONObject.getString("username").length() - 1));
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("***Call", call.toString());
                t.printStackTrace();
            }
        });
    }

    public void nextStep(View view) {
        if (getIntent() != null) {
            mEmail = getIntent().getStringExtra("UserEmail");
            mFirstName = getIntent().getStringExtra("FirstName");
            mLastName = getIntent().getStringExtra("LastName");
            mMobileNumber = getIntent().getStringExtra("MobileNumber");
            mFullName = getIntent().getStringExtra("FullName");
            title = getIntent().getStringExtra("title");
            mLoginType = "phone";

            //get user type if it doctor or patient
            if (com.tabeeby.doctor.BuildConfig.VERSION) {
                if (getIntent().getStringExtra("user_type").equals("doctor"))
                    mUserType = "doctor";
                else
                    mUserType = "provider";
            } else {
                mUserType = "patient";
            }
            //check terms and condition are checked or not
            if (validate()) {
                if (mTermsAndCondition.isChecked()) {
                    mTerms = "1";
                    makeHTTPcall();
                } else {
                    mTerms = "0";
                    Utils.createToastShort(getString(R.string.terms_and_condition_error_msg), mContext);
                }
            }
        }
    }

    public boolean validate() {
        if (mUserName.getText().toString().trim().equals("")) {
            mTextInputUserName.setError(getString(R.string.Enter_User_Name));
            return false;
        }
        if (mUserPassword.getText().toString().trim().equals("")) {
            mTextInputPassword.setError(getString(R.string.Enter_Password));
            return false;
        }
        if (mConfirmUserPassword.getText().toString().trim().equals("")) {
            mTextInputComfirmPassword.setError(getString(R.string.Enter_Confirm_Password));
            return false;
        }
        if (!mUserPassword.getText().toString().trim().equals(mConfirmUserPassword.getText().toString().trim())) {
            mTextInputComfirmPassword.setError(getString(R.string.Password_Mismatch));
            return false;
        }
        if (mUserPassword.getText().toString().trim().length() < 3) {
            mTextInputPassword.setError(getString(R.string.minimum_length));
            return false;
        }
        return true;
    }

    private void makeHTTPcall() {
        Call<ResponseBody> responseBodyCall = api.signupApi(mEmail, mUserPassword.getText().toString(), mConfirmUserPassword.getText().toString().trim(), mFirstName, mLastName, mFullName, mUserType, mLoginType, mTerms, mMobileNumber, Utils.retrieveSharedPreference(mContext, "Country"), title/*Utils.retrieveSharedPreference(mContext, "doctor_sub_type").toLowerCase()*/, Utils.retrieveSharedPreference(mContext, "Language"), mUserName.getText().toString().trim(),"");

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String responseBody = Utils.convertTypedBodyToString(response.body());
                if (response.code() == ServerUtils.STATUS_OK) {
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody);
                        //String data = jsonObject.getString("data");
                        if (jsonObject != null) {
                            JSONObject DataJsonObject = jsonObject.getJSONObject("data");
                            JSONObject userJsonObject = DataJsonObject.getJSONObject("user");
                            if (userJsonObject.getString("status").equals("0")) {
                                Utils.storeSharedPreference(mContext, getString(R.string.pref_access_token), userJsonObject.getString(getString(R.string.pref_access_token)).trim());
                                Utils.storeSharedPreference(mContext, getString(R.string.pref_email), userJsonObject.getString(getString(R.string.pref_email)).trim());
                                Utils.storeSharedPreference(mContext, getString(R.string.pref_picture), userJsonObject.getString(getString(R.string.pref_picture)).trim());
                                Utils.storeSharedPreference(mContext, getString(R.string.pref_user_type), userJsonObject.getString(getString(R.string.pref_user_type)).trim());
                                Utils.storeSharedPreference(mContext, getString(R.string.pref_login_type), userJsonObject.getString(getString(R.string.pref_login_type)).trim());
                                Utils.storeSharedPreference(mContext, getString(R.string.pref_user_id), userJsonObject.getString(getString(R.string.pref_user_id)).trim());
                                Intent intent = new Intent(mContext, OtpPageActivity.class);
                                intent.putExtra("uid", userJsonObject.getString(getString(R.string.pref_user_id)));
                                intent.putExtra("otpToken", userJsonObject.getString(getString(R.string.otp)));
                                startActivity(intent);
                                finish();
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
                Log.i("***Call", call.toString());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("onBackPressed", "onBackPressed");
    }
}
