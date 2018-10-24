package com.example.hp1.nizarofficialprojectmovies;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CameraGalleryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CAMERA_REQUEST = 0;
    Button btTakePhoto , btGallery;
    ImageView imageView2;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_gallery);

        btTakePhoto = (Button) findViewById(R.id.btTakePhoto);
        btTakePhoto.setOnClickListener(this);

        btGallery = (Button) findViewById(R.id.btGallery);
        btGallery.setOnClickListener(this);

        imageView2 = (ImageView) findViewById(R.id.imageView2);


    }

    @Override
    public void onClick(View v) {
        if(v == btTakePhoto){
            Intent i =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,CAMERA_REQUEST);
        }else{

        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView2.setImageBitmap(photo);
        }
    }
}
