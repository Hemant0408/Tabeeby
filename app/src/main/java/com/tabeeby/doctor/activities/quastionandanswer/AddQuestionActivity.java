package com.tabeeby.doctor.activities.quastionandanswer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.signup.OtpPageActivity;
import com.tabeeby.doctor.application.MyApplication;
import com.tabeeby.doctor.httpclient.API;
import com.tabeeby.doctor.utils.ConnectionDetector;
import com.tabeeby.doctor.utils.ServerUtils;
import com.tabeeby.doctor.utils.Utils;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQuestionActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
     protected Toolbar toolbar;

    @Bind(R.id.edtCategory)
    protected EditText mEditTextCategory;

    @Bind(R.id.edtTitle)
    protected EditText mEditTextTitle;

    @Bind(R.id.edtQuestionDescription)
    protected EditText mEditTextQuestionDesciption;

    @Bind(R.id.textInputCategory)
    protected TextInputLayout mTextInputCategory;

    @Bind(R.id.textInputTitle)
    protected TextInputLayout mTextInputTitle;

    @Bind(R.id.textInputQuestionDescription)
    protected TextInputLayout mTextInputLayputQuestionDesc;

    @Bind(R.id.btnSubmit)
    protected Button mButtonSubmit;

    private API api;

    private Context mContext;

    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        bundle=savedInstanceState;
        mContext=this;
        ButterKnife.bind(this);
        setUpActionBar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        api = MyApplication.getInstance().getHttpService();

        mEditTextCategory.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mTextInputCategory.setError(null);
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mEditTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mTextInputTitle.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mEditTextQuestionDesciption.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mTextInputLayputQuestionDesc.setError(null);
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ConnectionDetector.checkInternetConnection(mContext)) {
                    addQuestion();
                }else
                {
                    Utils.ErrorMessage((Activity) mContext,bundle,getString(R.string.no_internetconnection));
                }
            }
        });

    }

    private void addQuestion()
    {
        if(validate())
        {
            Utils.ShowProgressDialog(mContext);
            String header="Bearer "+Utils.retrieveSharedPreference(mContext,getString(R.string.pref_access_token));
            Call<ResponseBody> responseBodyCall = api.addQuestionApi(header,mEditTextTitle.getText().toString().trim(),mEditTextCategory.getText().toString().trim(),mEditTextQuestionDesciption.getText().toString().trim(),"1",Utils.retrieveSharedPreference(mContext,getString(R.string.pref_user_id)).trim());
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == ServerUtils.STATUS_OK) {
                        try {
                            Utils.DismissProgressDialog();
                            if(response!=null) {
                                String responseBody = Utils.convertTypedBodyToString(response.body());
                                JSONObject jsonObject = new JSONObject(responseBody);
                                finish();
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

    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0.2f);
        getSupportActionBar().setTitle(R.string.add_question);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    public boolean validate()
    {
        if(mEditTextCategory.getText().toString().trim().equals(""))
        {
            mTextInputCategory.setError("Enter Category");
            return false;
        }

        if(mEditTextTitle.getText().toString().trim().equals(""))
        {
            mTextInputTitle.setError("Enter Title");
            return  false;
        }
        if(mEditTextQuestionDesciption.getText().toString().trim().equals(""))
        {
            mTextInputLayputQuestionDesc.setError("Enter Question Description");
            return  false;
        }
        return true;
    }
}
