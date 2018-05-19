package com.example.burin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.Viewholder> {

    Context context;
    ArrayList<String> data = new ArrayList<>();
    String name;
    String floor;

    public RoomAdapter(ArrayList<String> data, String name, String floor) {
        this.data = data;
        this.name = name;
        this.floor = floor;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_room, parent, false);
        context = parent.getContext();

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {
        //holder.textView.setText("ชั้นที่ " + (position+1));

        holder.textView.setText("ห้องหมายเลข "+data.get(position));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference roomRef = databaseReference.child("หอพัก").child(name).child("ห้องพัก").child(floor).child(data.get(position));

        DatabaseReference roomNameRef = roomRef.child("ผู้เช่า").child("name");
        DatabaseReference roomStatusRef = roomRef.child("รายละเอียด").child("status");


        roomNameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue() != null){
                    String nameRoom = dataSnapshot.getValue().toString();
                    holder.nameText.setText(nameRoom);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        roomStatusRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    String status = dataSnapshot.getValue().toString();
                    if(status.contentEquals("ว่าง")){
                        holder.iconArea.setCardBackgroundColor(context.getResources().getColor(R.color.colorGreen400));
                        holder.nameText.setText("ว่าง");
                    }else if (status.contentEquals("เช่าปกติ")){
                        holder.iconArea.setCardBackgroundColor(context.getResources().getColor(R.color.colorYellow600));
                    }else {
                        holder.iconArea.setCardBackgroundColor(context.getResources().getColor(R.color.colorRed400));
                        holder.nameText.setText("ชำรุด");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail.class);
                intent.putExtra("NAME",name);
                intent.putExtra("ROOM",data.get(position));
                intent.putExtra("FLOOR",floor);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;
        CardView iconArea;
        TextView nameText;

        public Viewholder(View itemView) {
            super(itemView);
            this.cardView = (CardView) itemView.findViewById(R.id.main_card_list);

            this.textView = (TextView) itemView.findViewById(R.id.TextView_Card_List);
            this.nameText = (TextView) itemView.findViewById(R.id.card_list_name_text);
            this.iconArea = (CardView)itemView.findViewById(R.id.card_list_icon_card);
        }
    }
}
