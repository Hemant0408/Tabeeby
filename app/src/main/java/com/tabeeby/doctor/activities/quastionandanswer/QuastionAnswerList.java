package com.tabeeby.doctor.activities.quastionandanswer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tabeeby.doctor.R;
import com.tabeeby.doctor.adapter.QuestionAnswerAdapter;
import com.tabeeby.doctor.application.MyApplication;
import com.tabeeby.doctor.httpclient.API;
import com.tabeeby.doctor.model.QuestionsModel;
import com.tabeeby.doctor.utils.ServerUtils;
import com.tabeeby.doctor.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuastionAnswerList extends AppCompatActivity {

    private ArrayList<QuestionsModel> mArraylistQuestionlist;
    QuestionAnswerAdapter questionAnswerAdapter;
    LinearLayoutManager linearLayoutManager;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    API api;
    private Context mContext;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quastion_answer_list);
        mContext=this;
        ButterKnife.bind(this);
        api = MyApplication.getInstance().getHttpService();

        setUpActionBar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.lv_question_and_answer_list);

        makeHTTPcall();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext,AddQuestionActivity.class));
            }
        });
    }

    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0.2f);
        getSupportActionBar().setTitle("Q and A List");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    @Override
    protected void onResume() {
        super.onResume();
        makeHTTPcall();
    }

    private void makeHTTPcall() {
        Call<ResponseBody> responseBodyCall = api.QuestionListApi();
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String responseBody = Utils.convertTypedBodyToString(response.body());
                Log.i("**ReponseBody", responseBody);
                Log.i("**ResponceCode", response.code() + "");
                if (response.code() == ServerUtils.STATUS_OK) {
                    try {

                        Gson gson = new Gson();
                        Type type = new TypeToken<List<QuestionsModel>>() {
                        }.getType();


                        JSONObject jsonObject = new JSONObject(responseBody);
                        String data = jsonObject.getString("data");
                        JSONObject DataJsonObject = new JSONObject(data);
                        JSONArray jsonArray = DataJsonObject.getJSONArray("questions");
                        mArraylistQuestionlist = new ArrayList<>();
                        mArraylistQuestionlist.clear();
                        recyclerView.removeAllViews();
                        mArraylistQuestionlist = gson.fromJson(jsonArray.toString(), type);

                        if (mArraylistQuestionlist.size() != 0) {
                            questionAnswerAdapter = new QuestionAnswerAdapter(mContext, mArraylistQuestionlist);
                            linearLayoutManager = new LinearLayoutManager(mContext);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setAdapter(questionAnswerAdapter);
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

}
