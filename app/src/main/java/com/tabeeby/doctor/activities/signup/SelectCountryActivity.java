package com.tabeeby.doctor.activities.signup;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.utiles.Utils;

public class SelectCountryActivity extends AppCompatActivity {
    private Context mContext;
    static final int CUSTOM_DIALOG_ID = 0;
    ListView dialog_ListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);
        mContext=this;
    }
    public void nextStep(View view) {
        showDialog(CUSTOM_DIALOG_ID);
    }


        @Override
        protected Dialog onCreateDialog(int id) {

            Dialog dialog = null;

            switch(id) {
                case CUSTOM_DIALOG_ID:
                    dialog = new Dialog(this, R.style.myCoolDialog);
                    dialog.setContentView(R.layout.dialog_list_item);
                    dialog.setTitle("Select Country");
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

//Prepare ListView in dialog
                    dialog_ListView = (ListView)dialog.findViewById(R.id.dialoglist);
                    ArrayAdapter<String> adapter
                            = new ArrayAdapter<String>(this,R.layout.country_list_item, Utils.country_list);
                    dialog_ListView.setAdapter(adapter);
                    dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            dismissDialog(CUSTOM_DIALOG_ID);
                            startActivity(new Intent(mContext, RegistrationScreen1Activity.class));
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
        showDialog(CUSTOM_DIALOG_ID);
    }

    public void showOrganisationDialog(View view) {
        showDialog(CUSTOM_DIALOG_ID);
    }


}
