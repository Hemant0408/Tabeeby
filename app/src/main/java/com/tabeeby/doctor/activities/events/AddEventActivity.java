package com.tabeeby.doctor.activities.events;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tabeeby.doctor.R;

import com.tabeeby.doctor.application.MyApplication;
import com.tabeeby.doctor.httpclient.API;
import com.tabeeby.doctor.imageutils.GetImageThumbnail;
import com.tabeeby.doctor.utils.ServerUtils;
import com.tabeeby.doctor.utils.Utils;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEventActivity extends AppCompatActivity {

    private static String root = null;
    private static String imageFolderPath = null;
    private String imageName = null;
    private static Uri fileUri = null;
    private static final int CAMERA_IMAGE_REQUEST=1;
    final int PIC_CROP = 2;
    Uri selectedImage;
    File image;
    String ba1,picturePath;



    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    private Context mContext;
    private Bundle bundle;
    private API api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        ButterKnife.bind(this);
        setUpActionBar();
        mContext=this;
        bundle=savedInstanceState;
        api = MyApplication.getInstance().getHttpService();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {finish();}
        });





    }




    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0.2f);
        getSupportActionBar().setTitle(R.string.add_event);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }


    public void SelectImage(View view) {
        selectImage(view);
    }


    private void selectImage(View view) {


        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        final String path = (String) view.getTag();

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {


                if (options[item].equals("Take Photo"))

                {

                    root = Environment.getExternalStorageDirectory().toString()
                            + "/Your_Folder";

                    // Creating folders for Image
                    imageFolderPath = root + "/saved_images";
                    File imagesFolder = new File(imageFolderPath);
                    imagesFolder.mkdirs();

                    // Generating file name
                    imageName = "test.png";

                    // Creating image here

                    image = new File(imageFolderPath, imageName);

                    fileUri = Uri.fromFile(image);


                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                    startActivityForResult(takePictureIntent,
                            CAMERA_IMAGE_REQUEST);


                }
                else if (options[item].equals("Choose from Gallery"))
                {

                    Intent intent = new   Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(intent, 2);

                }

                else if (options[item].equals("Cancel")) {

                    dialog.dismiss();

                }
            }

        });

        builder.show();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {

                super.onActivityResult(requestCode, resultCode, data);

                if (resultCode == RESULT_OK) {

                    switch (requestCode) {
                        case CAMERA_IMAGE_REQUEST:

                            Bitmap bitmap = null;
                            try {
                                GetImageThumbnail getImageThumbnail = new GetImageThumbnail();
                                bitmap = getImageThumbnail.getThumbnail(fileUri, this);
                                ExifInterface exif = new ExifInterface(image.getAbsolutePath());     //Since API Level 5
                                String orientation = exif.getAttribute(ExifInterface.TAG_ORIENTATION);

                                Matrix matrix = new Matrix();
                                if (orientation.equals("6")) {
                                    matrix.postRotate(90);
                                } else if (orientation.equals("3")) {
                                    matrix.postRotate(180);
                                } else if (orientation.equals("8")) {
                                    matrix.postRotate(270);
                                }
                                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

                            } catch (FileNotFoundException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }


                            ByteArrayOutputStream bao = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 10, bao);
                            byte[] ba = bao.toByteArray();
                            ba1 = com.tabeeby.doctor.imageutils.Base64.encodeBytes(ba);

                            Log.i("BiteCode",ba1);
                            uploadToServer();

                            break;

                        default:
                            Toast.makeText(this, "Something went wrong...",
                                    Toast.LENGTH_SHORT).show();
                            break;
                    }

                }

            } else if (requestCode == 2) {


                selectedImage = data.getData();

                Bitmap bitmap = null;

                try {
                    GetImageThumbnail getImageThumbnail = new GetImageThumbnail();
                    bitmap = getImageThumbnail.getThumbnail(selectedImage, this);

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();


                    ExifInterface exif = new ExifInterface(filePath);     //Since API Level 5
                    String orientation = exif.getAttribute(ExifInterface.TAG_ORIENTATION);

                    Matrix matrix = new Matrix();
                    if (orientation.equals("6")) {
                        matrix.postRotate(90);
                    } else if (orientation.equals("3")) {
                        matrix.postRotate(180);
                    } else if (orientation.equals("8")) {
                        matrix.postRotate(270);
                    }
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                //img.setTag("yes");
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 10, bao);
                byte[] ba = bao.toByteArray();
                ba1 = com.tabeeby.doctor.imageutils.Base64.encodeBytes(ba);

                Log.i("BiteCode",ba1);

                uploadToServer();

            }

        }
    }


    public void  uploadToServer() {

        Call<ResponseBody> responseBodyCall = api.imageUpload(ba1,"newImage.jpg");
                responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == ServerUtils.STATUS_OK) {
                    try {
                        Utils.DismissProgressDialog();
                        if(response!=null) {
                            String responseBody = Utils.convertTypedBodyToString(response.body());
                            JSONObject jsonObject = new JSONObject(responseBody);
                            finish();
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


}
