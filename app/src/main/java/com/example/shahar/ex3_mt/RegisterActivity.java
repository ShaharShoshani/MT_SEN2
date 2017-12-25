package com.example.shahar.ex3_mt;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    private Button btn_Confirm;
    private ImageButton btn_image;
    private ImageButton btn_camera;
    private ImageView x;
    private Uri outputFileUri;
    private EditText inputUserName;
    private EditText inputPassword;
    private EditText inputFirstName;
    private EditText inputLastName;
    private EditText inputCity;
    private EditText inputStreet;
    private EditText inputEmail;
    private FirebaseDatabase database ;
    private DatabaseReference refDatabase;
    private FirebaseAuth mAuth;
    private boolean flag=false;
    private String uid;
    private User user;
    private static final String TAG = "EmailPassword";



    //private  String file="";
    public static final int PICK_IMAGE = 1;
    public static final int TAKE_IMAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
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
        final File newdir = new File(dir);
        newdir.mkdirs();

        btn_Confirm=(Button) findViewById(R.id.btn_Confirm);
        btn_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName= inputUserName.getText().toString();
                String password=inputPassword.getText().toString();
                String firstName=inputFirstName.getText().toString();
                String lastName=inputLastName.getText().toString();
                String city=inputCity.getText().toString();
                String street=inputStreet.getText().toString();
                String email=inputEmail.getText().toString();
                if((!userName.equals("") && !password.equals("") && !firstName.equals("") &&
                        !lastName.equals("") && !city.equals("") && !street.equals("") &&
                        !email.equals("") )) {
                    View focusView = null;
                    if(password.length()>=6) {
                        if(email.contains("@")) {
                            user = new User(userName, password, firstName, lastName, city, street, email);
                            refDatabase = database.getReference();
                            mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                // Sign in success, update UI with the signed-in user's information
                                                Log.d(TAG, "createUserWithEmail:success");
                                                Toast.makeText(RegisterActivity.this, "Authentication succsess.",
                                                        Toast.LENGTH_LONG).show();
                                                FirebaseUser fuser = mAuth.getCurrentUser();
                                                uid = fuser.getUid();
                                                refDatabase.child("Users").child("Regular-User").child(uid).setValue(user);
                                                Intent i = new Intent(RegisterActivity.this, HomePage.class);
                                                startActivity(i);
                                                finish();
                                            } else {
                                                // If sign in fails, display a message to the user.
                                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                                        Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, getString(R.string.error_invalid_email),
                                    Toast.LENGTH_LONG).show();


                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, getString(R.string.error_invalid_password),
                                Toast.LENGTH_LONG).show();

                    }
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Please add the missing field..",
                            Toast.LENGTH_SHORT).show();

                }


            }


        });

        btn_image=(ImageButton) findViewById(R.id.btn_image) ;
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                }
                else
                {
                    intent=new Intent();
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

                }

                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);


            }
        });

        btn_camera=(ImageButton) findViewById(R.id.btn_camera) ;
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {

                    Calendar calendar=Calendar.getInstance();
                    String file =dir+calendar.getTimeInMillis()+".jpg";
                    File newfile=new File(file);
                    outputFileUri=Uri.fromFile(newfile);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                }
                startActivityForResult(cameraIntent, TAKE_IMAGE);
            }
        });

        inputUserName=(EditText)findViewById(R.id.edt_UserName);
        inputPassword=(EditText)findViewById(R.id.edt_Password);
        inputFirstName=(EditText)findViewById(R.id.edt_FirstName);
        inputLastName=(EditText)findViewById(R.id.edt_LastName);
        inputCity=(EditText)findViewById(R.id.edt_City);
        inputStreet=(EditText)findViewById(R.id.edt_Street);
        inputEmail=(EditText)findViewById(R.id.edt_Email);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            x=(ImageView)findViewById(R.id.imageview);
            final Bundle extras = data.getExtras();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                Bitmap bm = extras.getParcelable("data");
                Uri uribit=getImageUri(getApplicationContext(),bm);
                String path=getRealPathFrpomURI(uribit);
                path=path.substring(path.lastIndexOf(".")+1);
                if(path.equals("jpg")|| path.equals("png")) {
                    x.setImageBitmap(bm);
                    Toast.makeText(getApplicationContext(), path+" uploaded successfully", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Illegal format",Toast.LENGTH_LONG).show();

                }
            }
            else
            {
                String path=getRealPathFrpomURI(uri);
                path=path.substring(path.lastIndexOf(".")+1);
                if(path.equals("jpg")|| path.equals("png")) {
                    x.setImageURI(null);
                    x.setImageURI(uri);
                    x.setBackgroundColor(0);
                    Toast.makeText(getApplicationContext(),path+" uploaded successfully "+path,Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Illegal format",Toast.LENGTH_LONG).show();
                }
            }


        }
        else if (requestCode == TAKE_IMAGE && resultCode == RESULT_OK){
            x=(ImageView)findViewById(R.id.imageview);
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
                x.setImageURI(null);
                x.setImageURI(outputFileUri);
                x.setBackgroundColor(0);
            }
            else{
                final Bundle extras = data.getExtras();
                Bitmap bm = extras.getParcelable("data");
                x.setImageBitmap(bm);
            }

            Toast.makeText(getApplicationContext(),"Photo uploaded successfully",Toast.LENGTH_LONG).show();
        }

    }
    private String getRealPathFrpomURI(Uri uri)
    {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void create_user_in_auth(User user){
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            flag=true;
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


}