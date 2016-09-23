package com.tabeeby.doctor.activities.quastionandanswer;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tabeeby.doctor.R;
import com.tabeeby.doctor.adapter.AnswerListAdapter;
import com.tabeeby.doctor.adapter.EventAdapter;
import com.tabeeby.doctor.adapter.QuestionAnswerAdapter;
import com.tabeeby.doctor.application.MyApplication;
import com.tabeeby.doctor.httpclient.API;
import com.tabeeby.doctor.model.AnswerModel;
import com.tabeeby.doctor.model.QuestionsModel;
import com.tabeeby.doctor.utils.ServerUtils;
import com.tabeeby.doctor.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewQuestionAndAnswer extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.etd_type_answer)
    protected EditText mEditTextAnswer;

    private Context mContext;
    private API api;
    private ArrayList<String> arrayList;
    private AnswerListAdapter findDoctorAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private String mUserid,mQuestionId;
    private ArrayList<AnswerModel> mArraylistQuestionlist;
    private ArrayList<AnswerModel> mArrayListAnswerList;
    private AnswerModel mAnswermodel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_question_and_answer);
        mContext=this;
        ButterKnife.bind(this);
        setUpActionBar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {finish();}
        });
        api = MyApplication.getInstance().getHttpService();
        mArrayListAnswerList=new ArrayList<>();
        mAnswermodel=new AnswerModel();

        recyclerView = (RecyclerView) findViewById(R.id.lv_answer_list);

        if(getIntent().getExtras()!=null) {

            if (getIntent().getExtras().getString("category").trim() != null) {
                mAnswermodel.setQuestion_category(getIntent().getExtras().getString("category"));
            }

            if (getIntent().getExtras().getString("qua_title").trim() != null) {
                mAnswermodel.setQuestion_title(getIntent().getExtras().getString("qua_title").trim());
            }

            if (getIntent().getExtras().getString("qua_desc").trim() != null) {
                mAnswermodel.setQuestion_text(getIntent().getExtras().getString("qua_desc").trim());
            }

            if(getIntent().getExtras().getString("view_count").trim()!=null) {
                mAnswermodel.setQuestion_view_count( getIntent().getExtras().getString("view_count").trim());
            }

            if(getIntent().getExtras().getString("answer_count").trim()!=null) {
                mAnswermodel.setQuastion_answer_count(getIntent().getExtras().getString("answer_count").trim());
            }

            if(getIntent().getExtras().getString("ask_by").trim()!=null) {
                mAnswermodel.setQuastion_ask_by(getIntent().getExtras().getString("ask_by").trim());
            }

            if(getIntent().getExtras().getString("user_id").trim()!=null) {
                mUserid = getIntent().getExtras().getString("user_id").trim();
                mAnswermodel.setUser_id(getIntent().getExtras().getString("user_id").trim());
            }

            if(getIntent().getExtras().getString("question_id").trim()!=null) {
                mQuestionId = getIntent().getExtras().getString("question_id");
                mAnswermodel.setQuestion_id(getIntent().getExtras().getString("question_id"));
            }

        }

        mArrayListAnswerList.add(mAnswermodel);

        getAnswers();

        ViewCount();
    }


    public void getAnswers()
    {
        if(mQuestionId!=null)
        {
            Call<ResponseBody> responseBodyCall = api.AnswerListApi(mQuestionId);
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String responseBody = Utils.convertTypedBodyToString(response.body());
                    Log.i("**ReponseBody",responseBody);
                    Log.i("**ResponceCode",response.code()+"");
                    if (response.code() == ServerUtils.STATUS_OK) {
                        try {

                            Gson gson = new Gson();
                            Type type = new TypeToken<List<AnswerModel>>() {
                            }.getType();

                            JSONObject jsonObject=new JSONObject(responseBody);
                            String data=jsonObject.getString("data");
                            JSONObject DataJsonObject=new JSONObject(data);
                            JSONArray jsonArray = DataJsonObject.getJSONArray("answers");
                            mArraylistQuestionlist = new ArrayList<>();
                            mArraylistQuestionlist = gson.fromJson(jsonArray.toString(), type);

                            if(mArraylistQuestionlist!=null)
                            {
                                for(int i=0;i<mArraylistQuestionlist.size();i++)
                                {
                                    mArrayListAnswerList.add(mArraylistQuestionlist.get(i));
                                }
                            }

                            if(mArrayListAnswerList.size()!=0)
                            {
                                findDoctorAdapter = new AnswerListAdapter(mContext,mArrayListAnswerList);
                                linearLayoutManager = new LinearLayoutManager(mContext);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(findDoctorAdapter);
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

    public void putAnswer(View v) {
        if (mEditTextAnswer.getText().toString().trim() != null) {
            String header = "Bearer " + Utils.retrieveSharedPreference(mContext, getString(R.string.pref_access_token));
            Call<ResponseBody> responseBodyCall = api.addAnswerApi(header, mQuestionId, mUserid, "1", mEditTextAnswer.getText().toString().trim());
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String responseBody = Utils.convertTypedBodyToString(response.body());
                    if (response.code() == ServerUtils.STATUS_OK) {
                        try {
                            mEditTextAnswer.setText("");
                            recyclerView.removeAllViews();
                            mArrayListAnswerList.clear();
                            mArraylistQuestionlist.clear();
                            mArrayListAnswerList.add(mAnswermodel);
                            getAnswers();
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
    }

    public void ViewCount()
    {
        String header = "Bearer " + Utils.retrieveSharedPreference(mContext, getString(R.string.pref_access_token));
        Call<ResponseBody> responseBodyCall = api.addViewCountApi(header, mQuestionId, mUserid);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String responseBody = Utils.convertTypedBodyToString(response.body());
                if (response.code() == ServerUtils.STATUS_OK) {
                    try {

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
}
