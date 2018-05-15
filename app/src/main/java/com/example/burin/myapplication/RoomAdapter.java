package com.example.burin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list, parent, false);
        context = parent.getContext();

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        //holder.textView.setText("ชั้นที่ " + (position+1));

        holder.textView.setText(data.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail.class);
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

        public Viewholder(View itemView) {
            super(itemView);
            this.cardView = (CardView) itemView.findViewById(R.id.main_card_list);

            this.textView = (TextView) itemView.findViewById(R.id.TextView_Card_List);
        }
    }
}
