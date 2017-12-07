package com.example.shahar.ex3_mt;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import static android.content.Intent.URI_ALLOW_UNSAFE;

public class RegisterActivity extends AppCompatActivity {
    private Button btn_Confirm;
    private ImageButton btn_image;
    private ImageButton btn_camera;
    private ImageView x;
    //private Uri outputFileUri;
    //private  String file="";
    public static final int PICK_IMAGE = 1;
    public static final int TAKE_IMAGE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        String[] permissions = new String[]{ Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED  ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, 124);
            }
        }
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        newdir.mkdirs();

        btn_Confirm=(Button) findViewById(R.id.btn_Confirm);
        btn_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Intent i = new Intent(RegisterActivity.this, HomePage.class);
        startActivity(i);
        finish();
            }
        });

        btn_image=(ImageButton) findViewById(R.id.btn_image) ;
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra("crop", "true");
                intent.putExtra("scale", true);
                intent.putExtra("outputX", 256);
                intent.putExtra("outputY", 256);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("return-data", true);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

            }
        });

        btn_camera=(ImageButton) findViewById(R.id.btn_camera) ;
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //cameraIntent.putExtra("crop", "true");
                cameraIntent.putExtra("scale", true);
                cameraIntent.putExtra("outputX", 256);
                cameraIntent.putExtra("outputY", 256);
                cameraIntent.putExtra("aspectX", 1);
                cameraIntent.putExtra("aspectY", 1);
                cameraIntent.putExtra("return-data", true);
                startActivityForResult(cameraIntent, TAKE_IMAGE);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            x=(ImageView)findViewById(R.id.imageview);
            final Bundle extras = data.getExtras();
            Bitmap bm = extras.getParcelable("data");
            x.setImageBitmap(bm);
            //x.setImageURI(null);
            //x.setImageURI(uri);
            x.setBackgroundColor(0);
            Toast.makeText(getApplicationContext(),"Photo uploaded successfully",Toast.LENGTH_LONG).show();
        }
        else if (requestCode == TAKE_IMAGE && resultCode == RESULT_OK){
            x=(ImageView)findViewById(R.id.imageview);
            final Bundle extras = data.getExtras();
            Bitmap bm = extras.getParcelable("data");
            x.setImageBitmap(bm);
            //x.setImageURI(null);
            //x.setImageURI(outputFileUri);
            x.setBackgroundColor(0);
            Toast.makeText(getApplicationContext(),"Photo uploaded successfully",Toast.LENGTH_LONG).show();
        }

    }



}
