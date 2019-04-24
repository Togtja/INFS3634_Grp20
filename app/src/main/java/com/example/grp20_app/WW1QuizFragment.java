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
            //You finished the quiz, so we save your progress
            MainActivity.GLOBAL_PROFILE.saveData(getContext());
            WW1QuizListFragment ww1QuizListFragment = new WW1QuizListFragment();
            getFragmentManager().beginTransaction()
                    .replace(R.id.wikifrag, ww1QuizListFragment)
                    .commit();
        }
        ImageView imageView = view.findViewById(R.id.imageView);
        //There a bug here that makes it display a null photo (or rather a null photo isn't null)
        if(quiz.get(qStuff.get(0)) != null){
            Log.d("PHOTO", "Is not gone");
            imageView.setImageBitmap(quiz.get(qStuff.get(0)).getImage());
        }
        else{
            Log.d("PHOTO", "Is it gone");
            imageView.setVisibility(View.GONE);
        }
        questions.setText(quiz.get(qStuff.get(0)).getQuestion());
        score = view.findViewById(R.id.text_score);
        score.setText("Score: " + qStuff.get(2));
        TextView q_num = view.findViewById(R.id.question_number);
        q_num.setText(Integer.toString(qStuff.get(0)+ 1));
        options = view.findViewById(R.id.quiz_buttons_rv);
        options.setHasFixedSize(true);

        options.setLayoutManager(new GridLayoutManager(getContext(), 1));

        mAdapter = new WW1QuizOptionRV(getFragmentManager(),quiz.get(qStuff.get(0)).getAnswer(), qStuff.get(0), qStuff.get(2), qStuff.get(3), userView);
        options.setAdapter(mAdapter);
        return view;
    }

    //This changes/updates the profile UI that lives in the quiz fragments
    //Remember to call this after any profile changes has been made
    //Do not call it with a view that don't hold the ww1_user_stuff.xml
    public static void UserSetup(View userView){
        WW1UserProfile profile = MainActivity.GLOBAL_PROFILE;

        Log.d("Profile xp", String.valueOf(profile.getCurrXP()));
        //Username
        TextView name = userView.findViewById(R.id.userName);
        if(name == null){
            //We are calling this in the wrong layout
            //Bad Coding!!
            Log.d("BAD CODING!","We are being called in the wrong Layout");
            return;
        }
        name.setText(profile.getUserName());
        //Title
        TextView title = userView.findViewById(R.id.title);
        title.setText(profile.getTitle());
        //Lvl
        TextView level = userView.findViewById(R.id.user_level);
        level.setText("Level: " + profile.getLvl());
        //XP Text
        TextView xpText = userView.findViewById(R.id.text_XP);
        xpText.setText(profile.getCurrXP() + "/" + profile.getNextlvlXP() + " XP");
        //ProgressBarPercentage
        ProgressBar progressBar = userView.findViewById(R.id.progressBar);
        progressBar.setProgress(profile.getCurrXP());
        progressBar.setMax(profile.getNextlvlXP());
        //ProfilePhoto
        ImageView profilePhoto = userView.findViewById(R.id.profilePhoto);
        if(profile.getProfilePhotoString() != null){
            profilePhoto.setImageURI(Uri.parse(profile.getProfilePhotoString()));
        }
    }
}
