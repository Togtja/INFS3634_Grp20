package com.example.grp20_app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/*
* Displays the different quiz options
* Then check what the user selected
* Give points and updates the UI
* Then call the next question
* */

public class WW1QuizOptionRV extends RecyclerView.Adapter<WW1QuizOptionRV.ViewHolder> {
    FragmentManager fragmentManager;
    private ArrayList<Pair<Integer, String>> options;
    int qNr; //The Quiz Nr
    int currScore;//The current score for that quiz the user is taking
    int currStrike;//How many the user have managed to answer in a row
    Context context; //Need the context to build a quiz
    View userView; //Takes in the userView which updates the stats of the user
    WW1QuizOptionRV(FragmentManager fragmentManager,ArrayList<Pair<Integer,String>> quizzes, int questionNr, int currScore, int currStrike, View userView){
        this.fragmentManager = fragmentManager;
        this.options = quizzes;
        qNr = questionNr;
        this.currScore = currScore;
        this.currStrike = currStrike;
        this.userView = userView;

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
                //If multiple choice do something else


                //If the answer is correct give points else don't
                if(options.get(i).first == 1){
                    currScore += 100;
                    MainActivity.GLOBAL_PROFILE.addCurrXP(Math.round(100.f * (currStrike/10.f)));
                    currStrike +=1;
                    WW1QuizFragment.UserSetup(userView);
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
                quizStuff.add(MainActivity.GLOBAL_PROFILE.getCurrXP()); //CurrXp
                quizStuff.add(currScore); //Score
                quizStuff.add(currStrike); //Strike
                b.putSerializable("q_nr", quizStuff);
                WW1QuizFragment ww1QuizFragment = new WW1QuizFragment();
                ww1QuizFragment.setArguments(b);
                fragmentManager.beginTransaction()
                        .replace(R.id.wikifrag, ww1QuizFragment)
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
