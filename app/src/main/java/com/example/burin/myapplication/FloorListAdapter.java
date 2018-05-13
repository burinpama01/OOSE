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

public class FloorListAdapter extends RecyclerView.Adapter<FloorListAdapter.ViewHolder>{

    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_floor,parent,false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewCardFloor.setText("ชั้นที่ "+(position+1));
        holder.cardViewFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MainList.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardViewFloor;
        public TextView textViewCardFloor;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewCardFloor = (TextView)itemView.findViewById(R.id.TextView_Card_Floor);
            this.cardViewFloor = (CardView)itemView.findViewById(R.id.Card_Floor);

        }
    }
}
