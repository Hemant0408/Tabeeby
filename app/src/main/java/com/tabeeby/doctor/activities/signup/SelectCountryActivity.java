package com.tabeeby.doctor.activities.signup;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.tabeeby.doctor.GsonModels.CountryModel;
import com.tabeeby.doctor.Models.Country;
import com.tabeeby.doctor.R;
import com.tabeeby.doctor.adapter.CountryAdapter;
import com.tabeeby.doctor.application.MyApplication;
import com.tabeeby.doctor.database.DbClass;
import com.tabeeby.doctor.utils.ServerUtils;
import com.tabeeby.doctor.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectCountryActivity extends AppCompatActivity {

    static final int CUSTOM_DIALOG_ID = 0;
    ListView dialog_ListView;
    private Context mContext;

    @Bind(R.id.tv_selected_country)
    protected TextView tv_selected_country;

    @Bind(R.id.btn_next_step)
    protected Button btn_next_step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);
        ButterKnife.bind(this);
        mContext = this;


        btn_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tv_selected_country.getText().toString().trim().equals("")) {
                    if (com.tabeeby.doctor.BuildConfig.VERSION) {
                        startActivity(new Intent(mContext, SelectDoctorProviderActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(mContext, SignUpActivity.class));
                        finish();
                    }
                } else {
                    Utils.createToastShort(getString(R.string.select_country_error_msg), mContext);
                }
            }
        });

        makeHTTPCallForCountry();
    }

    ArrayList<Country> Country_list;

    private void makeHTTPCallForCountry() {

        Utils.ShowProgressDialog(SelectCountryActivity.this);
        Call<CountryModel> catogoriesCall = MyApplication.getInstance().getHttpService().getCountryDetails();
        catogoriesCall.enqueue(new Callback<CountryModel>() {
            @Override
            public void onResponse(Call<CountryModel> call, Response<CountryModel> response) {

                if (response.code() == ServerUtils.STATUS_OK) {
                    //DbClass db = new DbClass(SelectCountryActivity.this);

                    //db.open();
                    Country_list = getDataReady(response.body().getData().getCountry_list());

                    Utils.DismissProgressDialog();

                    /*for (int i = 0; i < list.size(); i++) {
                        db.insertCountryData(list.get(i).getId(), list.get(i).getName(), "");
                    }*/

                    Log.e("Country Array Size: ", "" + Country_list.size());
                    //Log.e("Country List Size: ", "" + db.getCountryListEnglish().size());

                    //db.close();
                }
            }

            @Override
            public void onFailure(Call<CountryModel> call, Throwable t) {
                Log.i("TAG", "on fail :" + t.getMessage());

                Utils.DismissProgressDialog();
            }
        });
    }

    private ArrayList<Country> getDataReady(List<CountryModel.DataBean.CountryListBean> data) {
        ArrayList<Country> list = new ArrayList<>();

        list.clear();
        if (data != null) {
            for (CountryModel.DataBean.CountryListBean bean : data) {
                Country listModel = new Country();
                listModel.setId(bean.getId());
                listModel.setName(bean.getTitle());
                list.add(listModel);
            }
        }
        return list;
    }

    public void nextStep(View view) {
        showDialog(CUSTOM_DIALOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        Dialog dialog = null;

        switch (id) {
            case CUSTOM_DIALOG_ID:
                dialog = new Dialog(this, R.style.myCoolDialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_list_item);
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);

                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int screenWidth = (int) (metrics.widthPixels * 0.95);
                int screenHeight = (int) (metrics.widthPixels * 0.95);

                Window window = dialog.getWindow();
                window.setLayout(screenWidth, screenHeight);

                //Prepare ListView in dialog
                dialog_ListView = (ListView) dialog.findViewById(R.id.dialoglist);
                /*final ArrayAdapter<String> adapter
                        = new ArrayAdapter<String>(this, R.layout.country_list_item, Utils.getCountryList(mContext));*/
                final CountryAdapter adapter = new CountryAdapter(SelectCountryActivity.this, Country_list);
                dialog_ListView.setAdapter(adapter);
                dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        tv_selected_country.setText(Country_list.get(position).getName());
                        Utils.storeSharedPreference(mContext, "Country", Country_list.get(position).getId()/*tv_selected_country.getText().toString().trim()*/);
                        dismissDialog(CUSTOM_DIALOG_ID);
                    }
                });

                break;
        }

        return dialog;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog, Bundle bundle) {
        super.onPrepareDialog(id, dialog, bundle);

        switch (id) {
            case CUSTOM_DIALOG_ID:
                break;
        }
    }

    public void showDoctorDialog(View view) {
        showDialog(CUSTOM_DIALOG_ID);
    }

    public void showOrganisationDialog(View view) {
        showDialog(CUSTOM_DIALOG_ID);
    }
}
