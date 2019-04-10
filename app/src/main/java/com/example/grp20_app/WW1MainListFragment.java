package com.example.grp20_app;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.grp20_app.R;

import java.util.ArrayList;

public class WW1MainListFragment extends Fragment {
    RecyclerView ww1MainList;
    private WW1MainListRecyclerView mAdapter;
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ww1_main_list_view, container, false);
        ww1MainList = view.findViewById(R.id.ww1_main_rv);
        ww1MainList.setHasFixedSize(true);

        ww1MainList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ArrayList<Pair<String, Integer>> list = new ArrayList<>();
        //Make the list
        String s = "Build up"; Integer i = R.drawable.ww1start;
        list.add(new Pair<>(s,i));
        s = "1914"; i = R.drawable.archyduke;
        list.add(new Pair<>(s,i));
        s = "1915"; i = R.drawable.capehelles;
        list.add(new Pair<>(s,i));
        s = "1916"; i = R.drawable.ww1h;
        list.add(new Pair<>(s,i));
        s = "1917"; i = R.drawable.ww13;
        list.add(new Pair<>(s,i));
        s = "1918"; i = R.drawable.ww2end;
        list.add(new Pair<>(s,i));
        s = "After Match"; i = R.drawable.peaceww1;
        list.add(new Pair<>(s,i));
        s = "Quiz"; i = R.drawable.quizicon;
        list.add(new Pair<>(s,i));
        mAdapter = new WW1MainListRecyclerView(list);

        ww1MainList.setAdapter(mAdapter);
        return  view;
    }


}
