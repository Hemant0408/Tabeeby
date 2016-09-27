package com.tabeeby.doctor.activities.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.tabeeby.doctor.BuildConfig;
import com.tabeeby.doctor.R;
import com.tabeeby.doctor.application.application;
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

    private String mEmail,mFirstName,mLastName,mUserType,mLoginType,mTerms,mMobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_screen);
        mContext = this;
        ButterKnife.bind(this);
        api = application.getInstance().getHttpService();


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
    }

    public void nextStep(View view) {
        if(getIntent()!=null)
        {
            mEmail=getIntent().getStringExtra("UserEmail");
            mFirstName=getIntent().getStringExtra("FirstName");
            mLastName=getIntent().getStringExtra("LastName");
            mMobileNumber=getIntent().getStringExtra("MobileNumber");
            mLoginType="email";

            //get user type if it doctor or patient
            if (com.tabeeby.doctor.BuildConfig.VERSION)
            { mUserType="doctor";}
            else
            {mUserType="patient";}
            //check terms and condition are checked or not
            if(validate()) {
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

    public boolean validate()
    {
        if(mUserPassword.getText().toString().trim().equals(""))
        {
            mTextInputPassword.setError(getString(R.string.Enter_Password));
         return false;
        }
        if(mConfirmUserPassword.getText().toString().trim().equals(""))
        {
            mTextInputComfirmPassword.setError(getString(R.string.Enter_Confirm_Password));
            return false;
        }
        if(!mUserPassword.getText().toString().trim().equals(mConfirmUserPassword.getText().toString().trim()))
        {
            mTextInputComfirmPassword.setError(getString(R.string.Password_Mismatch));
            return false;
        }
        return true;
    }


    private void makeHTTPcall() {
        Call<ResponseBody> responseBodyCall = api.signupApi(mEmail,mUserPassword.getText().toString(),mConfirmUserPassword.getText().toString().trim(),mFirstName,mLastName,null,Utils.retrieveSharedPreference(mContext,"doctor_type").trim(),mLoginType,mTerms,mMobileNumber,"India",Utils.retrieveSharedPreference(mContext,"doctor_sub_type").toLowerCase(),Utils.retrieveSharedPreference(mContext,"Language"));
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String responseBody = Utils.convertTypedBodyToString(response.body());
                if (response.code() == ServerUtils.STATUS_OK) {
                    try {
                        JSONObject jsonObject=new JSONObject(responseBody);
                        Log.i("****StatusCode",jsonObject.getString("code"));
                        startActivity(new Intent(mContext,OtpPageActivity.class));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("***Call",call.toString());
                t.printStackTrace();
            }
        });
    }
}
