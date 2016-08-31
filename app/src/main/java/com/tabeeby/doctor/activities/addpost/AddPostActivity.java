package com.tabeeby.doctor.activities.addpost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tabeeby.doctor.R;

public class AddPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
    }

    public void selectMultipleImage(View view) {
       /* Intent intent = new Intent();
        intent.setType("image*//*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.Action_mu);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), 2);*/
       /* Intent intent=new Intent(Action.ACTION_MULTIPLE_PICK);
        startActivityForResult(intent, 200);*/
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode == 2){

                String[] imagesPath = data.getStringArrayExtra("all_path");
                Log.i("****SizeOfImages",imagesPath.length+"");
            }
        }
    }
}
