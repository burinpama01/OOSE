package com.example.burin.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddFloorActivity extends AppCompatActivity {

    ArrayList<String> floor;
    RecyclerView RecyclerViewAddfloor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_floor);

        floor = new ArrayList<>();
        floor.clear();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog("เพิ่มชั้นที่ " + (floor.size() + 1), "ใส่จำนวนห้อง");
            }
        });

        FloatingActionButton fabs = (FloatingActionButton) findViewById(R.id.fab_d);
        fabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (floor.size() > 0) {
                    showdialogDelete();
                }

            }
        });

        RecyclerViewAddfloor = (RecyclerView) findViewById(R.id.RecyclerView_Addfloor);
        RecyclerViewAddfloor.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAddfloor.setAdapter(new Adapter(floor));

        ImageView ImageViewBackAddfloorDrome = (ImageView) findViewById(R.id.ImageView_Back_Addfloor_Drome);
        ImageViewBackAddfloorDrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView TextViewConfirmAddfloorDrome = (TextView) findViewById(R.id.TextView_Confirm_Addfloor_Drome);
        TextViewConfirmAddfloorDrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (floor.size() > 0){
                    intent();
                }else {
                    Toast.makeText(AddFloorActivity.this, "กรุณาเพิ่มชั้น", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void intent(){
        Bundle bundle = getIntent().getExtras();

        //Bundle extra = new Bundle();
        //extra.putSerializable("c",new ArrayList<String>());

        Intent intent = new Intent(AddFloorActivity.this, PictureListActivity.class);
        intent.putExtra("NAME",bundle.getString("NAME").toString());
        intent.putExtra("PASSWORD",bundle.getString("PASSWORD").toString());
        intent.putExtra("FLOOR",floor);
        //intent.putExtra("EXTRA",extra);
        startActivity(intent);
    }

    public void showdialog(String Title, String Message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialog.setView(dialogView);
        dialog.setTitle(Title);
        dialog.setMessage(Message);
        final EditText editText = (EditText) dialogView.findViewById(R.id.custom_dialog_edittext);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        dialog.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = editText.getText().toString();
                Integer i = Integer.parseInt(text);
                if (text.length() > 0) {
                    if(i > 99){
                        Toast.makeText(AddFloorActivity.this,"จำนวนห้องเยอะเกินไป",Toast.LENGTH_SHORT).show();
                    }
                    else if (Integer.valueOf(text) > 0) {
                        floor.add(text);
                        RecyclerViewAddfloor.setAdapter(new Adapter(floor));
                    }else {
                        Toast.makeText(AddFloorActivity.this,"จำนวนห้องน้อยเกินไป",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(AddFloorActivity.this,"ไม่พบการกรอกข้อมูล",Toast.LENGTH_SHORT).show();
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

    public void showdialogDelete() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialog.setView(dialogView);
        dialog.setTitle("ลบชั้นที่ " + floor.size());
        dialog.setMessage("ต้องการลบชั้น");
        final EditText editText = (EditText) dialogView.findViewById(R.id.custom_dialog_edittext);
        editText.setVisibility(View.GONE);
        dialog.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                floor.remove(floor.size() - 1);
                RecyclerViewAddfloor.setAdapter(new Adapter(floor));
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
