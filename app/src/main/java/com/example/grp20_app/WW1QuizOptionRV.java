package com.example.grp20_app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class WW1QuizOptionRV extends RecyclerView.Adapter<WW1QuizOptionRV.ViewHolder> {
    FragmentManager fragmentManager;
    private ArrayList<Pair<Integer, String>> options;
    int qNr;
    int currXp;
    int currScore;
    int currStrike;
    Context context;
    WW1QuizOptionRV(FragmentManager fragmentManager,ArrayList<Pair<Integer,String>> quizzes, int questionNr, int currXp, int currScore, int currStrike){
        this.fragmentManager = fragmentManager;
        this.options = quizzes;
        qNr = questionNr;
        this.currXp = currXp;
        this.currScore = currScore;
        this.currStrike = currStrike;

    }

    @NonNull
    @Override
    public WW1QuizOptionRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ww1_quiz_btn, viewGroup, false);
        context = viewGroup.getContext();
        return new WW1QuizOptionRV.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Button btn = viewHolder.optionBtn;
        btn.setText(options.get(i).second);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(options.get(i).first == 1){
                    currScore += 100;
                    currXp += (int) (100.f * (1.f + ((float)currStrike/10.f)));
                    currStrike +=1;
                }
                else{
                    currStrike = 0;
                    //wrong answer
                    //Give 0 points and no xp
                }
                Bundle b = new Bundle();
                b.putSerializable("quiz", WW1Quiz.getBuildUpQuiz(context));
                ArrayList<Integer> quizStuff = new ArrayList<>();
                quizStuff.add(qNr + 1); //Quiz Nr
                quizStuff.add(currXp); //CurrXp
                quizStuff.add(currXp); //Score
                quizStuff.add(0); //Strike
                b.putSerializable("q_nr", quizStuff);
                WW1QuizFragment ww1QuizFragment = new WW1QuizFragment();
                ww1QuizFragment.setArguments(b);
                fragmentManager.beginTransaction()
                        .replace(R.id.wikifrag, ww1QuizFragment).addToBackStack(null)
                        .commit();
            }
        });

        /*
        * If onclick option has option.get(i).first > 0, then points!!
        *
        * */
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button optionBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            optionBtn = itemView.findViewById(R.id.answer1);
        }
    }
}
