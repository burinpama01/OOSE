package com.example.burin.myapplication;

import android.graphics.Point;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    //private ArrayList<String> data;
    private ArrayList<Integer> data;

    private PictureListActivity activity;

    private Integer size;

    //private Integer checkPosition = -1;

    private ViewHolder lastHolder;

    public ImageAdapter(PictureListActivity activity, ArrayList<Integer> data) {
        this.data = data;

        this.activity = activity;

        //checkPosition = activity.position;

        Point point = new Tools().getDisplaySize(activity);
        Integer x = point.x;
        size = x / 3;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_image, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.imageView.getLayoutParams().height = size;
        holder.imageView.getLayoutParams().width = size;

        holder.check.getLayoutParams().height = size;
        holder.check.getLayoutParams().width = size;

        Picasso.get().load(data.get(position)).centerCrop().resize(300, 300).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(activity.position == position){
                    activity.position = -1;
                    lastHolder.check.setVisibility(View.GONE);
                } else {
                    if(activity.position > -1){
                        holder.check.setVisibility(View.VISIBLE);
                        lastHolder.check.setVisibility(View.GONE);
                    } else {
                        holder.check.setVisibility(View.VISIBLE);
                    }
                    lastHolder = holder;
                    //holder.check.setVisibility(View.VISIBLE);
                    activity.position = position;
                }

                //setVisibility(position,holder);
            }
        });


    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public RelativeLayout check;

        public ViewHolder(View itemView) {
            super(itemView);

            this.imageView = (ImageView) itemView.findViewById(R.id.card_image_image_view);
            this.check = (RelativeLayout) itemView.findViewById(R.id.card_image_check_picture);
        }
    }
}
