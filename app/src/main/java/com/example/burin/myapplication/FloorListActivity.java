package com.example.burin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FloorListActivity extends AppCompatActivity {

    ArrayList<String> floor;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_list);

        floor = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("NAME").toString();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference floorRef = databaseReference.child("หอพัก").child(name).child("ห้องพัก");

        final RecyclerView RecyclerFloorList = (RecyclerView)findViewById(R.id.RecyclerView_FloorList);
        RecyclerFloorList.setLayoutManager(new LinearLayoutManager(this));
        RecyclerFloorList.setAdapter(new FloorListAdapter(name,new ArrayList<String>()));

        ImageView back = (ImageView)findViewById(R.id.floor_list_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        floorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    floor.clear();

                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        floor.add(ds.getKey());
                    }

                    RecyclerFloorList.setAdapter(new FloorListAdapter(name,floor));
                }else {
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ImageView delete = (ImageView)findViewById(R.id.floor_action_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogDeletes();

            }
        });

    }

    public void showdialogDeletes() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_delete, null);
        dialog.setView(dialogView);
        TextView textView = (TextView) dialogView.findViewById(R.id.dialog_delete_text);
        textView.setText("คุณต้องการลบหอพัก " + name);
        //dialog.setTitle("ลบชั้นที่ " + floor.size());
        //dialog.setMessage("ต้องการลบชั้น");

        final AlertDialog abc = dialog.create();
        abc.show();

        CardView cancel = (CardView) dialogView.findViewById(R.id.dialog_delete_cancel);
        CardView confirm = (CardView) dialogView.findViewById(R.id.dialog_delete_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference data = FirebaseDatabase.getInstance().getReference();
                DatabaseReference ref = data.child("หอพัก").child(name);
                ref.removeValue();
                finish();
                abc.cancel();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abc.cancel();
            }
        });
    }

}
