package com.example.grp20_app;

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
import java.util.List;

public class WW1QuizFragment extends Fragment {
    TextView score;
    RecyclerView options;
    WW1QuizOptionRV mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.multiple_choice2, container, false);

        Bundle bundle = getArguments();
        if (bundle == null) {return null;}
        FrameLayout frame = view.findViewById(R.id.scoreboard);
        View userView = LayoutInflater.from(getContext()).inflate(R.layout.ww1_user_stuff, frame, true);
        UserSetup(userView);

        ArrayList<WW1Quiz> quiz = (ArrayList<WW1Quiz>) bundle.getSerializable("quiz");
        ArrayList<Integer> qStuff = (ArrayList<Integer>) bundle.getSerializable("q_nr");
        /*
                quizStuff(0); //Quiz Nr
                quizStuff(1); //CurrXp
                quizStuff(2); //Score
                quizStuff(3); //Strike
         */

        TextView questions =  view.findViewById(R.id.quiz_question);
        if(qStuff.get(0) >= quiz.size()){
            return null;
        }
        ImageView imageView = view.findViewById(R.id.imageView);
        if(quiz.get(qStuff.get(0)) != null){
            imageView.setImageBitmap(quiz.get(qStuff.get(0)).getImage());
        }
        else{
            imageView.setVisibility(View.GONE);
        }
        questions.setText(quiz.get(qStuff.get(0)).getQuestion());
        score = view.findViewById(R.id.text_score);
        score.setText("Score: " +Integer.toString(qStuff.get(2)));
        TextView q_num = view.findViewById(R.id.question_number);
        q_num.setText(Integer.toString(qStuff.get(0)+ 1));
        options = view.findViewById(R.id.quiz_buttons_rv);
        options.setHasFixedSize(true);

        options.setLayoutManager(new GridLayoutManager(getContext(), 1));

        mAdapter = new WW1QuizOptionRV(getFragmentManager(),quiz.get(qStuff.get(0)).getAnswer(), qStuff.get(0), qStuff.get(1), qStuff.get(2), qStuff.get(3));
        options.setAdapter(mAdapter);
        return view;
    }
    private void UserSetup(View userView){
        WW1UserProfile profile = MainActivity.GLOBAL_PROFILE;
        //Username
        TextView name = userView.findViewById(R.id.userName);
        name.setText(profile.getUserName());
        //Title
        TextView title = userView.findViewById(R.id.title);
        title.setText(profile.getTitle());
        //Lvl
        TextView level = userView.findViewById(R.id.user_level);
        level.setText("Level: " + Integer.toString(profile.getLvl()));
        //XP Text
        TextView xpText = userView.findViewById(R.id.text_XP);
        xpText.setText(Integer.toString(profile.getCurrXP()) + "/" + Integer.toString(profile.getNextlvlXP()) + " XP");
        //ProgressBarPercentage
        ProgressBar progressBar = userView.findViewById(R.id.progressBar);
        progressBar.setProgress(profile.getCurrXP());
        progressBar.setMax(profile.getNextlvlXP());
        //ProfilePhoto
        ImageView profilePhoto = userView.findViewById(R.id.profilePhoto);
        if(profile.getProfilePhotoString() == null){
            //Set profile photo bases on image
        }
        else{
            profilePhoto.setImageURI(Uri.parse(profile.getProfilePhotoString()));
        }
    }
}
