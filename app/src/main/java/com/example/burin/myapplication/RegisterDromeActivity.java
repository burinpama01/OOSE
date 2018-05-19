package com.example.burin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

    private ArrayList<String> total;

    RecyclerView recyclerView;

    LinearLayout pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        pro = (LinearLayout)findViewById(R.id.add_floor_progress);

        arr = new ArrayList<>();
        editText = (EditText) findViewById(R.id.edittext_code_dorm);

        total = new ArrayList<>();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        recyclerView = (RecyclerView) findViewById(R.id.search_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SearchAdapter(new ArrayList<String>(),RegisterDromeActivity.this));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dorm = databaseReference.child("หอพัก");
        dorm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    total.add(ds.getKey());
                }

                recyclerView.setAdapter(new SearchAdapter(total,RegisterDromeActivity.this));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<String> ss = new ArrayList<>();
                if (editText.getText().length() == 0) {
                    ss = total;
                } else {
                    for (int i = 0; i < total.size(); i++) {
                        String a = total.get(i);
                        a =  "หอพัก " + a;
                        if (a.contains(editText.getText().toString())) {
                            ss.add(total.get(i));
                        }
                    }
                }

                recyclerView.setAdapter(new SearchAdapter(ss,RegisterDromeActivity.this));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        CardView EnterRegisButton = (CardView) findViewById(R.id.Button_Enter_Register_Drome);
        EnterRegisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                str = editText.getText().toString();
                //Log.e("adsa",String.valueOf(str.length()));
                if (str.length() > 0) {
                    firebase();
                } else {
                    Toast.makeText(RegisterDromeActivity.this, "กรุณากรอกชื่อหอพัก", Toast.LENGTH_SHORT).show();
                }

                //Intent intent = new Intent(RegisterDromeActivity.this, Main.class);
                //startActivity(intent);
            }
        });

        RelativeLayout TextViewRegisterDrome = (RelativeLayout) findViewById(R.id.TextView_Register_Drome);
        TextViewRegisterDrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterDromeActivity.this, RegisterAdminActivity.class);
                startActivity(intent);
            }
        });


    }

    private void firebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference root = databaseReference.child("หอพัก");

        arr.clear();

        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Test", "PASS");
                boolean a = false;

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //Toast.makeText(RegisterDromeActivity.this,ds.getKey().toString(),Toast.LENGTH_SHORT).show();
                    //Log.e("TEST",ds.getKey().toString());

                    //arr.add(ds.getKey().toString());

                    if (str.contentEquals(ds.getKey().toString())) {
                        a = true;
                    }

                    //check();
                }

                if (!a) {
                    Toast.makeText(RegisterDromeActivity.this, "ไม่พบหอพัก", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(RegisterDromeActivity.this, Main.class);
                    intent.putExtra("NAME", str);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(RegisterDromeActivity.this, "การเชื่อมต่อล่มเหลว", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void check() {


    }

}
