package com.example.shahar.ex3_mt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SuppliersActivity extends AppCompatActivity {
    private TextView supplierview;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Button btn_getback;
    private Button btn_addsupplier;
    private EditText edt_contactsupplier;
    private EditText edt_namesupplier;
    private FirebaseAuth mAuth;


    private static final String TAG = "ReadDataBaseSuppliers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers);
        supplierview=(TextView)findViewById(R.id.view_suppliers_text);
        edt_contactsupplier=(EditText)findViewById(R.id.edt_contact_supplier);
        edt_namesupplier=(EditText)findViewById(R.id.edt_name_supplier);
        database = FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        myRef= database.getReference("Suppliers");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String x;
                if(dataSnapshot != null) {
                    x = "Getting Suppliers Info...";
                    Toast.makeText(SuppliersActivity.this, x,
                            Toast.LENGTH_SHORT).show();
                    int i=1;
                    for(DataSnapshot data:dataSnapshot.getChildren()){
                        Supplier supp = data.getValue(Supplier.class);
                        supplierview.append("\n"+i+") Supplier name: "
                                +supp.getSupplierName()+"\n    Supplier contact info: "+
                        supp.getSupplierContact());
                        i++;
                    }

                }
                else {
                    x = "Can't Get Suppliers Info";
                    Toast.makeText(SuppliersActivity.this, x,
                            Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value supplier.", error.toException());
            }
        });
        btn_addsupplier = (Button) findViewById(R.id.btn_add_Suppliers);
        btn_addsupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edt_namesupplier.getText().toString();
                String contact = edt_contactsupplier.getText().toString();
                if(name.equals("") || contact.equals("")){
                    Toast.makeText(SuppliersActivity.this, "Please enter the fields.",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Supplier supp = new Supplier(name, contact);
                    String key=myRef.push().getKey();
                    myRef.child(key).setValue(supp);
                    Toast.makeText(SuppliersActivity.this, "Supplier added!",
                            Toast.LENGTH_LONG).show();
                    edt_namesupplier.setText(null);
                    edt_contactsupplier.setText(null);
                }
            }
        });
        btn_getback = (Button) findViewById(R.id.btn_supplierBackToMenu);
        btn_getback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SuppliersActivity.this,HomePage.class);
                startActivity(i);
                finish();
            }
        });

    }
}
