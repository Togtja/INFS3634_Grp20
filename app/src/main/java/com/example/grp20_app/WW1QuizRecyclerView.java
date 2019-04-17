package com.example.grp20_app;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WW1QuizRecyclerView extends RecyclerView.Adapter<WW1QuizRecyclerView.ViewHolder> {
    private ArrayList<String> quizes;
    WW1QuizRecyclerView(ArrayList<String> quizes){
        this.quizes = quizes;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ww1_quizlist, viewGroup, false);
        return new WW1QuizRecyclerView.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.text.setText(quizes.get(i));
    }

    @Override
    public int getItemCount() {
        return quizes.size();
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
