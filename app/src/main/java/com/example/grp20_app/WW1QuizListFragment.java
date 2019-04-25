package com.example.grp20_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class WW1QuizListFragment extends Fragment {
    RecyclerView quizRecycler;
    WW1QuizRecyclerView mAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);

        //FrameLayout frame = view.findViewById(R.id.ww1_quizlist_user);
        //View userView = LayoutInflater.from(getContext()).inflate(R.layout.ww1_user_stuff, frame, true);
        //WW1QuizActivity.UserSetup(userView);


        quizRecycler = view.findViewById(R.id.ww1_main_rv);
        quizRecycler.setHasFixedSize(true);

        quizRecycler.setLayoutManager(new GridLayoutManager(getContext(), 1));

        //Sends in the list of available quizzes
        ArrayList<String> test_list = new ArrayList<>();
        test_list.add("Quiz About The Causes and Buildup");
        test_list.add("Quiz About 1914");
        test_list.add("Quiz About 1915");
        test_list.add("Quiz About 1916");
        test_list.add("Quiz About 1917");
        test_list.add("Quiz About 1918");
        test_list.add("Quiz About The Aftermath");
        mAdapter = new WW1QuizRecyclerView(getFragmentManager(), test_list);

        quizRecycler.setAdapter(mAdapter);
        return view;
    }
}
