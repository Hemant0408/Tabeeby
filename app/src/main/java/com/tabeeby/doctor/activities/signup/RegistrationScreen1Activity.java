package com.tabeeby.doctor.activities.signup;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.tabeeby.doctor.utils.Utils;

import java.util.ArrayList;

public class RegistrationScreen1Activity extends AppCompatActivity {

    ListView dialog_ListView;
    ImageView mDoctor, mOrganisation;
    String title;
    TextView tv_doctor, tv_health_care;
    Dialog dialog = null;
    String selectedValue = "";
    private Context mContext;
    private ArrayList<String> doctorType = new ArrayList<>();
    private ArrayList<String> healthCare = new ArrayList<>();

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
        title = getString(R.string.select_type);
        showCustomDialogDoctor(title);
        mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_dr_type_pink_48dp));
        mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_health_pro_grey_48dp));
    }

    public void showOrganisationDialog(View view) {
        title = getString(R.string.select_health_care_provider);
        showCustomDialogHealthCare(title);
        mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_dr_type_grey_48dp));
        mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_health_pro_pink_48dp));
    }

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
        doctorType.add(getString(R.string.consultant));
        doctorType.add(getString(R.string.specialist));
        doctorType.add(getString(R.string.registrar));
        doctorType.add(getString(R.string.general_practitioner));
        doctorType.add(getString(R.string.other));

        final ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, R.layout.country_list_item, doctorType);
        dialog_ListView.setAdapter(adapter);


        dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                selectedValue = adapter.getItem(position);
                if (dialog != null)
                    dialog.dismiss();
                tv_doctor.setText(selectedValue);
                tv_health_care.setText(getString(R.string.health_care_provider));
                Utils.storeSharedPreference(mContext,"doctor_type","health care provider");
                Utils.storeSharedPreference(mContext,"doctor_sub_type",selectedValue);
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
        healthCare.add(getString(R.string.clinics_and_hospital));
        healthCare.add(getString(R.string.fitness_center));
        healthCare.add(getString(R.string.pharmacies));
        healthCare.add(getString(R.string.medical_tourism));
        healthCare.add(getString(R.string.labs));
        healthCare.add(getString(R.string.home_healthcare));


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
                tv_doctor.setText("Doctor");
                Utils.storeSharedPreference(mContext,"doctor_type","doctor");
                Utils.storeSharedPreference(mContext,"doctor_sub_type",selectedValue);
            }
        });

        dialog.show();
    }
}
