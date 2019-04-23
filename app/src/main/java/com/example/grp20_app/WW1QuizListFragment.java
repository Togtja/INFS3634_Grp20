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

public class WW1QuizListFragment extends Fragment {
    RecyclerView quizRecycler;
    WW1QuizRecyclerView mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.ww1_quiz_fragment, container, false);

        FrameLayout frame = view.findViewById(R.id.ww1_quizlist_user);
        View userView = LayoutInflater.from(getContext()).inflate(R.layout.ww1_user_stuff, frame, true);
        UserSetup(userView);



        quizRecycler = view.findViewById(R.id.ww1_quizlist_rv);
        quizRecycler.setHasFixedSize(true);

        quizRecycler.setLayoutManager(new GridLayoutManager(getContext(), 1));

        //Sends in the list of available quizzes
        ArrayList<String> test_list = new ArrayList<>();
        test_list.add("Quiz About 1914");
        test_list.add("Quiz About 1915");
        mAdapter = new WW1QuizRecyclerView(getFragmentManager(), test_list);

        quizRecycler.setAdapter(mAdapter);

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
            Log.d("Uri" , profile.getProfilePhotoString());
            profilePhoto.setImageURI(Uri.parse(profile.getProfilePhotoString()));
        }
    }
}

