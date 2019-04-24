package com.example.grp20_app;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

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
    View progressBarView; //Takes in the userView which updates the stats of the user
    int quizArray; //The id of the array of question we use
    WW1QuizOptionRV(FragmentManager fragmentManager,ArrayList<Pair<Integer,String>> quizzes, int questionNr, int currScore, int currStrike, int quizArray, View userView){
        this.fragmentManager = fragmentManager;
        this.options = quizzes;
        qNr = questionNr;
        this.currScore = currScore;
        this.currStrike = currStrike;
        this.progressBarView = userView;
        this.quizArray = quizArray;

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
        //To do set this up as a async task, and just let main show a loading bar
        Button btn = viewHolder.optionBtn;
        btn.setText(options.get(i).second);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                ProgressBar p = progressBarView.findViewById(R.id.progressBar_MC);
                RecyclerView rv = progressBarView.findViewById(R.id.quiz_buttons_rv);
                p.setVisibility(View.VISIBLE);
                rv.setVisibility(View.INVISIBLE);
                new LoadQuestion().execute(i);

            }
        });
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
    private class LoadQuestion extends AsyncTask<Integer, Void, WW1QuizFragment> {
        protected WW1QuizFragment doInBackground(Integer... ints) {
            if (options.get(ints[0]).first == 1) {
                currScore += 100;
                MainActivity.GLOBAL_PROFILE.addCurrXP(100 + Math.round((100.f * (currStrike / 10.f))));
                currStrike += 1;
                // WW1QuizFragment.UserSetup(userView);
            } else {
                currStrike = 0;
                //wrong answer
                //Give 0 points and no xp
            }

            Bundle b = new Bundle();
            ArrayList<Integer> quizStuff = new ArrayList<>();
            quizStuff.add(qNr + 1); //Quiz Nr
            quizStuff.add(currScore); //Score
            quizStuff.add(currStrike); //Strike
            quizStuff.add(quizArray);
            b.putSerializable("q_nr", quizStuff);
            WW1QuizFragment ww1QuizFragment = new WW1QuizFragment();
            ww1QuizFragment.setArguments(b);
            return  ww1QuizFragment;
        }

        protected void onPostExecute(WW1QuizFragment result) {
            fragmentManager.beginTransaction()
                    .replace(R.id.wikifrag, result)
                    .commit();
        }
    }

}
