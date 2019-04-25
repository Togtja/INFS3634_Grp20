package com.example.grp20_app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WW1QuizRecyclerView extends RecyclerView.Adapter<WW1QuizRecyclerView.ViewHolder> {
    private FragmentManager fragmentManager;
    private ArrayList<String> quizzes;
    private WW1UserProfile user;

    Context context;
    WW1QuizRecyclerView(FragmentManager fragmentManager, ArrayList<String> quizzes/*, WW1UserProfile user*/){
        this.fragmentManager = fragmentManager;
        this.quizzes = quizzes;
        //this.user = user;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ww1_quizlist, viewGroup, false);
        context = viewGroup.getContext();
        return new WW1QuizRecyclerView.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.text.setText(quizzes.get(i));
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Send you to the correct quiz
                Bundle b = new Bundle();
                ArrayList<Integer> quizStuff = new ArrayList<>();
                quizStuff.add(0); //Quiz Nr
                quizStuff.add(0); //Score
                quizStuff.add(0); //Strike
                quizStuff.add(i); //ID for the quiz (0 is build up 1 is 1914 etc)

                b.putSerializable("q_nr", quizStuff);
                WW1QuizFragment ww1QuizFragment = new WW1QuizFragment();
                ww1QuizFragment.setArguments(b);
                fragmentManager.beginTransaction()
                        .replace(R.id.wikifrag, ww1QuizFragment, "quiz")
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.quizList_text);
            card = itemView.findViewById(R.id.quizList_cv);
        }
    }
}
