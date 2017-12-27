package com.example.shahar.ex3_mt;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {
    private Button btn_myprofile_HomePage;
    private Button btn_readme_HomePage;
    private Button btn_map_HomePage;
    private Button btn_logout_HomePage;
    private Button btn_Products_HomePage;
    private Button btn_Suppliers_HomePage;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser().getUid().equals("Admin")) {
            btn_Suppliers_HomePage = (Button) findViewById(R.id.btn_Suppliers_HomePage);
            btn_Suppliers_HomePage.setVisibility(View.VISIBLE);
            btn_Suppliers_HomePage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(HomePage.this, SuppliersActivity.class);
                    startActivity(i);
                    finish();
                }
            });
        }

        btn_myprofile_HomePage = (Button) findViewById(R.id.btn_myprofile_HomePage);
        btn_myprofile_HomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomePage.this,UserProfile.class);
                startActivity(i);
                finish();
            }
        });

        btn_readme_HomePage=(Button)findViewById(R.id.btn_readme_HomePage);
        btn_readme_HomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomePage.this,ReadMe.class);
                startActivity(i);
                finish();
            }
        });

        btn_map_HomePage=(Button)findViewById(R.id.btn_map_HomePage);
        btn_map_HomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomePage.this,MapsActivity.class);
                startActivity(i);
            }
        });

        btn_logout_HomePage=(Button)findViewById(R.id.btn_logout_HomePage);
        btn_logout_HomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuth != null)
                    mAuth.signOut();
                Intent i=new Intent(HomePage.this,LoginActivity.class);
                startActivity(i);
                finish();

            }
        });

        btn_Products_HomePage = (Button) findViewById(R.id.btn_Products_HomePage);
        btn_Products_HomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomePage.this,ProductsActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}


