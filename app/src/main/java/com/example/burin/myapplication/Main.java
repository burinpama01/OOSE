package com.example.burin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);

        Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("NAME").toString();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference parent = databaseReference.child("หอพัก").child(name);

        Button loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                final String passs = password.getText().toString();

                DatabaseReference info = parent.child("รายละเอียด");

                if(user.contentEquals("admin")){
                    final DatabaseReference pass = info.child("รหัสผ่าน");

                    pass.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getValue().toString().contentEquals(passs)){
                                Intent intent = new Intent(Main.this, FloorListActivity.class);
                                intent.putExtra("NAME",name);
                                startActivity(intent);

                                username.setText("");
                                password.setText("");
                            }else {
                                Toast.makeText(Main.this,"รหัสผ่านไม่ถูกต้อง",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(Main.this,"ระบบผืดพลาด",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(Main.this,"ไม่พบผู้ใช้",Toast.LENGTH_SHORT).show();
                }

            }
        });


//Log.e("TEST","HELLO");

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Main.this, RegisterDromeActivity.class);
        startActivity(intent);

        finish();

        super.onBackPressed();

    }
}
