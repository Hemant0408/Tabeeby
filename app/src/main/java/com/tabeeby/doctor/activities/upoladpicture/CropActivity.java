package com.tabeeby.doctor.activities.upoladpicture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.isseiaoki.simplecropview.CropImageView;
import com.tabeeby.doctor.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by toshiba on 9/30/2015.
 */
public class CropActivity extends AppCompatActivity implements View.OnClickListener {

    // Bitmap
    public static Bitmap bitmap;
    public static String filePath;
    // CropImageView
    CropImageView cropImageView;
    // Button
    private Button crop_button;
    //Bundle
    private Bundle bundle;

    public static Bitmap getBitmap() {
        return bitmap;
    }

    public static void setBitmap(Bitmap bitmap) {
        CropActivity.bitmap = bitmap;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        filePath = filePath;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop);

        findById();

        bundle = getIntent().getExtras();

        try {
            String path = bundle.getString("path");
            Bitmap myBitmap = BitmapFactory.decodeFile(path);
            cropImageView.setImageBitmap(myBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //cropImageView.setOverlayColor(0xAA1C1C1C);
        cropImageView.setCropEnabled(true);
        crop_button.setOnClickListener(this);

        crop_button.setTransformationMethod(null);
    }

    private void findById() {

        // Button
        crop_button = (Button) findViewById(R.id.crop_button);

        // CropImageView
        cropImageView = (CropImageView) findViewById(R.id.cropImageView);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.crop_button:
                setBitmap(cropImageView.getCroppedBitmap());
                storeImage(cropImageView.getCroppedBitmap(), "CROP_");
                setFlag(true);
                CropActivity.this.finish();
                break;
        }
    }

    private static boolean flag = false;

    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        CropActivity.flag = flag;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        clearData();
        finish();
    }

    private void clearData() {
        //get path to external storage (SD card)
        String iconsStoragePath = Environment.getExternalStorageDirectory() + "/Tabeeby/Images/";
        File sdIconStorageDir = new File(iconsStoragePath);

        //create storage directories, if they don't exist
        sdIconStorageDir.mkdirs();

        try {
            File files[] = sdIconStorageDir.listFiles();
            if (files != null) {
                for (File f : files)
                    f.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean storeImage(Bitmap imageData, String filename) {
        //get path to external storage (SD card)
        String iconsStoragePath = Environment.getExternalStorageDirectory() + "/Tabeeby/Images/";
        File sdIconStorageDir = new File(iconsStoragePath);

        //create storage directories, if they don't exist
        sdIconStorageDir.mkdirs();

        try {
            File files[] = sdIconStorageDir.listFiles();
            if (files != null) {
                for (File f : files)
                    f.delete();
            }

            try {
                if (sdIconStorageDir.exists()) {
                    File noMedia = new File(sdIconStorageDir.getAbsolutePath() + "/.nomedia");
                    noMedia.createNewFile();
                } else {
                }
            } catch (Exception e) {
            }

            filePath = sdIconStorageDir.toString() + "/" + (filename + System.currentTimeMillis()) + ".jpg";
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);

            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

            //choose another format if PNG doesn't suit you
            imageData.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();

            setFilePath(filePath);
            Log.e("FilePath", filePath);
        } catch (FileNotFoundException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            return false;
        } catch (IOException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            return false;
        }

        return true;
    }
}
