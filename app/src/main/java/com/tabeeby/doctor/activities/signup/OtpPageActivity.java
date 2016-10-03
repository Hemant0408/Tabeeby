package com.tabeeby.doctor.activities.signup;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tabeeby.doctor.CustomClasses.CustomCountDownTimer;
import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.upoladpicture.UploadProfilePicture;
import com.tabeeby.doctor.application.MyApplication;
import com.tabeeby.doctor.utils.ConnectionDetector;
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

public class OtpPageActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;

    private long timerTimeValue = 180000;// initially set it to 3 minutes
    private long timerTimeOnPauseValue = 180000;

    @Bind(R.id.forgot_password_Edittext)
    EditText forgot_password_Edittext;

    @Bind(R.id.timer_textView)
    TextView timer_textView;
    @Bind(R.id.otp_error)
    TextView otp_error;
    @Bind(R.id.submit_button)
    Button submit_button;

    private long systemTimeOnPaused = 0;
    private boolean isPaused = false, isTimerEnded = false;
    private Intent intent;
    private String otpToken, uid;//="rnoYmFKrQITNt2nVk-fQ7pLYdpl5ZMHQ_1461921708";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);
        ButterKnife.bind(this);
        mContext = this;
        submit_button.setOnClickListener(this);
        timer_textView.setOnClickListener(this);
        countDownTimer.start();

        intent = getIntent();
        if (intent != null) {
            otpToken = intent.getStringExtra("otpToken");
            uid = intent.getStringExtra("uid");
            Log.i("TAG", "otpToken :" + otpToken);
            forgot_password_Edittext.setText(otpToken);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            progressDialog = new ProgressDialog(new ContextThemeWrapper(this, android.R.style.Theme_Holo_Light_Dialog));
        } else {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    public void nextStep(View view) {
        startActivity(new Intent(mContext, UploadProfilePicture.class));
        finishAffinity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        countDownTimer.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
        systemTimeOnPaused = System.currentTimeMillis();
        Log.i("TAG", "time paused at :" + systemTimeOnPaused);
        isPaused = true;
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        if (isPaused) {

            resumeTimer();
        }
        isPaused = false;
    }

    private void resumeTimer() {
        systemTimeOnPaused = System.currentTimeMillis() - systemTimeOnPaused;
        //Log.i("TAG","time difference from paused :"+systemTimeOnPaused);

        long diff = timerTimeValue - timerTimeOnPauseValue;
        // Log.i("TAG","time difference with timer :"+diff);
        timerTimeValue = timerTimeValue - systemTimeOnPaused - diff;

        if (timerTimeValue > 0) {
            //    Log.i("TAG","time remaining :"+timerTimeValue);
            countDownTimer.setMillisInFuture(timerTimeValue);
            countDownTimer.start();
        } else {
            timer_textView.setText(getResources().getText(R.string.otp_resend_mssg));
            timer_textView.setTextColor(getResources().getColor(R.color.red));
            isTimerEnded = true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_button:
                if (forgot_password_Edittext.getText().toString().trim().length() > 3) {
                    checkAndValidateOnServer(true);
                } else {
                    Utils.createToastLong("Enter valid OTP password", OtpPageActivity.this);
                }
                break;

            case R.id.timer_textView:
                /*isTimerEnded=false;
                countDownTimer.setMillisInFuture(180000); // start timer with 3 minutes timer
                countDownTimer.start();*/
                if (isTimerEnded) {
                    checkAndValidateOnServer(false);
                }
                break;
        }

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // FireToast.makeToast(context,"Recived");
            final Bundle bundle = intent.getExtras();
            SmsMessage[] messages;
            try {
                if (bundle != null) {
                    Object[] pdus = (Object[]) bundle.get("pdus");

                    assert pdus != null;
                    messages = new SmsMessage[pdus.length];

                    for (int i = 0; i < messages.length; i++) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            String format = bundle.getString("format");
                            messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                        } else {
                            messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        }

                        String message = messages[i].getMessageBody();
                        if (message.contains("Type a Thought Security Code:")) {
                            String onlyNumericText = message.replaceAll("\\D", "");
                            forgot_password_Edittext.setText(onlyNumericText.trim());
                            //  unregisterReceiver(broadcastReceiver);
                            if (ConnectionDetector.checkInternetConnection(OtpPageActivity.this)) {
                                checkAndValidateOnServer(true);
                            } else {
                                ConnectionDetector.showConnectionErrorMessage(OtpPageActivity.this);
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    };

    private void checkAndValidateOnServer(final boolean OTPverify) {
        otp_error.setText("");
        Call<ResponseBody> response;
        if (OTPverify) {
            progressDialog.setMessage(getResources().getString(R.string.please_wait_verifying_otp));
            progressDialog.show();
            response = MyApplication.getInstance().getHttpService().postOTPverify("Bearer " + Utils.retrieveSharedPreference(mContext, getString(R.string.pref_access_token)), forgot_password_Edittext.getText().toString().trim(), uid);

        } else {
            progressDialog.setMessage(getResources().getString(R.string.please_wait));
            progressDialog.show();
            response = MyApplication.getInstance().getHttpService().postOTPresend(uid);
        }
        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == ServerUtils.STATUS_OK) {
                    String responseJson = Utils.convertTypedBodyToString(response.body());
                    Log.i("TAG", "response :" + responseJson);
                    if (OTPverify) {

                        try {
                            /*JSONArray jsonArray = new JSONArray(responseJson);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.cancel();
                                }
                                JSONObject object = jsonArray.getJSONObject(i);

                                if (object.getInt("responseStatusCode") == 1) {
                                    //sharedPreferences.edit().putString("authkey", object.getString("authkey")).apply();
                                    //FireToast.makeToast(OTPactivity.this,"registerd");
                                    Intent intent = new Intent(OtpPageActivity.this, UploadProfilePicture.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // FireToast.makeToast(OTPactivity.this,object.getString("message"));
                                    otp_error.setText(object.getString("message"));
                                    if (progressDialog.isShowing()) {
                                        progressDialog.cancel();
                                    }
                                }
                            }*/

                            JSONObject jsonObject = new JSONObject(responseJson);

                            if (jsonObject.getBoolean("status")) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.cancel();
                                }
                                Utils.createToastLong(jsonObject.getString("message"), OtpPageActivity.this);
                                Intent intent = new Intent(OtpPageActivity.this, UploadProfilePicture.class);
                                startActivity(intent);
                                finish();
                            } else {
                                otp_error.setText(jsonObject.getString("message"));
                                if (progressDialog.isShowing()) {
                                    progressDialog.cancel();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {
                        if (progressDialog.isShowing()) {
                            progressDialog.cancel();
                        }
                        isTimerEnded = false;
                        countDownTimer.setMillisInFuture(180000); // start timer with 3 minutes timer
                        countDownTimer.start();
                        timer_textView.setTextColor(getResources().getColor(R.color.colorAccent));
                    }
                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.cancel();
                    }
                    String responseBody = Utils.convertTypedBodyToString(response.errorBody());
                    Log.i("TAG", "on Response :" + responseBody);
                    try {
                        JSONArray resObject = new JSONArray(responseBody);
                        for (int i = 0; i < resObject.length(); i++) {
                            // [{"field":"email","message":"This email has already been taken."},{"field":"phone","message":"This mobile is already exist please call us on +91 022-39653137 to recover your account."}]
                            JSONObject object = resObject.getJSONObject(i);
                            //  validateScreen(object.getString("field"),object.getString("message"));
                            otp_error.setText(object.getString("message"));

                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (progressDialog.isShowing()) {
                    progressDialog.cancel();
                }
            }
        });

     /*   [
        {
            "responseStatusCode": 1,
                "status": "success",
                "authkey": "yhduX5l49ZRuY4fUUrydPHiMd_AAAua9"
        }
        ]*/
    }
    //Type a Thought Security Code: 6913. Use this to finish verification


    public static String getTimeRemaining(long timeInMilliSeconds) {
        long seconds = timeInMilliSeconds / 1000;
        long minutes = seconds / 60;
        // long hours = minutes / 60;
        //  long days = hours / 24;
        // String time = days + ":" + hours % 24 + ":" + minutes % 60 + ":" + seconds % 60;
        //String time =  minutes % 60 + ":" + ((seconds % 60)<10?"0"+seconds % 60:seconds % 60);
        return minutes % 60 + ":" + ((seconds % 60) < 10 ? "0" + seconds % 60 : seconds % 60);
    }

    private CustomCountDownTimer countDownTimer = new CustomCountDownTimer(timerTimeValue, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timer_textView.setText(getTimeRemaining(millisUntilFinished));
            timerTimeOnPauseValue = millisUntilFinished;
        }

        @Override
        public void onFinish() {
            timer_textView.setText(getResources().getText(R.string.otp_resend_mssg));
            timer_textView.setTextColor(getResources().getColor(R.color.red));
            isTimerEnded = true;
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
