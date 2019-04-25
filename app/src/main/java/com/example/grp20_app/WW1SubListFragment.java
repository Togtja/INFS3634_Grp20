package com.example.grp20_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class WW1SubListFragment extends Fragment {
    private WW1SubListRecyclerView mAdapter;
    RecyclerView ww1MainList;
    ArrayList<String> wikiPages = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.recyclerview, container, false);
        ww1MainList = view.findViewById(R.id.ww1_main_rv);
        ww1MainList.setHasFixedSize(true);

        ww1MainList.setLayoutManager(new GridLayoutManager(getContext(), 2));



        wikiPages  = (ArrayList<String>) getArguments().getSerializable("wiki");

        FragmentManager fragmentManager = getFragmentManager();
        mAdapter = new WW1SubListRecyclerView(fragmentManager, wikiPages);
        ww1MainList.setAdapter(mAdapter);
        return view;

    }
}
