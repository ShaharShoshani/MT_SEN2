package com.example.shahar.ex3_mt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductsActivity extends AppCompatActivity {

    private Button btn_ProductsBackToMenu;
    private Button btn_add_products;
    private EditText edt_name_product;
    private EditText edt_quantity_product;
    private TextView edt_price_product;
    private TextView view_products_text;
    private TextView  view_admin_text;
    private TextView tv_name_product;
    private TextView tv_products_quantity;
    private TextView tv_products_price;
    private FirebaseDatabase database ;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private static final String TAG = "ReadDataBaseProducts";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        view_products_text=(TextView)findViewById(R.id.view_products_text);


        database = FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser().getUid().equals("Admin"))
        {

            edt_name_product=(EditText)findViewById(R.id.edt_name_product);
            btn_add_products=(Button)findViewById((R.id.btn_add_products));
            edt_quantity_product=(EditText)findViewById(R.id.edt_quantity_product);
            edt_price_product=(EditText)findViewById(R.id.edt_price_product);
            view_admin_text=(TextView)findViewById(R.id.view_admin_text);
            tv_name_product=(TextView)findViewById(R.id.tv_name_product);
            tv_products_quantity=(TextView)findViewById(R.id.tv_quantity_product);
            tv_products_price=(TextView)findViewById(R.id.tv_price_product);
            view_admin_text.setVisibility(View.VISIBLE);
            edt_name_product.setVisibility(View.VISIBLE);
            edt_quantity_product.setVisibility(View.VISIBLE);
            edt_price_product.setVisibility(View.VISIBLE);
            btn_add_products.setVisibility(View.VISIBLE);
            tv_name_product.setVisibility(View.VISIBLE);
            tv_products_quantity.setVisibility(View.VISIBLE);
            tv_products_price.setVisibility(View.VISIBLE);
            btn_add_products = (Button) findViewById(R.id.btn_add_products);
            btn_add_products.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name=edt_name_product.getText().toString();
                    String quantity = edt_quantity_product.getText().toString();
                    String price = edt_price_product.getText().toString();
                    if(name.equals("") || quantity.equals("") || price.equals("")){
                        Toast.makeText(ProductsActivity.this, "Please enter the fields.",
                                Toast.LENGTH_LONG).show();
                    }
                    else {
                        Product supp = new Product(name, quantity,price);
                        String key=myRef.push().getKey();
                        myRef.child(key).setValue(supp);
                        Toast.makeText(ProductsActivity.this, "Product added!",
                                Toast.LENGTH_LONG).show();
                        edt_name_product.setText(null);
                        edt_quantity_product.setText(null);
                        edt_price_product.setText(null);

                    }
                }
            });
        }

        myRef= database.getReference("Products");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String x;
                if(dataSnapshot != null) {
                    x = "Getting Products Info...";
                    Toast.makeText(ProductsActivity.this, x,
                            Toast.LENGTH_SHORT).show();
                    int i=1;
                    for(DataSnapshot data:dataSnapshot.getChildren()){
                        Product prod = data.getValue(Product.class);
                        view_products_text.append("\n"+i+") Product name: "
                                +prod.getNameProduct()+"\n    Quantity: "+
                                prod.getQuantity()+"\n    Price: "+prod.getPrice());
                        i++;
                    }

                }
                else {
                    x = "Can't Get Products Info";
                    Toast.makeText(ProductsActivity.this, x,
                            Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value Products.", error.toException());
            }
        });

        btn_ProductsBackToMenu = (Button) findViewById(R.id.btn_ProductsBackToMenu);
        btn_ProductsBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ProductsActivity.this,HomePage.class);
                startActivity(i);
                finish();
            }
        });

    }
}
