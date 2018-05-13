package com.example.burin.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class FloorListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floor_list);

        RecyclerView RecyclerFloorList = (RecyclerView)findViewById(R.id.RecyclerView_FloorList);
        RecyclerFloorList.setLayoutManager(new LinearLayoutManager(this));
        RecyclerFloorList.setAdapter(new FloorListAdapter());
    }

}
