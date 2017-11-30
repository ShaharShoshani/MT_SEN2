package com.example.shahar.ex3_mt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserProfile extends AppCompatActivity {

    private Button btn_getback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
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
