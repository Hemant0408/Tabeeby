package com.tabeeby.doctor.activities.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.login.LoginActivity;
import com.tabeeby.doctor.application.MyApplication;
import com.tabeeby.doctor.utils.ServerUtils;
import com.tabeeby.doctor.utils.Utils;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    // CallbackManager callbackManager;

    private Context mContext;

    @Bind(R.id.edtFirstName)
    protected EditText mFirstName;

    @Bind(R.id.edtLastName)
    protected EditText mLastName;

    //@Bind(R.id.edtUserEmail)
    //protected EditText mUserEmail;

    @Bind(R.id.edtMobileNumber)
    protected EditText mMobileNumber;

    @Bind(R.id.textInputFirstName)
    protected TextInputLayout mTextInputFirstName;

    @Bind(R.id.textInputLastName)
    protected TextInputLayout mTextInputLastName;

    //@Bind(R.id.textInputUserEmail)
    //protected TextInputLayout mTextInputUserEmail;

    @Bind(R.id.textInputMobileNumber)
    protected TextInputLayout mTextInputMobileNumber;

    @Bind(R.id.input_type)
    protected TextInputLayout mTextInputFullName;

    @Bind(R.id.edt_full_name)
    protected EditText mFullName;

    @Bind(R.id.edtUserPassword)
    protected EditText mUserPassword;

    @Bind(R.id.chkTermsandCondition)
    protected CheckBox mTermsAndCondition;

    @Bind(R.id.textInputPassword)
    protected TextInputLayout mTextInputPassword;

    @Bind(R.id.textInputUserName)
    protected TextInputLayout mTextInputUserName;

    @Bind(R.id.edtUserName)
    protected TextView mUserName;

    Bundle bundle;

    String user_type, doctor_title, provider_title;
    private String mUserType, mLoginType, mTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_sign_up);
        mContext = this;
        ButterKnife.bind(this);



      /*  //Call facebook api
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();


        //callbackManager = CallbackManager.Factory.create();

        /*LoginButton loginButton = (LoginButton) findViewById(R.id.usersettings_fragment_login_button);
        loginButton.setReadPermissions("user_friends");
*/
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            user_type = bundle.getString("user_type");
            doctor_title = bundle.getString("title");
            provider_title = bundle.getString("title");
        }

        // Callback registration
        /*loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                Log.i("***Profile_Url", profile.getProfilePictureUri(10, 10).toString());
                Utils.storeSharedPreference(mContext, "user_name", profile.getName());
                startActivity(new Intent(mContext, MainActivity.class));
                finishAffinity();
            }

            @Override
            public void onCancel() {
                Log.i("Info", "cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.i("Info", exception.toString());
                // App code
            }
        });*/

        //Remove error text from field
        mFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTextInputFirstName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTextInputLastName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTextInputFullName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        /*mUserEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTextInputUserEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });*/
        mMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTextInputMobileNumber.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

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

        Bundle bundle1 = getIntent().getExtras();

        if (bundle1 != null) {
            if (Utils.retrieveSharedPreference(SignUpActivity.this, "doctor_type").equals("health_care_provider")) {
                mTextInputFullName.setVisibility(View.VISIBLE);
                mTextInputFirstName.setVisibility(View.GONE);
                mTextInputLastName.setVisibility(View.GONE);
                doctor_title = "";
            } else {
                mTextInputFullName.setVisibility(View.GONE);
                mTextInputFirstName.setVisibility(View.VISIBLE);
                mTextInputLastName.setVisibility(View.VISIBLE);
                provider_title = "";
            }
        }
    }

    private void makeHttpCallToCheckUserName() {
        Call<ResponseBody> responseBodyCall = MyApplication.getInstance().getHttpService().getUserName(mUserName.getText().toString().trim());

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
        /*if (validate()) {
            Intent i = new Intent(mContext, PasswordScreenActivity.class);
            i.putExtra("UserEmail", ""*//*mUserEmail.getText().toString().trim()*//*);
            i.putExtra("FirstName", mFirstName.getText().toString().trim());
            i.putExtra("LastName", mLastName.getText().toString().trim());
            i.putExtra("MobileNumber", mMobileNumber.getText().toString().trim());
            i.putExtra("FullName", mFullName.getText().toString().trim());
            i.putExtra("user_type", user_type);
            i.putExtra("title", title);
            startActivity(i);
        }*/
        mLoginType = "phone";
        //get user type if it doctor or patient
        if (com.tabeeby.doctor.BuildConfig.VERSION) {
            if (getIntent().getStringExtra("user_type").equals("doctor")) {
                mUserType = "doctor";
            } else {
                mUserType = "provider";
            }
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

    private void makeHTTPcall() {

        Utils.ShowProgressDialog(SignUpActivity.this);
        Call<ResponseBody> responseBodyCall = MyApplication.getInstance().getHttpService().signupApi("", mUserPassword.getText().toString(), mUserPassword.getText().toString(), mFirstName.getText().toString(), mLastName.getText().toString(), mFullName.getText().toString(), mUserType, mLoginType, mTerms, mMobileNumber.getText().toString(), Utils.retrieveSharedPreference(mContext, "Country"), doctor_title/*Utils.retrieveSharedPreference(mContext, "doctor_sub_type").toLowerCase()*/, Utils.retrieveSharedPreference(mContext, "Language"), mUserName.getText().toString().trim(), provider_title);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String responseBody = Utils.convertTypedBodyToString(response.body());
                if (response.code() == ServerUtils.STATUS_OK) {
                    try {
                        Utils.DismissProgressDialog();
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
                Utils.DismissProgressDialog();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //check validation of form
    public boolean validate() {
        if (mTextInputFullName.getVisibility() == View.VISIBLE) {
            if (mFullName.getText().toString().trim().equals("")) {
                mTextInputFullName.setError("Enter Full Name");
                return false;
            }
        }
        if (mTextInputFirstName.getVisibility() == View.VISIBLE) {
            if (mFirstName.getText().toString().trim().equals("")) {
                mTextInputFirstName.setError(getString(R.string.signup_first_name_error_msg));
                return false;
            }
        }
        if (mTextInputLastName.getVisibility() == View.VISIBLE) {
            if (mLastName.getText().toString().trim().equals("")) {
                mTextInputLastName.setError(getString(R.string.signup_last_name_error_msg));
                return false;
            }
        }
        /*if (mUserEmail.getText().toString().trim().equals("")) {
            mTextInputUserEmail.setError(getString(R.string.signup_email_address_error_msg));
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mUserEmail.getText().toString().trim()).matches()) {
            mTextInputUserEmail.setError(getString(R.string.signup_valid_email_address_msg));
            return false;
        }*/
        if (mMobileNumber.getText().toString().trim().equals("")) {
            mTextInputMobileNumber.setError(getString(R.string.signup_mobile_number_error_msg));
            return false;
        }

        if (mMobileNumber.getText().toString().trim().length() < 10) {
            mTextInputMobileNumber.setError(getString(R.string.signup_valid_mobile_number_error_msg));
            return false;
        }

        if (mUserName.getText().toString().trim().equals("")) {
            mTextInputUserName.setError(getString(R.string.Enter_User_Name));
            return false;
        }
        if (mUserPassword.getText().toString().trim().equals("")) {
            mTextInputPassword.setError(getString(R.string.Enter_Password));
            return false;
        }
        if (mUserPassword.getText().toString().trim().length() < 3) {
            mTextInputPassword.setError(getString(R.string.minimum_length));
            return false;
        }

        return true;
    }

    public void alreadyMember(View view) {
        startActivity(new Intent(mContext, LoginActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private String removeFirstAndLastCharacters(String message) {

        return message.substring(1, message.length() - 1);
    }

    private void setErrorMessage(String message) {


        switch (message) {

            case "":
                mTextInputFullName.setError(removeFirstAndLastCharacters(message));
                return;
        }
    }
}
