package com.example.burin.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Detail extends AppCompatActivity {


    private RadioButton R1;
    private RadioButton R2;
    private RadioButton R3;

    private int position = -1;

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

        final TextView tv_name = (TextView)findViewById(R.id.detail_detail_name_textview);
        final TextView tv_nickname = (TextView)findViewById(R.id.detail_detail_nickname_textview);
        final TextView tv_age = (TextView)findViewById(R.id.detail_detail_age_textview);
        final TextView tv_ID = (TextView)findViewById(R.id.detail_detail_ID_textview);
        final TextView tv_phonenumber = (TextView)findViewById(R.id.detail_detail_phonenumber_textview);

        RelativeLayout name_layout = (RelativeLayout)findViewById(R.id.detail_detail_name_layout);
        name_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog("เพิ่มชื่อ","กรุณากรอกชื่อ-นามสกุล",tv_name, InputType.TYPE_CLASS_TEXT);
            }
        });

        RelativeLayout nickname_layout = (RelativeLayout)findViewById(R.id.detail_detail_nickname_layout);
        nickname_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog("เพิ่มชื่อ","กรุณากรอกชื่อเล่น",tv_nickname, InputType.TYPE_CLASS_TEXT);
            }
        });

        RelativeLayout age_layout = (RelativeLayout)findViewById(R.id.detail_detail_age_layout);
        age_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog("เพิ่มอายุ","กรุณากรอกอายุ",tv_age, InputType.TYPE_CLASS_NUMBER);
            }
        });

        RelativeLayout ID_layout = (RelativeLayout)findViewById(R.id.detail_detail_ID_layout);
        ID_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog("เพิ่มเลขบัตรประชาชน","กรุณากรอกเลขบัตรประชาชน",tv_ID, InputType.TYPE_CLASS_NUMBER);
            }
        });

        RelativeLayout phonenumber_layout = (RelativeLayout)findViewById(R.id.detail_detail_phonenumber_layout);
        phonenumber_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog("เพิ่มเบอร์โทร","กรุณากรอกเบอร์โทร",tv_phonenumber, InputType.TYPE_CLASS_PHONE);
            }
        });


        Spinner spinner = (Spinner)findViewById(R.id.detail_detail_sex_spinnerview);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Detail.this, R.array.sex, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        R1 = (RadioButton)findViewById(R.id.radioButton_detail_1);
        R2 = (RadioButton)findViewById(R.id.radioButton_detail_2);
        R3 = (RadioButton)findViewById(R.id.radioButton_detail_3);

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
    }


    private void clear(){
        R1.setChecked(false);
        R2.setChecked(false);
        R3.setChecked(false);
    }

    private void checkedRadio(int ID){
        if (ID == 0){
            clear();
            R1.setChecked(true);
        } else if (ID == 1){
            clear();
            R2.setChecked(true);
        }else if (ID == 2){
            clear();
            R3.setChecked(true);
        }else {
            clear();
        }
    }

    public void showdialog(String Title, String Message, final TextView tv, int type){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialog.setView(dialogView);
        dialog.setTitle(Title);
        dialog.setMessage(Message);
        final EditText editText = (EditText)dialogView.findViewById(R.id.custom_dialog_edittext) ;
        editText.setText(tv.getText().toString());
        editText.setInputType(type);
        dialog.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = editText.getText().toString();
                tv.setText(text);

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
