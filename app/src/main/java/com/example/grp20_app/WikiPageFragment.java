package com.example.grp20_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WikiPageFragment extends Fragment {
    public WikiPageFragment(){}
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.activity_main, container, false);
            Bundle bundle = getArguments();
            if(bundle != null){

            }

            return view;
    }

}
