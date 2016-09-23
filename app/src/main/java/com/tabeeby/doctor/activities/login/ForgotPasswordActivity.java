package com.tabeeby.doctor.activities.login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.application.MyApplication;
import com.tabeeby.doctor.utils.ConnectionDetector;
import com.tabeeby.doctor.utils.ServerUtils;
import com.tabeeby.doctor.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Z510 on 4/28/2016.
 */
public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.email_id_EditView)
    EditText email_id_EditView;
    @Bind(R.id.submit_button)
    Button submit_button;
    @Bind(R.id.forgot)
    TextInputLayout forgotTextInputLayout;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.forgot_password_layout);
        ButterKnife.bind(this);
        submit_button.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            progressDialog = new ProgressDialog(new ContextThemeWrapper(this, android.R.style.Theme_Holo_Light_Dialog));
        } else {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_button:
                if (validate()) {
                    progressDialog.show();
                    createHttpCall();
                }
                break;
        }
    }

    private void createHttpCall() {
        if (ConnectionDetector.checkInternetConnection(ForgotPasswordActivity.this)) {
            Call<ResponseBody> responseBodyCall = MyApplication.getInstance().getHttpService().postForgotPassword(email_id_EditView.getText().toString().trim());
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == ServerUtils.STATUS_OK) {
                        String responseString = Utils.convertTypedBodyToString(response.body());
                        try {
                            if (progressDialog.isShowing()) {
                                progressDialog.cancel();
                            }
                            /*
{
  "data": [],
  "status": true,
  "message": "Check your email address for instructions to reset your password",
  "code": 103
}
                        */
                            JSONObject object = new JSONObject(responseString);
                            if (object.getBoolean("status")) {

                                showAlert(true, object.getString("message"));

                            } else {
// {"error":{"email":["There is no user with such email."]},"status":false,"message":"Unable to reset your password. Check errors.","code":103}
                                JSONObject jsonObject = object.getJSONObject("error");
                                validateScreen("email", jsonObject.getString("email"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (progressDialog.isShowing()) {
                                progressDialog.cancel();
                            }
                        }
                    } else {
                        if (progressDialog.isShowing()) {
                            progressDialog.cancel();
                        }
                        String responseBody = Utils.convertTypedBodyToString(response.errorBody());
                        Log.i("TAG", "on Response :" + responseBody);
                        try {
                        } catch (Exception e) {
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
        } else {
            ConnectionDetector.showConnectionErrorMessage(ForgotPasswordActivity.this);
        }
    }

    private void validateScreen(String field, String message) {
        switch (field) {
            case "email":
                forgotTextInputLayout.setError(message);
                break;
            default:
                forgotTextInputLayout.setError(message);
                break;
        }
    }

    public boolean validate() {
        boolean ok = true;
        forgotTextInputLayout.setErrorEnabled(false);
        if (email_id_EditView.getText().toString().trim().length() == 0) {
            validateScreen("email", getResources().getString(R.string.error_enter_email_id));
            ok = false;
        } else if (!Utils.isValidEmail(email_id_EditView.getText().toString().trim())) {
            validateScreen("email", getResources().getString(R.string.error_enter_valid_email_id));
            ok = false;
        }

        return ok;
    }

    private void showAlert(final boolean finish, String message) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        //builder.setTitle("Dialog");
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (finish) {
                    finish();
                }
            }
        });
        builder.show();
    }


    @OnClick(R.id.backToLogin_textview)
    public void onBackTextView() {
        finish();
    }

}
