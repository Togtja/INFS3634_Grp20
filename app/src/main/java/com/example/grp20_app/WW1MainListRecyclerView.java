package com.example.grp20_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WW1MainListRecyclerView extends RecyclerView.Adapter<WW1MainListRecyclerView.ViewHolder> {
    private  ArrayList<Pair<String,Integer>> arrayPair;
    private FragmentManager fragmentManager;
    ArrayList<ArrayList<String>> wikiSites;

    public WW1MainListRecyclerView(FragmentManager fragmentManager, ArrayList<Pair<String, Integer>> arrayPair, ArrayList<ArrayList<String>> wikiSites) {
        this.fragmentManager = fragmentManager;
        this.arrayPair = arrayPair;
        this.wikiSites = wikiSites;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ww1_mainpage, viewGroup, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(arrayPair.get(i).first);
        viewHolder.image.setImageResource(arrayPair.get(i).second);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //This means it is quiz
                if(i == wikiSites.size()){
                    WW1QuizFragment ww1QuizFragment = new WW1QuizFragment();
                    //Make bundle if we need to

                    fragmentManager.beginTransaction()
                            .replace(R.id.wikifrag, ww1QuizFragment).addToBackStack(null)
                            .commit();
                }
                else {
                    final WW1SubListFragment ww1SubListFragment = new WW1SubListFragment();
                    final Bundle b = new Bundle();
                    b.putSerializable("wiki", wikiSites.get(i));
                    ww1SubListFragment.setArguments(b);
                    fragmentManager.beginTransaction()
                            .replace(R.id.wikifrag, ww1SubListFragment).addToBackStack(null)
                            .commit();
                }

            }

        });

            /*
            try {
                wikiPages.add(call.execute().body());
            } catch (IOException e) {
                e.printStackTrace();
            }*/


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
