package com.example.grp20_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WW1QuizFragment extends Fragment {
    TextView score;
    RecyclerView options;
    WW1QuizOptionRV mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.multiple_choice, container, false);

        Bundle bundle = getArguments();
        if (bundle == null) {return null;}
        ArrayList<WW1Quiz> quiz = (ArrayList<WW1Quiz>) bundle.getSerializable("quiz");
        ArrayList<Integer> qStuff = (ArrayList<Integer>) bundle.getSerializable("q_nr");
        /*
                quizStuff(0); //Quiz Nr
                quizStuff(1); //CurrXp
                quizStuff(2); //Score
                quizStuff(3); //Strike
         */

        TextView questions =  view.findViewById(R.id.quiz_question);
        if(questions == null){
            Log.d("Null?", "Ummmm, how?");
        }
        if(qStuff.get(0) >= quiz.size()){
            return null;
        }
        ImageView imageView = view.findViewById(R.id.imageView);
        if(quiz.get(qStuff.get(0)) != null){
            imageView.setImageBitmap(quiz.get(qStuff.get(0)).getImage());
        }
        else{
            imageView.setVisibility(View.INVISIBLE);
        }
        questions.setText(quiz.get(qStuff.get(0)).getQuestion());
        score = view.findViewById(R.id.score);
        score.setText(Integer.toString(qStuff.get(2)));
        TextView q_num = view.findViewById(R.id.question_number);
        q_num.setText(Integer.toString(qStuff.get(0)+ 1));
        options = view.findViewById(R.id.quiz_buttons_rv);
        options.setHasFixedSize(true);

        options.setLayoutManager(new GridLayoutManager(getContext(), 1));

        mAdapter = new WW1QuizOptionRV(getFragmentManager(),quiz.get(qStuff.get(0)).getAnswer(), qStuff.get(0), qStuff.get(1), qStuff.get(2), qStuff.get(3));
        options.setAdapter(mAdapter);
        return view;
    }
}
