package com.example.shahar.ex3_mt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReadMe extends AppCompatActivity {
    private Button btn_readmeBackToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_me);

        btn_readmeBackToMenu=(Button)findViewById(R.id.btn_readmeBackToMenu);
        btn_readmeBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ReadMe.this,HomePage.class);
                startActivity(i);
                finish();

            }
        });

    }
}
