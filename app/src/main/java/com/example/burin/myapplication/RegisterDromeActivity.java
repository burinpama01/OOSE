package com.example.burin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegisterDromeActivity extends AppCompatActivity {

    private ArrayList<String> arr;
    private EditText editText;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_drome);

        arr = new ArrayList<>();
        editText = (EditText)findViewById(R.id.edittext_code_dorm);

        Button EnterRegisButton = (Button)findViewById(R.id.Button_Enter_Register_Drome);
        EnterRegisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebase();

                str = editText.getText().toString();
                //Intent intent = new Intent(RegisterDromeActivity.this, Main.class);
                //startActivity(intent);
            }
        });

        TextView TextViewRegisterDrome = (TextView)findViewById(R.id.TextView_Register_Drome);
        TextViewRegisterDrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterDromeActivity.this,RegisterAdminActivity.class);
                startActivity(intent);
            }
        });



    }

    private void firebase(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference root = databaseReference.child("หอพัก");

        arr.clear();

        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Test","PASS");
                boolean a = false;

                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    //Toast.makeText(RegisterDromeActivity.this,ds.getKey().toString(),Toast.LENGTH_SHORT).show();
                    //Log.e("TEST",ds.getKey().toString());

                    //arr.add(ds.getKey().toString());

                    if(str.contentEquals(ds.getKey().toString())){
                        a = true;
                    }

                    //check();
                }

                if(!a){
                    Toast.makeText(RegisterDromeActivity.this,"ไม่พบหอพัก",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(RegisterDromeActivity.this, Main.class);
                    intent.putExtra("NAME",str);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(RegisterDromeActivity.this,"การเชื่อมต่อล่มเหลว",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void check(){




    }

}
