package com.tabeeby.doctor.activities.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

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

public class SignUpActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_sign_up);
        mContext = this;


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
    }

    public void nextStep(View view) {
        startActivity(new Intent(mContext, OtpPageActivity.class));
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











  /*  private UiLifecycleHelper uiHelper;
    private View otherView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set View that should be visible after log-in invisible initially
        otherView = (View) findViewById(R.id.other_views);
        otherView.setVisibility(View.GONE);
        // To maintain FB Login session
        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);
    }

    // Called when session changes
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state,
                         Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    // When session is changed, this method is called from callback method
    private void onSessionStateChange(Session session, SessionState state,
                                      Exception exception) {
        final TextView name = (TextView) findViewById(R.id.name);
        final TextView gender = (TextView) findViewById(R.id.gender);
        final TextView location = (TextView) findViewById(R.id.location);
        // When Session is successfully opened (User logged-in)
        if (state.isOpened()) {
            Log.i(TAG, "Logged in...");
            // make request to the /me API to get Graph user
            Request.newMeRequest(session, new Request.GraphUserCallback() {

                // callback after Graph API response with user
                // object
                @Override
                public void onCompleted(GraphUser user, Response response) {
                    if (user != null) {
                        // Set view visibility to true
                        otherView.setVisibility(View.VISIBLE);
                        // Set User name
                        name.setText("Hello " + user.getName());
                        // Set Gender
                        gender.setText("Your Gender: "
                                + user.getProperty("gender").toString());
                        location.setText("Your Current Location: "
                                + user.getLocation().getProperty("name")
                                .toString());
                    }
                }
            }).executeAsync();
        } else if (state.isClosed()) {
            Log.i(TAG, "Logged out...");
            otherView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uiHelper.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "OnActivityResult...");
    }

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }
}*/