package com.tabeeby.doctor.activities.signup;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tabeeby.doctor.R;

import java.util.ArrayList;

public class RegistrationScreen1Activity extends AppCompatActivity {

    private Context mContext;
    ListView dialog_ListView;
    ImageView mDoctor, mOrganisation;
    String title;

    private ArrayList<String> doctorType = new ArrayList<>();
    private ArrayList<String> healthCare = new ArrayList<>();

    TextView tv_doctor, tv_health_care;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen1);
        mContext = this;

        mDoctor = (ImageView) findViewById(R.id.img_doctor_select);
        mOrganisation = (ImageView) findViewById(R.id.img_clinic_select);

        tv_doctor = (TextView) findViewById(R.id.tv_doctor);
        tv_health_care = (TextView) findViewById(R.id.tv_health_care);
    }

    public void nextStep(View view) {
        startActivity(new Intent(mContext, SignUpActivity.class));
    }

    public void showDoctorDialog(View view) {
        title = "Select Type";
        showCustomDialogDoctor(title);
        mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_doctor_select));
        mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_clinic_unselect));
    }

    public void showOrganisationDialog(View view) {
        title = "Select Health Care Provider";
        showCustomDialogHealthCare(title);
        mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_doctor_unselect));
        mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_clinic_select));
    }

    Dialog dialog = null;

    private void showCustomDialogDoctor(String title) {
        dialog = new Dialog(this, R.style.myCoolDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_list_item);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        dialog_ListView = (ListView) dialog.findViewById(R.id.dialoglist);

        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);

        tv_title.setText(title);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.95);
        int screenHeight = (int) (metrics.widthPixels * 0.95);

        Window window = dialog.getWindow();
        window.setLayout(screenWidth, LinearLayout.LayoutParams.WRAP_CONTENT);

        doctorType.clear();
        doctorType.add("Public Doctor");
        doctorType.add("Private Doctor");

        final ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, R.layout.country_list_item, doctorType);
        dialog_ListView.setAdapter(adapter);

        //doctorTypeAdapter = new DoctorTypeAdapter(RegistrationScreen1Activity.this, doctorType);

        //dialog_ListView.setAdapter(doctorTypeAdapter);

        dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                selectedValue = adapter.getItem(position);
                if (dialog != null)
                    dialog.dismiss();
                tv_doctor.setText(selectedValue);
                tv_health_care.setText("");
            }
        });

        dialog.show();
    }

    private void showCustomDialogHealthCare(String title) {
        dialog = new Dialog(this, R.style.myCoolDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_list_item);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        dialog_ListView = (ListView) dialog.findViewById(R.id.dialoglist);

        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);

        tv_title.setText(title);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.95);
        int screenHeight = (int) (metrics.widthPixels * 0.95);

        Window window = dialog.getWindow();
        window.setLayout(screenWidth, LinearLayout.LayoutParams.WRAP_CONTENT);

        healthCare.clear();
        healthCare.add("Clinics and Hospital");
        healthCare.add("Fitness Center");
        healthCare.add("Pharmacies");
        healthCare.add("Medical Tourism");
        healthCare.add("Labs");
        healthCare.add("Home Healthcare");

        //healthCareAdapter = new HealthCareAdapter(RegistrationScreen1Activity.this, healthCare);

        //dialog_ListView.setAdapter(healthCareAdapter);

        final ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, R.layout.country_list_item, healthCare);
        dialog_ListView.setAdapter(adapter);

        dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                selectedValue = adapter.getItem(position);
                if (dialog != null)
                    dialog.dismiss();
                tv_health_care.setText(selectedValue);
                tv_doctor.setText("");
            }
        });

        dialog.show();
    }

    String selectedValue = "";
}
