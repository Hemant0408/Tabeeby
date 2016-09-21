package com.tabeeby.doctor.activities.upoladpicture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.maintabactivity.MainActivity;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class UploadProfilePicture extends AppCompatActivity {

    private Context mContext;
    CircleImageView cropImageViewGallery, cropImageViewCamera;
    boolean photo_flag_camera = false, photo_flag_gallery = false;
    ImageView img_camera_photo, img_gallery_photo;
    TextView tv_camera, tv_gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile_picture);
        mContext = this;

        cropImageViewGallery = (CircleImageView) findViewById(R.id.cropImageViewGallery);
        cropImageViewCamera = (CircleImageView) findViewById(R.id.cropImageViewCamera);

        img_gallery_photo = (ImageView) findViewById(R.id.img_gallery_photo);
        img_camera_photo = (ImageView) findViewById(R.id.img_camera_photo);

        tv_camera = (TextView) findViewById(R.id.tv_camera);
        tv_gallery = (TextView) findViewById(R.id.tv_gallery);
    }

    public void nextStep(View view) {
        startActivity(new Intent(mContext, MainActivity.class));
        finish();
    }

    public void skip(View view) {
        startActivity(new Intent(mContext, MainActivity.class));
        finish();
    }

    public void captureFromCamera(View view) {
        Log.e("Camera", "Camera");
        photo_flag_camera = true;
        photo_flag_gallery = false;
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(photoFileName));
        startActivityForResult(intent, 0);
    }

    public void captureFromGallery(View view) {
        try {
            Log.e("Gallery", "Gallery");
            photo_flag_gallery = true;
            photo_flag_camera = false;
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(e.getClass().getName(), e.getMessage(), e);
        }
    }

    public String photoFileName = "photo.jpg";

    // Returns the Uri for a photo stored on disk given the fileName
    public Uri getPhotoFileUri(String fileName) {

        // Get safe storage directory for photos
        File mediaStorageDir = new File(
                Environment.getExternalStorageDirectory() + "/Tabeeby/Camera/");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d(APP_TAG, "failed to create directory");
        }

        try {
            if (mediaStorageDir.exists()) {
                File noMedia = new File(mediaStorageDir.getAbsolutePath() + "/.nomedia");
                noMedia.createNewFile();
            } else {
            }
        } catch (Exception e) {
        }

        // Return the file target for the photo based on filename
        return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
    }

    public final String APP_TAG = "MyCustomApp";

    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    /**
     * Image result from gallery * @param resultCode * @param data
     */
    private void imageFromGallery(int resultCode, Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String path = cursor.getString(columnIndex);
        cursor.close();
        Intent i = new Intent(this, CropActivity.class);
        i.putExtra("path", path);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1: {
                    this.imageFromGallery(resultCode, data);
                }
                break;
                case 0: {
                    try {
                        Uri takenPhotoUri = getPhotoFileUri(photoFileName);
                        System.out.println(takenPhotoUri.getPath());
                        Log.e("Path: ", takenPhotoUri.getPath());
                        Intent i = new Intent(this, CropActivity.class);
                        i.putExtra("path", takenPhotoUri.getPath());
                        startActivity(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                }
                break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (CropActivity.isFlag()) {
            if (CropActivity.getBitmap() != null) {
                if (photo_flag_camera) {
                    img_camera_photo.setVisibility(View.GONE);
                    tv_camera.setVisibility(View.GONE);
                    cropImageViewCamera.setVisibility(View.VISIBLE);
                    cropImageViewCamera.setImageBitmap(CropActivity.getBitmap());

                    img_gallery_photo.setVisibility(View.VISIBLE);
                    tv_gallery.setVisibility(View.VISIBLE);
                    cropImageViewGallery.setVisibility(View.GONE);
                } else {

                    img_gallery_photo.setVisibility(View.GONE);
                    tv_gallery.setVisibility(View.GONE);
                    cropImageViewGallery.setVisibility(View.VISIBLE);
                    cropImageViewGallery.setImageBitmap(CropActivity.getBitmap());

                    img_camera_photo.setVisibility(View.VISIBLE);
                    tv_camera.setVisibility(View.VISIBLE);
                    cropImageViewCamera.setVisibility(View.GONE);
                }
                //filePath = CropActivity.getFilePath();
            }
            CropActivity.setFlag(false);
        }
    }
}
