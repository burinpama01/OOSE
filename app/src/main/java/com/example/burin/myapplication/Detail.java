package com.example.burin.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Detail extends AppCompatActivity {


    private RadioButton R1;
    private RadioButton R2;
    private RadioButton R3;

    private int position = -1;

    String name;
    String floor;
    String room;
    Spinner spinner;

    TextView tv_name;
    TextView tv_nickname;
    TextView tv_age;
    TextView tv_ID;
    TextView tv_phonenumber;

    String m_name = "";
    String m_nickname = "";
    String m_age = "";
    String m_id = "";
    String m_phone = "";
    String m_password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        ImageView imgview_back_detail = (ImageView) findViewById(R.id.back_imageView_detail);
        imgview_back_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final TextView tv_name = (TextView) findViewById(R.id.detail_detail_name_textview);
        final TextView tv_nickname = (TextView) findViewById(R.id.detail_detail_nickname_textview);
        final TextView tv_age = (TextView) findViewById(R.id.detail_detail_age_textview);
        final TextView tv_ID = (TextView) findViewById(R.id.detail_detail_ID_textview);
        final TextView tv_phonenumber = (TextView) findViewById(R.id.detail_detail_phonenumber_textview);
        final TextView tv_password = (TextView)findViewById(R.id.detail_detail_password_textview);
        tv_password.setVisibility(View.VISIBLE);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("NAME").toString();
        floor = bundle.getString("FLOOR").toString();
        room = bundle.getString("ROOM").toString();

        TextView title = (TextView) findViewById(R.id.detail_title);
        title.setText("ห้อง " + room);


        spinner = (Spinner) findViewById(R.id.detail_detail_sex_spinnerview);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Detail.this, R.array.sex, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference roomRef = databaseReference.child("หอพัก").child(name).child("ห้องพัก").child(floor).child(room).child("ผู้เช่า");

        roomRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    RoomDetail roomDetail = dataSnapshot.getValue(RoomDetail.class);

                    m_age = roomDetail.getAge();
                    m_id = roomDetail.getIdCard();
                    m_name = roomDetail.getName();
                    m_nickname = roomDetail.getNickName();
                    m_phone = roomDetail.getPhoneNumber();

                    tv_age.setText(m_age + " ปี");
                    tv_ID.setText(m_id);
                    tv_name.setText(m_name);
                    tv_nickname.setText(m_nickname);
                    tv_phonenumber.setText(m_phone);

                    if (roomDetail.getSex().contentEquals("ชาย")) {
                        spinner.setSelection(1);
                    } else if (roomDetail.getSex().contentEquals("หญิง")) {
                        spinner.setSelection(2);
                    } else {
                        spinner.setSelection(0);
                    }

                }else {
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference dataRef = databaseReference.child("หอพัก").child(name).child("ห้องพัก").child(floor).child(room).child("รายละเอียด").child("password");
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    tv_password.setText(dataSnapshot.getValue().toString());
                }else {
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference infoRef = databaseReference.child("หอพัก").child(name).child("ห้องพัก").child(floor).child(room).child("รายละเอียด").child("status");
        infoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    String status = dataSnapshot.getValue().toString();
                    if (status.contentEquals("ว่าง")) {
                        checkedRadio(0);
                    } else if (status.contentEquals("เช่าปกติ")) {
                        checkedRadio(1);
                    } else {
                        checkedRadio(2);
                    }
                }else {
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        RelativeLayout name_layout = (RelativeLayout) findViewById(R.id.detail_detail_name_layout);
        name_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog("เพิ่มชื่อ", "กรุณากรอกชื่อ-นามสกุล", tv_name, 0, m_name, InputType.TYPE_CLASS_TEXT);
            }
        });

        RelativeLayout nickname_layout = (RelativeLayout) findViewById(R.id.detail_detail_nickname_layout);
        nickname_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog("เพิ่มชื่อ", "กรุณากรอกชื่อเล่น", tv_nickname, 3, m_nickname, InputType.TYPE_CLASS_TEXT);
            }
        });

        RelativeLayout age_layout = (RelativeLayout) findViewById(R.id.detail_detail_age_layout);
        age_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog("เพิ่มอายุ", "กรุณากรอกอายุ", tv_age, 1, m_age, InputType.TYPE_CLASS_NUMBER);
            }
        });

        RelativeLayout ID_layout = (RelativeLayout) findViewById(R.id.detail_detail_ID_layout);
        ID_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog("เพิ่มเลขบัตรประชาชน", "กรุณากรอกเลขบัตรประชาชน", tv_ID, 2, m_id, InputType.TYPE_CLASS_NUMBER);
            }
        });

        RelativeLayout phonenumber_layout = (RelativeLayout) findViewById(R.id.detail_detail_phonenumber_layout);
        phonenumber_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog("เพิ่มเบอร์โทร", "กรุณากรอกเบอร์โทร", tv_phonenumber, 4, m_phone, InputType.TYPE_CLASS_PHONE);
            }
        });

        RelativeLayout password_layout = (RelativeLayout)findViewById(R.id.detail_detail_password_layout);
        password_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog("ตั้งรหัสผ่าน","กรุณากรอกรหัสผ่าน",tv_password,5,m_password,InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });


        R1 = (RadioButton) findViewById(R.id.radioButton_detail_1);
        R2 = (RadioButton) findViewById(R.id.radioButton_detail_2);
        R3 = (RadioButton) findViewById(R.id.radioButton_detail_3);

        R1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedRadio(0);
            }
        });
        R2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedRadio(1);
            }
        });
        R3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedRadio(2);
            }
        });

        Button button = (Button) findViewById(R.id.detail_submit_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFirebase();
                finish();
            }
        });
    }


    private void clear() {
        R1.setChecked(false);
        R2.setChecked(false);
        R3.setChecked(false);
    }

    private void checkedRadio(int ID) {
        if (ID == 0) {
            clear();
            R1.setChecked(true);

            position = 0;
        } else if (ID == 1) {
            clear();
            R2.setChecked(true);
            position = 1;
        } else if (ID == 2) {
            clear();
            R3.setChecked(true);

            position = 2;
        } else {
            clear();
            position = -1;
        }
    }

    private void setFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference roomRef = databaseReference.child("หอพัก").child(name).child("ห้องพัก").child(floor).child(room);
        DatabaseReference infoRef = roomRef.child("รายละเอียด").child("status");
        DatabaseReference userRef = roomRef.child("ผู้เช่า");

        DatabaseReference a = roomRef.child("รายละเอียด").child("password");

        RoomDetail roomDetail = new RoomDetail();
        roomDetail.setAge(m_age);
        roomDetail.setName(m_name);
        roomDetail.setIdCard(m_id);
        roomDetail.setNickName(m_nickname);
        roomDetail.setPhoneNumber(m_phone);
        roomDetail.setSex(getSex(spinner.getSelectedItemPosition()));

        userRef.setValue(roomDetail);

        infoRef.setValue(getStatus());
        a.setValue(m_password);
    }

    private String getStatus() {
        if (position == 0) {
            return "ว่าง";
        } else if (position == 1) {
            return "เช่าปกติ";
        } else if (position == 2) {
            return "ชำรุด";
        } else {
            return "ว่าง";
        }
    }

    private String getSex(int ID) {
        if (ID == 1) {
            return "ชาย";
        } else if (ID == 2) {
            return "หญิง";
        } else {
            return "";
        }
    }

    public void showdialog(String Title, String Message, final TextView tv, final int id, String raw, int type) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialog.setView(dialogView);
        dialog.setTitle(Title);
        dialog.setMessage(Message);
        final EditText editText = (EditText) dialogView.findViewById(R.id.custom_dialog_edittext);
        editText.requestFocus();
        editText.setText(raw);
        editText.setInputType(type);
        dialog.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = editText.getText().toString();
                if (id != 1 && id != 2 && id != 4) {
                    tv.setText(text);
                }

                if (id == 0) {
                    m_name = text;
                } else if (id == 1) {
                    m_age = text;
                    tv.setText(text + " ปี");
                } else if (id == 2) {
                    if(text.length() > 13){
                        new Dialog().alert(Detail.this,"แจ้งเตือน","กรุณากรอกไม่เกิน 13 หลัก");
                    }else if(text.length() < 13){
                        new Dialog().alert(Detail.this,"แจ้งเตือน","กรุณากรอกให้ครบ 13 หลัก");
                    }else {
                        m_id = text;
                        tv.setText(text);
                    }
                } else if (id == 3) {
                    m_nickname = text;
                } else if (id == 4) {
                    if (text.length() > 10){
                        new Dialog().alert(Detail.this,"แจ้งเตือน","กรุณากรอกไม่เกิน 10 หลัก");
                    }else if(text.length() < 10){
                        new Dialog().alert(Detail.this,"แจ้งเตือน","กรุณากรอกให้ครบ 10 หลัก");
                    } else{
                        m_phone = text;
                        tv.setText(text);
                    }
                } else if (id == 5){
                    m_password = text;
                }
            }
        });

        dialog.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog abc = dialog.create();
        abc.show();
    }


}
