package com.example.burin.myapplication;

import android.app.Activity;
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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Viewholder>{

    private ArrayList<String> data;

    private Context context;
    private RegisterDromeActivity activity;

    public SearchAdapter(ArrayList<String> data, RegisterDromeActivity activity,Boolean ready) {
        this.data = data;
        this.activity = activity;

        if(data.size() == 0 && ready){
            activity.empty.setVisibility(View.VISIBLE);
            activity.pro.setVisibility(View.GONE);
        }
        else if(data.size()>0){
            activity.empty.setVisibility(View.GONE);
            activity.pro.setVisibility(View.GONE);
        }
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loca_card,parent,false);

        this.context = parent.getContext();

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int position) {

        holder.textView.setText("หอพัก "+data.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main.class);
                intent.putExtra("NAME", data.get(position));
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });

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
            this.textView = (TextView) itemView.findViewById(R.id.loca_card_text);
            this.cardView = (CardView)itemView.findViewById(R.id.loca_card_card);
        }
    }

}
