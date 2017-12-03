package com.example.shahar.ex3_mt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class RegisterActivity extends AppCompatActivity {
private Button btn_Confirm;
    private ImageButton btn_image;
    public static final int PICK_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                ImageButton btn = (ImageButton)findViewById(R.id.btn_image);
                btn.setImageResource(R.drawable.uploaded_photo);



            }
        });

    }
}
