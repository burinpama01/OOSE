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
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main extends AppCompatActivity {

    ImageView cover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cover = (ImageView)findViewById(R.id.login_cover);

        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);



        Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("NAME").toString();

        TextView titles = (TextView)findViewById(R.id.login_title);
        titles.setText("หอพัก "+name);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference parent = databaseReference.child("หอพัก").child(name);

        DatabaseReference infos = parent.child("รายละเอียด");

        DatabaseReference dRef = infos.child("รูปพื้นหลัง");
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Integer x = (int)dataSnapshot.getValue();
                if(dataSnapshot.getValue() == null){
                    Intent intent = new Intent(Main.this,RegisterDromeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Integer x = Integer.parseInt(dataSnapshot.getValue().toString());
                    setCover(new Tools().getImageArray().get(x));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        CardView loginButton = (CardView) findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String user = username.getText().toString();
                final String passs = password.getText().toString();

                DatabaseReference info = parent.child("รายละเอียด");



                if(user.contentEquals("admin")){
                    final DatabaseReference pass = info.child("รหัสผ่าน");

                    pass.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue()!=null){
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

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(Main.this,"ระบบผืดพลาด",Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {

                    if(user.length() >= 3){
                        String x = user.substring(0,user.length()-2);
                        DatabaseReference dataRef = parent.child("ห้องพัก").child("ชั้นที่ "+x).child(user).child("รายละเอียด").child("password");
                        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.getValue()!=null){
                                    String pa = dataSnapshot.getValue().toString();
                                    if(passs.length() == 0){
                                        Toast.makeText(Main.this,"กรุณาพิมพ์รหัสผ่าน",Toast.LENGTH_SHORT).show();
                                    }else if(passs.contentEquals(pa)){
                                        Intent intent = new Intent(Main.this,DetailUserActivity.class);
                                        startActivity(intent);
                                    }else {
                                        Toast.makeText(Main.this,"รหัสผ่านไม่ถูกต้อง",Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(Main.this,"ไม่พบผู้ใช้",Toast.LENGTH_SHORT).show();
                                }



                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }else {
                        Toast.makeText(Main.this,"ไม่พบผู้ใช้",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        RelativeLayout back = (RelativeLayout)findViewById(R.id.login_back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, RegisterDromeActivity.class);
                startActivity(intent);

                finish();
            }
        });


Log.e("TEST","HELLO");

    }

    private void setCover(int res){
        cover.setImageDrawable(getResources().getDrawable(res));
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Main.this, RegisterDromeActivity.class);
        startActivity(intent);

        finish();

        super.onBackPressed();

    }
}
