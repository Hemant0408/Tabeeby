package com.tabeeby.doctor.activities.signup;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.tabeeby.doctor.BuildConfig;
import com.tabeeby.doctor.R;
import com.tabeeby.doctor.adapter.DoctorTypeAdapter;
import com.tabeeby.doctor.adapter.HealthCareAdapter;
import com.tabeeby.doctor.utils.Utils;

import java.util.ArrayList;

public class RegistrationScreen1Activity extends AppCompatActivity {

    private Context mContext;
    static final int CUSTOM_DIALOG_ID = 0;
    ListView dialog_ListView;
    String flag;
    ImageView mDoctor, mOrganisation;
    String title;

    private DoctorTypeAdapter doctorTypeAdapter;
    private HealthCareAdapter healthCareAdapter;
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

    @Override
    protected Dialog onCreateDialog(int id) {

        Dialog dialog = null;

        switch (id) {
            case CUSTOM_DIALOG_ID:
                dialog = new Dialog(this, R.style.myCoolDialog);
                dialog.setContentView(R.layout.dialog_list_item);
                dialog.setTitle(title);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                dialog_ListView = (ListView) dialog.findViewById(R.id.dialoglist);
                ArrayAdapter<String> adapter = null;

                if (flag.equals("D")) {
                    if (adapter != null) {
                        adapter.clear();
                        dialog_ListView.removeAllViews();
                    }
                    //adapter = new ArrayAdapter<String>(this, R.layout.country_list_item, listContentDoctor);
                } else {
                    if (adapter != null) {
                        adapter.clear();
                        dialog_ListView.removeAllViews();
                    }
                    //adapter = new ArrayAdapter<String>(this, R.layout.country_list_item, listContentOrganisation);
                }

                dialog_ListView.setAdapter(adapter);
                dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        if (flag.equals("D")) {
                            mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_doctor_select));
                            mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_clinic_unselect));
                        } else {
                            mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_doctor_unselect));
                            mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_clinic_select));
                        }
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
        title = "Select Type";
        flag = "D";
        //listContentDoctor = new String[]{"Public Doctor", "Private Doctor"};
        // showDialog(CUSTOM_DIALOG_ID);

        showCustomDialogDoctor(title);
        mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_doctor_select));
        mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_clinic_unselect));
    }

    public void showOrganisationDialog(View view) {
        title = "Select Health Care Provider";
        flag = "O";
        //listContentOrganisation = new String[]{"Clinics and Hospital", "Fitness Center", "Pharmacies", "Medical Tourism", "Labs", "Home Healthcare"};
        // showDialog(CUSTOM_DIALOG_ID);
        showCustomDialogHealthCare(title);
        mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_doctor_unselect));
        mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_clinic_select));
    }

    private void showCustomDialogDoctor(String title) {
        Dialog dialog = new Dialog(this, R.style.myCoolDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_list_item);
        //dialog.setTitle(title);
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

        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, R.layout.country_list_item, doctorType);
        dialog_ListView.setAdapter(adapter);

        //doctorTypeAdapter = new DoctorTypeAdapter(RegistrationScreen1Activity.this, doctorType);

        //dialog_ListView.setAdapter(doctorTypeAdapter);

        dialog.show();

        dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                tv_doctor.setText(parent.getSelectedItem().toString());
                tv_health_care.setText("");

                if (flag.equals("D")) {
                    mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_doctor_select));
                    mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_clinic_unselect));
                } else {
                    mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_doctor_unselect));
                    mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_clinic_select));
                }
                dismissDialog(CUSTOM_DIALOG_ID);
            }
        });
    }

    private void showCustomDialogHealthCare(String title) {
        Dialog dialog = new Dialog(this, R.style.myCoolDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_list_item);
        //dialog.setTitle(title);
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

        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, R.layout.country_list_item, healthCare);
        dialog_ListView.setAdapter(adapter);

        dialog.show();

        dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                tv_health_care.setText(parent.getSelectedItem().toString());
                tv_doctor.setText("");
                if (flag.equals("D")) {
                    mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_doctor_select));
                    mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_clinic_unselect));

                } else {
                    mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_doctor_unselect));
                    mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_clinic_select));
                }
                dismissDialog(CUSTOM_DIALOG_ID);
            }
        });
    }
}
