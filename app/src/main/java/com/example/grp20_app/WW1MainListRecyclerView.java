package com.example.grp20_app;

import android.content.Intent;
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
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
/*
* This class takes in an Array of Pairs of Stings and Integers
* which the String represents a title and the int the drawable ID
* And takes in an Array of Array of Wikipedia titles which we use to do API calls
*
* Based on what CardView holder you click on from the UI
* We decided what gets sent to the SubListFragment and consequently get displayed there
*
* */

public class WW1MainListRecyclerView extends RecyclerView.Adapter<WW1MainListRecyclerView.ViewHolder> {

    static ArrayList<WikiPage> wikiPages = new ArrayList<>();
    static int wikiPageSize = 0;

    private  ArrayList<Pair<String,Integer>> arrayPair;
    private FragmentManager fragmentManager;
    private ArrayList<ArrayList<String>> wikiSites;
    private View viewQuiz;

    WW1MainListRecyclerView(FragmentManager fragmentManager, ArrayList<Pair<String, Integer>> arrayPair, ArrayList<ArrayList<String>> wikiSites) {
        this.fragmentManager = fragmentManager;
        this.arrayPair = arrayPair;
        this.wikiSites = wikiSites;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ww1_mainpage, viewGroup, false);
        viewQuiz = v;
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        viewHolder.name.setText(arrayPair.get(i).first);
        viewHolder.image.setImageResource(arrayPair.get(i).second);
        final int fin_i = i;
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //This means it is quiz
                if(fin_i == wikiSites.size()){
                    //Just opens the Quiz Fragment (Maybe make Activity)
                    viewQuiz.getContext().startActivity(new Intent(viewQuiz.getContext(), WW1QuizActivity.class));

                }
                else {
                    WW1SubListFragment ww1SubListFragment = new WW1SubListFragment();
                    //Bundle the Wikipages for that specific category
                    //E.g Bundle the 1917 wikipages to the 1917 category
                    Bundle b = new Bundle();
                    for (int j = 0; j < wikiPageSize; j++) {
                        if(wikiPages.get(j) == null){
                            continue;
                        }
                        if(wikiPages.get(j).getImage() != null){
                            File photo = new File(wikiPages.get(j).getImage());
                            if(photo.delete()){
                                //Debug
                            }
                            else{
                            }
                        }
                    }
                    wikiPages = new ArrayList<>();
                    for (int j = 0; j < wikiSites.get(fin_i).size(); j++) {
                        wikiPages.add(null);
                    }
                    wikiPageSize = wikiPages.size();
                    b.putSerializable("wiki", wikiSites.get(fin_i));
                    ww1SubListFragment.setArguments(b);
                    fragmentManager.beginTransaction()
                            .replace(R.id.wikifrag, ww1SubListFragment).addToBackStack(null)
                            .commit();
                }
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
