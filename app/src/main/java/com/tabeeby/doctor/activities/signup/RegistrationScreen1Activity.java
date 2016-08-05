package com.tabeeby.doctor.activities.signup;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.tabeeby.doctor.R;

public class RegistrationScreen1Activity extends AppCompatActivity {

    private Context mContext;
    static final int CUSTOM_DIALOG_ID = 0;
    ListView dialog_ListView;
    String[] listContentDoctor;
    String[] listContentOrganisation;
    String flag;
    ImageView mDoctor,mOrganisation;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen1);
        mContext=this;
        mDoctor=(ImageView) findViewById(R.id.img_doctor_select);
        mOrganisation=(ImageView) findViewById(R.id.img_clinic_select);
    }

    public void nextStep(View view) {
        startActivity(new Intent(mContext,SignUpActivity.class));
    }



    @Override
    protected Dialog onCreateDialog(int id) {

        Dialog dialog = null;

        switch(id) {
            case CUSTOM_DIALOG_ID:
                dialog = new Dialog(this, R.style.myCoolDialog);
                dialog.setContentView(R.layout.dialog_list_item);
                dialog.setTitle(title);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                dialog.setOnCancelListener(new DialogInterface.OnCancelListener(){

                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }});

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener(){

                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }});

                dialog_ListView = (ListView)dialog.findViewById(R.id.dialoglist);
                ArrayAdapter<String> adapter=null;
                if(flag.equals("D")) {
                    if(adapter!=null) {
                        adapter.clear();
                        dialog_ListView.removeAllViews();
                    }
                    adapter = new ArrayAdapter<String>(this, R.layout.country_list_item, listContentDoctor);
                }
                else
                {
                    if(adapter!=null) {
                        adapter.clear();
                        dialog_ListView.removeAllViews();
                    }
                    adapter = new ArrayAdapter<String>(this, R.layout.country_list_item, listContentOrganisation);
                }
                dialog_ListView.setAdapter(adapter);
                dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                       if (flag.equals("D"))
                       {
                           mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_doctor_select));
                           mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_clinic_unselect));
                       }
                        else {
                           mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_doctor_unselect));
                           mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_clinic_select));
                       }
                        dismissDialog(CUSTOM_DIALOG_ID);
                    }});

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
        title="Select Type";
        flag="D";
        listContentDoctor=new String[]{"Public Doctor","Private Doctor"};
       // showDialog(CUSTOM_DIALOG_ID);

        mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_doctor_select));
        mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_clinic_unselect));

    }

    public void showOrganisationDialog(View view) {
        title="Select Health Care Provider";
        flag="O";
        listContentOrganisation=new String[]{"Clinics and Hospital","Fitness Center","Pharmacies","Medical Tourism","Labs","Home Healthcare"};
       // showDialog(CUSTOM_DIALOG_ID);

        mDoctor.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_doctor_unselect));
        mOrganisation.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_clinic_select));

    }
}
