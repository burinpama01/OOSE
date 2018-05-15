package com.example.burin.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PictureListActivity extends AppCompatActivity {

    private Display display;
    private Point point;

    private RelativeLayout l1;
    private RelativeLayout l2;
    private RelativeLayout l3;
    private RelativeLayout l4;
    private RelativeLayout l5;
    private RelativeLayout l6;
    private RelativeLayout l7;
    private RelativeLayout l8;
    private RelativeLayout l9;

    private Integer position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_list);

        Bundle bundle = getIntent().getExtras();


        final ArrayList<String> floor = bundle.getStringArrayList("FLOOR");
        final String name = bundle.getString("NAME");
        final String password = bundle.getString("PASSWORD");

        ImageView ImageViewBackSelectPicture = (ImageView)findViewById(R.id.ImageView_Back_SelectPicture);
        ImageViewBackSelectPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        display = getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);

        ImageView IM1 = (ImageView) findViewById(R.id.pictureA1);
        setImageSize(IM1, R.drawable.beach);
        ImageView IM2 = (ImageView) findViewById(R.id.pictureA2);
        setImageSize(IM2, R.drawable.beach1);
        ImageView IM3 = (ImageView) findViewById(R.id.pictureA3);
        setImageSize(IM3, R.drawable.beach2);
        ImageView IM4 = (ImageView) findViewById(R.id.pictureA4);
        setImageSize(IM4, R.drawable.bridge);
        ImageView IM5 = (ImageView) findViewById(R.id.pictureA5);
        setImageSize(IM5, R.drawable.crabs);
        ImageView IM6 = (ImageView) findViewById(R.id.pictureA6);
        setImageSize(IM6, R.drawable.lake);
        ImageView IM7 = (ImageView) findViewById(R.id.pictureA7);
        setImageSize(IM7, R.drawable.moutain);
        ImageView IM8 = (ImageView) findViewById(R.id.pictureA8);
        setImageSize(IM8, R.drawable.mountain2);
        ImageView IM9 = (ImageView) findViewById(R.id.pictureA9);
        setImageSize(IM9, R.drawable.moutain1);

        l1 = (RelativeLayout) findViewById(R.id.check_pictureA1);
        l2 = (RelativeLayout) findViewById(R.id.check_pictureA2);
        l3 = (RelativeLayout) findViewById(R.id.check_pictureA3);
        l4 = (RelativeLayout) findViewById(R.id.check_pictureA4);
        l5 = (RelativeLayout) findViewById(R.id.check_pictureA5);
        l6 = (RelativeLayout) findViewById(R.id.check_pictureA6);
        l7 = (RelativeLayout) findViewById(R.id.check_pictureA7);
        l8 = (RelativeLayout) findViewById(R.id.check_pictureA8);
        l9 = (RelativeLayout) findViewById(R.id.check_pictureA9);

        Integer wi = point.x / 3;

        setLayoutParam(l1, wi);
        setLayoutParam(l2, wi);
        setLayoutParam(l3, wi);
        setLayoutParam(l4, wi);
        setLayoutParam(l5, wi);
        setLayoutParam(l6, wi);
        setLayoutParam(l7, wi);
        setLayoutParam(l8, wi);
        setLayoutParam(l9, wi);

        IM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkp(0);
            }
        });

        IM2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkp(1);
            }
        });

        IM3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkp(2);
            }
        });

        IM4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkp(3);
            }
        });

        IM5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkp(4);
            }
        });

        IM6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkp(5);
            }
        });

        IM7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkp(6);
            }
        });

        IM8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkp(7);
            }
        });

        IM9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkp(8);
            }
        });

        TextView TextViewConfirmSelectPicture = (TextView)findViewById(R.id.TextView_confirm_SelectPicture);
        TextViewConfirmSelectPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position>=0){
                    Intent intent = (new Intent(PictureListActivity.this,Main.class));
                    setFirebase(name,password,floor,position.toString());
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("NAME",name);
                    startActivity(intent);
                } else {
                    Toast.makeText(PictureListActivity.this,"กรุณาเลือกรูป",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setFirebase(String name,String pass,ArrayList<String> floor,String imageID){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference main = databaseReference.child("หอพัก").child(name);

        DatabaseReference info = main.child("รายละเอียด");
        DatabaseReference room = main.child("ห้องพัก");

        info.child("ชื่อหอ").setValue(name);
        info.child("รหัสผ่าน").setValue(pass);
        info.child("รูปพื้นหลัง").setValue(imageID);

        for(Integer i = 1;i <= floor.size();i++){
            DatabaseReference currentFloor = room.child("ชั้นที่ "+i);
            String totalRoom = floor.get(i-1);
            Integer total = Integer.parseInt(totalRoom);
            for (Integer j = 1;j<=total;j++){
                if(j < 10){
                    String r = i+"0"+ j.toString();
                    roomFirebase(currentFloor,r,i.toString());
                }else {
                    String r = i+ j.toString();
                    roomFirebase(currentFloor,r,i.toString());
                }
            }
        }
    }

    private void roomFirebase(DatabaseReference databaseReference,String number,String floor){
        DatabaseReference room = databaseReference.child(number);
        DatabaseReference roomInfo = room.child("รายละเอียด");
        DatabaseReference userInfo = room.child("ผู้เช่า");

        roomInfo.child("หมายเลขห้อง").setValue(number);
        roomInfo.child("ชั้น").setValue(floor);
        roomInfo.child("ประเภท").setValue("ไม่ระบุ");
        roomInfo.child("สถานะ").setValue("ว่าง");

        userInfo.child("ชื่อ").setValue("");
        userInfo.child("นามสกุล").setValue("");
        userInfo.child("ชื่อเล่น").setValue("");
        userInfo.child("เพศ").setValue("");
        userInfo.child("อายุ").setValue("");
        userInfo.child("เลขบัตรประชาชน").setValue("");
        userInfo.child("เบอร์โทร").setValue("");

    }

    private void setImageSize(ImageView imageView, int res) {
        Integer x = point.x;
        //Double w = Double.parseDouble(x.toString())/3;
        Integer wi = x / 3;
        //imageView.setMinimumHeight(wi);
        //imageView.setMinimumWidth(wi);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.getLayoutParams().height = wi;
        imageView.getLayoutParams().width = wi;


        //imageView.setMaxWidth(wi);
        //imageView.setMaxHeight(wi);

        imageView.setImageBitmap(getImage(res));
        //imageView.setImageDrawable(getResources().getDrawable(res));
        //Log.e("abc",wi.toString());
    }

    private void setLayoutParam(RelativeLayout layout, Integer i) {
        layout.getLayoutParams().width = i;
        layout.getLayoutParams().height = i;
    }

    private void checkp(int ID) {
        if (ID == position) {
            clear();
            position = -1;
        } else if (position < 0) {
            checkedImage(ID);
        } else {
            clear();
            checkedImage(ID);
        }
    }

    private void clear() {
        l1.setVisibility(View.GONE);
        l2.setVisibility(View.GONE);
        l3.setVisibility(View.GONE);
        l4.setVisibility(View.GONE);
        l5.setVisibility(View.GONE);
        l6.setVisibility(View.GONE);
        l7.setVisibility(View.GONE);
        l8.setVisibility(View.GONE);
        l9.setVisibility(View.GONE);
    }

    private void checkedImage(int ID) {
        position = ID;
        if (ID == 0) {
            l1.setVisibility(View.VISIBLE);
        } else if (ID == 1) {
            l2.setVisibility(View.VISIBLE);
        } else if (ID == 2) {
            l3.setVisibility(View.VISIBLE);
        } else if (ID == 3) {
            l4.setVisibility(View.VISIBLE);
        } else if (ID == 4) {
            l5.setVisibility(View.VISIBLE);
        } else if (ID == 5) {
            l6.setVisibility(View.VISIBLE);
        } else if (ID == 6) {
            l7.setVisibility(View.VISIBLE);
        } else if (ID == 7) {
            l8.setVisibility(View.VISIBLE);
        } else if (ID == 8) {
            l9.setVisibility(View.VISIBLE);
        } else {
            position = -1;
            clear();
        }
    }

    private Bitmap getImage(int res) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), res);
        double con;
        if (bitmap.getWidth() > bitmap.getHeight()) {
            con = getSize(bitmap.getWidth());
        } else {
            con = getSize(bitmap.getHeight());
        }
        Double wd = bitmap.getWidth() / con;
        Double hd = bitmap.getHeight() / con;

        return Bitmap.createScaledBitmap(bitmap, wd.intValue(), hd.intValue(), true);
    }

    private double getSize(int size) {
        if (size > 200) {
            return size / 200;
        } else {
            return size;
        }
    }


}

