package com.example.burin.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FloorListActivity extends AppCompatActivity {

    ArrayList<String> floor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floor_list);

        floor = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("NAME").toString();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference floorRef = databaseReference.child("หอพัก").child(name).child("ห้องพัก");

        final RecyclerView RecyclerFloorList = (RecyclerView)findViewById(R.id.RecyclerView_FloorList);
        RecyclerFloorList.setLayoutManager(new LinearLayoutManager(this));
        RecyclerFloorList.setAdapter(new FloorListAdapter(name,new ArrayList<String>()));

        floorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                floor.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    floor.add(ds.getKey());
                }

                RecyclerFloorList.setAdapter(new FloorListAdapter(name,floor));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
