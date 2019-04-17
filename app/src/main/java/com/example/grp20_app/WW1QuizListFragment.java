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

import java.util.ArrayList;

public class WW1QuizListFragment extends Fragment {
    RecyclerView quizRecycler;
    WW1QuizRecyclerView mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.ww1_quiz_fragment, container, false);


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
}
