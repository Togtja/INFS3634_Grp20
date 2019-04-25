package com.example.grp20_app;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;

/*
* Displays the Quiz to the UI
* Then Call WW1QuizOptionRV recycler view to display the quiz options
*/

public class WW1QuizFragment extends Fragment {
    TextView score;
    RecyclerView options;
    WW1QuizOptionRV mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.multiple_choice2, container, false);

        Bundle bundle = getArguments();
        //if something when wrong with the bundle restart the app
        if (bundle == null) {startActivity(new Intent(getContext(), MainActivity.class));}
        //UserSetup(userView);




        ArrayList<Integer> qStuff = (ArrayList<Integer>) bundle.getSerializable("q_nr");
        /*
                quizStuff(0); //Quiz Nr
                quizStuff(1); //Score
                quizStuff(2); //Strike
                quizStuff(3); //What Quiz to get
        */
        ArrayList<WW1Quiz> quiz;
        switch (qStuff.get(3)){
            case 0:
                quiz = WW1Quiz.getBuildUpQuiz(getContext());
                break;
            case 1:
                quiz = WW1Quiz.get1914Quiz(getContext());
                break;
            case 2:
                quiz = WW1Quiz.get1915Quiz(getContext());
                break;
            case 3:
                quiz = WW1Quiz.get1916Quiz(getContext());
                break;
            case 4:
                quiz = WW1Quiz.get1917Quiz(getContext());
                break;
            case 5:
                quiz = WW1Quiz.get1918Quiz(getContext());
                break;
            case 6:
                quiz = WW1Quiz.getAfterMathQuiz(getContext());
                break;
            default:
                Log.d("Size", "onCreateView: WE FUECK UP");
                return null;
        }
        Log.d("Quiz", "index " + qStuff.get(0) + " size of quiz: " + quiz.size());
        TextView questions =  view.findViewById(R.id.quiz_question);
        if(qStuff.get(0) >= quiz.size()){
            //You finished the quiz, so we save your progress
            MainActivity.GLOBAL_PROFILE.saveData(getContext());
            Log.d("Quiz", "onCreateView: Go to Quiz activity");
            WW1QuizListFragment quizListFragment = new WW1QuizListFragment();
            getFragmentManager().beginTransaction()
                    .replace(R.id.wikifrag, quizListFragment)
                    .commit();
            //I'll be honest I don't know why I need to return since I though I am replacing the view but whatever
            //this does give a "E/RecyclerView: No adapter attached; skipping layout" Error, but no time to fix
            return view;

        }
        ImageView imageView = view.findViewById(R.id.imageView);
        //There a bug here that makes it display a null photo (or rather a null photo isn't null)
        imageView.setVisibility(View.GONE);

        if(quiz.get(qStuff.get(0)).getImage() != null){
            imageView.setImageBitmap(quiz.get(qStuff.get(0)).getImage());
            imageView.setVisibility(View.VISIBLE);
        }
        questions.setText(quiz.get(qStuff.get(0)).getQuestion());
        score = view.findViewById(R.id.text_score);
        score.setText("Score: " + qStuff.get(1));
        TextView q_num = view.findViewById(R.id.question_number);
        q_num.setText(Integer.toString(qStuff.get(0)+ 1));
        options = view.findViewById(R.id.quiz_buttons_rv);
        options.setHasFixedSize(true);

        options.setLayoutManager(new GridLayoutManager(getContext(), 1));

        mAdapter = new WW1QuizOptionRV(getFragmentManager(),quiz.get(qStuff.get(0)).getAnswer(), qStuff.get(0), qStuff.get(1), qStuff.get(2), qStuff.get(3), view);
        options.setAdapter(mAdapter);
        return view;
    }


}
