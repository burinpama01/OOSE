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

    Context context;
    ArrayList<String> data = new ArrayList<>();

    public Adapter(ArrayList<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_floor, parent, false);
        context = parent.getContext();

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.textView.setText("ชั้นที่ " + (position+1));


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;

        public Viewholder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.TextView_Card_Floor);
            this.cardView = (CardView)itemView.findViewById(R.id.Card_Floor);
        }
    }
}
