package com.example.burin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainList extends AppCompatActivity {

    ArrayList<String> arrs;
    ArrayList<String> arrss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);

        Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("NAME").toString();
        final String floor = bundle.getString("FLOOR").toString();

        TextView title = (TextView)findViewById(R.id.main_list_title);
        title.setText(floor);

        arrs = new ArrayList<>();
        arrss = new ArrayList<>();

        final ArrayList<String> arr = new ArrayList<>();
        for (int i = 0 ;i<10;i++){
            arr.add("");
        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference roomRef = databaseReference.child("หอพัก").child(name).child("ห้องพัก").child(floor);

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerViewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RoomAdapter(new ArrayList<String>(),name,floor));

        roomRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    arrs.clear();
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        arrs.add(ds.getKey());
                    }

                    recyclerView.setAdapter(new RoomAdapter(arrs,name,floor));
                } else {
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ImageView backRoomList = (ImageView)findViewById(R.id.imageView_back_RoomList);
        backRoomList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
