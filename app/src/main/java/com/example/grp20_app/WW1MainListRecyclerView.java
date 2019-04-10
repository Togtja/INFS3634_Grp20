package com.example.grp20_app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WW1MainListRecyclerView extends RecyclerView.Adapter<WW1MainListRecyclerView.ViewHolder> {
    private  ArrayList<Pair<String,Integer>> arrayPair;
    private Context context;
    public WW1MainListRecyclerView(ArrayList<Pair<String, Integer>> arrayPair) {
        this.arrayPair = arrayPair;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ww1_mainpage, viewGroup, false);
        context = viewGroup.getContext();
        //WW1MainListRecyclerView.ViewHolder vh = new WW1MainListRecyclerView.ViewHolder(v);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(arrayPair.get(i).first);
        viewHolder.image.setImageResource(arrayPair.get(i).second);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open new Activity
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayPair.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ww1_cvtxt);
            image = itemView.findViewById(R.id.ww1_cvimg);
            cardView = itemView.findViewById(R.id.ww1_cvCard);
        }
    }
}
