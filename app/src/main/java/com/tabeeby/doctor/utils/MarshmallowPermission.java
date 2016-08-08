package com.tabeeby.doctor.utils;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

/**
 * Created by Z510 on 4/26/2016.
 */
public class MarshmallowPermission {


    /*   public static final int RECORD_PERMISSION_REQUEST_CODE = 1;*/
    public static final int EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 2;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 3;
    public static final int SMS_PERMISSION_REQUEST_CODE = 4;
    public static final int GET_ACCOUNTS_PERMISSION_REQUEST_CODE = 5;
    public static final int GET_WAKE_PERMISSION_REQUEST_CODE = 6;

    Activity activity;

    public MarshmallowPermission(Activity activity) {
        this.activity = activity;
    }

   /* public boolean checkPermissionForRecord(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }*/

 /*   public boolean checkPermissionForExternalStorage(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }*/

    public boolean checkPermissionForCamera() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForSMS() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForWakeLock() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WAKE_LOCK);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForAccounts() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.GET_ACCOUNTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    public void requestPermissionForWakeLock() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WAKE_LOCK)) {
            //FireToast.makeToast(activity,"Accounts permission needed. Please allow in App Settings for additional functionality.");
            //  ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.WAKE_LOCK},GET_WAKE_PERMISSION_REQUEST_CODE);

        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WAKE_LOCK}, GET_WAKE_PERMISSION_REQUEST_CODE);
        }
    }


    public void requestPermissionForAccounts() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.GET_ACCOUNTS)) {
            // ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.GET_ACCOUNTS},GET_ACCOUNTS_PERMISSION_REQUEST_CODE);
          /*  showMessageOKCancel("SMS permission needed for OTP", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(activity,new String[] {Manifest.permission.GET_ACCOUNTS},
                            GET_ACCOUNTS_PERMISSION_REQUEST_CODE);
                }
            });*/

        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.GET_ACCOUNTS}, GET_ACCOUNTS_PERMISSION_REQUEST_CODE);
        }
    }

    public void requestPermissionForExternalStorage() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
        }
    }

    public void requestPermissionForCamera() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
            //   FireToast.makeToast(activity,"Camera permission needed. Please allow in App Settings for additional functionality.");

        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    public void requestPermissionForSms() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_SMS)) {
            //  FireToast.makeToast(activity,"SMS permission needed. Please allow in App Settings for additional functionality.");
            //    ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.WAKE_LOCK},SMS_PERMISSION_REQUEST_CODE);
           /* showMessageOKCancel("SMS permission needed for OTP", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(activity,new String[] {Manifest.permission.READ_SMS},
                            SMS_PERMISSION_REQUEST_CODE);
                }
            });*/
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_SMS}, SMS_PERMISSION_REQUEST_CODE);
        }

    }


    public void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


}
