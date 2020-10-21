package com.example.feelhut;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;



public class editprofile extends AppCompatActivity {
    ImageButton proileimagebutton;
    private static final int ImageRequest=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        proileimagebutton = findViewById(R.id.edit_profile_image);
        try {
            proileimagebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/");
                    startActivityForResult(intent, ImageRequest);

                }
            });
        } catch(NullPointerException e){
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if (requestCode == ImageRequest  && resultCode==RESULT_OK)//-1
             {
                 Uri imageUri= data.getData();
                 CropImage.activity(imageUri)
                         .setGuidelines(CropImageView.Guidelines.ON)
                         .setAspectRatio(1,1)
                         .start(this);
    }
         if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
             CropImage.ActivityResult result= CropImage.getActivityResult(data);
             if (resultCode==RESULT_OK){
                 Uri resultUri = result.getUri();
                 proileimagebutton.setImageURI(resultUri);
             }
 else{
     if (resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
         Exception error = result.getError();
     }
             }
         }
    }
}
