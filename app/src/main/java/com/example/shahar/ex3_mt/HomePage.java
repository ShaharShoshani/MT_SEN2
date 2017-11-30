package com.example.shahar.ex3_mt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    private Button btn_myprofile_HomePage;
    private Button btn_readme_HomePage;
    private Button btn_map_HomePage;
    private Button btn_logout_HomePage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

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
                Intent i=new Intent(HomePage.this,LoginActivity.class);
                startActivity(i);
                finish();

            }
        });
    }
}
