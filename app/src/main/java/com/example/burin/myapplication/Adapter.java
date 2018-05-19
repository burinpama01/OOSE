package com.example.burin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.time.Instant;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {

    //Hello

    Context context;
    ArrayList<String> data = new ArrayList<>();

    AddFloorActivity activity;

    public Adapter(ArrayList<String> data, AddFloorActivity activity) {
        this.data = data;
        this.activity = activity;

        if (data.size() == 0) {
            activity.empty.setVisibility(View.VISIBLE);
        } else {
            activity.empty.setVisibility(View.GONE);
        }
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_floor_new, parent, false);
        context = parent.getContext();

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.textView.setText("ชั้นที่ " + (position + 1));
        holder.des.setText("จำนวน " + activity.floor.get(position) + " ชั้น");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;

        TextView des;

        public Viewholder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.TextView_Card_Floor);
            this.cardView = (CardView) itemView.findViewById(R.id.Card_Floor);
            this.des = (TextView) itemView.findViewById(R.id.TextView_Card_Floor_des);

        }
    }
}
