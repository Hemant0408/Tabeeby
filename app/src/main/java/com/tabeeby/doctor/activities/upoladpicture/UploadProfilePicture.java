package com.tabeeby.doctor.activities.upoladpicture;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.maintabactivity.MainActivity;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

public class UploadProfilePicture extends AppCompatActivity {
    private Context mContext;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile_picture);
        mContext = this;
    }

    public void nextStep(View view) {
        startActivity(new Intent(mContext, MainActivity.class));
        finish();
    }

    public void skip(View view) {
        startActivity(new Intent(mContext, MainActivity.class));
        finish();
    }

    public void Take_A_Photo(View view) {
        File photo = null;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            photo = new File(android.os.Environment
                    .getExternalStorageDirectory(), "t.jpg");
        } else {
            photo = new File(getCacheDir(), "t.jpg");
        }
        if (photo != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
            selectedImageUri = Uri.fromFile(photo);
            startActivityForResult(intent, 1);
        }
    }

    public void Choose_A_Photo(View view) {
       Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {

                if(selectedImageUri!=null) {
                    CropImage.activity(selectedImageUri)
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(this);
                }

            } else if (requestCode == 2) {

                Uri selected = data.getData();
                if(selected!=null) {
                    CropImage.activity(selected)
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(this);
                }
            }
            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                        Uri resultUri = result.getUri();
                        com.theartofdev.edmodo.cropper.CropImageView cropImageView=(com.theartofdev.edmodo.cropper.CropImageView)  findViewById(R.id.cropImageView);
                        cropImageView.setImageUriAsync(resultUri);

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
            }
        }
    }


}
