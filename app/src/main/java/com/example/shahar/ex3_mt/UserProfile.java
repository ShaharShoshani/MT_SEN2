package com.example.shahar.ex3_mt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    private Button btn_getback;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth        mAuth;
    private static final String TAG = "ReadFromDataBaseUser";
    private EditText inputUserName;
    private EditText inputFirstName;
    private EditText inputLastName;
    private EditText inputCity;
    private EditText inputStreet;
    private EditText inputEmail;
    private ImageView inputPhoto;
    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        inputUserName=(EditText)findViewById(R.id.edt_user_name_MyProfile);
        inputFirstName=(EditText)findViewById(R.id.edt_first_name_MyProfile);
        inputLastName=(EditText)findViewById(R.id.edt_last_name_MyProfile);
        inputCity=(EditText)findViewById(R.id.edt_city_MyProfile);
        inputStreet=(EditText)findViewById(R.id.edt_street_MyProfile);
        inputEmail=(EditText)findViewById(R.id.edt_email_MyProfile);
        inputPhoto=(ImageView)findViewById(R.id.edt_photo_MyProfile);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        mAuth=FirebaseAuth.getInstance();
        uid=mAuth.getCurrentUser().getUid();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (!uid.equals("Admin")) {
                    DataSnapshot get = dataSnapshot.child(uid);
                    String x;
                    if (get != null) {
                        x = "Getting User Info...";
                        User user = get.getValue(User.class);
                        inputUserName.setText(user.getUserName());
                        inputFirstName.setText(user.getFirstName());
                        inputLastName.setText(user.getLastName());
                        inputCity.setText(user.getCity());
                        inputStreet.setText(user.getStreet());
                        inputEmail.setText(user.getEmail());
                    } else
                        x = "Can't Get User Info";
                    Toast.makeText(UserProfile.this, x,
                            Toast.LENGTH_LONG).show();


                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        btn_getback = (Button) findViewById(R.id.btn_backtomenu_MyProfile);
        btn_getback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(UserProfile.this,HomePage.class);
                startActivity(i);
                finish();
            }
        });
    }
}

/*
                    inputUserName.setText(user.getUserName());
                    inputFirstName.setText(user.getFirstName());
                    inputLastName.setText(user.getLastName());
                    inputCity.setText(user.getCity());
                    inputStreet.setText(user.getStreet());
                    inputEmail.setText(user.getEmail());
 */