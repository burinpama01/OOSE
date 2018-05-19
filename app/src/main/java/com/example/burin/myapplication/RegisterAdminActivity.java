package com.example.burin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegisterAdminActivity extends AppCompatActivity {

    private ArrayList<String> arr;
    private String str;
    EditText name;
    EditText password;
    EditText re_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page_one);

        arr = new ArrayList<>();

        ImageView BackRegisAdmin = (ImageView)findViewById(R.id.Back_Register_Admin);
        BackRegisAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        name = (EditText)findViewById(R.id.register_admin_edittext_name);
        password = (EditText)findViewById(R.id.register_admin_edittext_password);
        re_password = (EditText)findViewById(R.id.register_admin_edittext_reenter_password);


        CardView ButtonRegisterAdminDrome = (CardView)findViewById(R.id.Button_Register_Admin_Drome);
        ButtonRegisterAdminDrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str = name.getText().toString();

                if ((name.getText().toString().length() > 0) || (password.getText().toString().length() > 0) || (re_password.getText().toString().length() > 0)){
                    if(password.getText().toString().length() > 3){
                        if(password.getText().toString().contentEquals(re_password.getText().toString())){
                            firebase();
                        } else {
                            new Dialog().alert(RegisterAdminActivity.this,"แจ้งเตือน","รหัสผ่านไม่ตรงกัน");
                            //Toast.makeText(RegisterAdminActivity.this,"รหัสผ่านไม่ตรงกัน",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        new Dialog().alert(RegisterAdminActivity.this,"แจ้งเตือน","กรุณาพิมพ์รหัสผ่านอย่างน้อย 4 ตัว");

                    }
                } else {
                    new Dialog().alert(RegisterAdminActivity.this,"แจ้งเตือน","ข้อมูลไม่ครบ");
                    //Toast.makeText(RegisterAdminActivity.this,"ข้อมูลไม่ครบ",Toast.LENGTH_SHORT).show();
                }




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

                if(a){
                    new Dialog().alert(RegisterAdminActivity.this,"แจ้งเตือน","หอพักชื่อนี้มีอยู่แล้ว");
                    //Toast.makeText(RegisterAdminActivity.this,"หอพักชื่อนี้มีอยู่แล้ว",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(RegisterAdminActivity.this,AddFloorActivity.class);
                    intent.putExtra("NAME",name.getText().toString());
                    intent.putExtra("PASSWORD",password.getText().toString());
                    startActivity(intent);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                new Dialog().alert(RegisterAdminActivity.this,"แจ้งเตือน","การเชื่อมต่อล่มเหลว");
                //Toast.makeText(RegisterAdminActivity.this,"การเชื่อมต่อล่มเหลว",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
