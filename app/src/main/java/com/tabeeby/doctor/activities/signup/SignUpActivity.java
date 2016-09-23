package com.tabeeby.doctor.activities.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.login.LoginActivity;
import com.tabeeby.doctor.activities.maintabactivity.MainActivity;
import com.tabeeby.doctor.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    private Context mContext;

    @Bind(R.id.edtFirstName)
    protected EditText mFirstName;

    @Bind(R.id.edtLastName)
    protected EditText mLastName;

    @Bind(R.id.edtUserEmail)
    protected EditText mUserEmail;

    @Bind(R.id.edtMobileNumber)
    protected EditText mMobileNumber;

    @Bind(R.id.textInputFirstName)
    protected TextInputLayout mTextInputFirstName;

    @Bind(R.id.textInputLastName)
    protected TextInputLayout mTextInputLastName;

    @Bind(R.id.textInputUserEmail)
    protected TextInputLayout mTextInputUserEmail;

    @Bind(R.id.textInputMobileNumber)
    protected TextInputLayout mTextInputMobileNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mContext = this;
        ButterKnife.bind(this);

        //Call facebook api
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.usersettings_fragment_login_button);
        loginButton.setReadPermissions("user_friends");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
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
        });

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
        mUserEmail.addTextChangedListener(new TextWatcher() {
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
        });
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
    }

    public void nextStep(View view) {
        if (validate()) {
            Intent i = new Intent(mContext, PasswordScreenActivity.class);
            i.putExtra("UserEmail", mUserEmail.getText().toString().trim());
            i.putExtra("FirstName", mFirstName.getText().toString().trim());
            i.putExtra("LastName", mLastName.getText().toString().trim());
            i.putExtra("MobileNumber", mMobileNumber.getText().toString().trim());
            startActivity(i);
        }
    }

    //check validation of form
    public boolean validate() {
        if (mFirstName.getText().toString().trim().equals("")) {
            mTextInputFirstName.setError(getString(R.string.signup_first_name_error_msg));
            return false;
        }
        if (mLastName.getText().toString().trim().equals("")) {
            mTextInputLastName.setError(getString(R.string.signup_last_name_error_msg));
            return false;
        }
        if (mUserEmail.getText().toString().trim().equals("")) {
            mTextInputUserEmail.setError(getString(R.string.signup_email_address_error_msg));
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mUserEmail.getText().toString().trim()).matches()) {
            mTextInputUserEmail.setError(getString(R.string.signup_valid_email_address_msg));
            return false;
        }
        if (mMobileNumber.getText().toString().trim().equals("")) {
            mTextInputMobileNumber.setError(getString(R.string.signup_mobile_number_error_msg));
            return false;
        }

        if (mMobileNumber.getText().toString().trim().length() < 10 || mMobileNumber.getText().toString().trim().length() > 10) {
            mTextInputMobileNumber.setError(getString(R.string.signup_valid_mobile_number_error_msg));
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
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}