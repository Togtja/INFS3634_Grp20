package com.example.grp20_app;

import android.net.Uri;
import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;

class PairList implements Serializable {
    private ArrayList<Pair<String, Integer>> list;
    public PairList() {
        list = new ArrayList<>();
        //Make the list
        String s = "Build up";
        Integer i = R.drawable.ww1start;
        list.add(new Pair<>(s, i));
        s = "1914";
        i = R.drawable.archyduke;
        list.add(new Pair<>(s, i));
        s = "1915";
        i = R.drawable.capehelles;
        list.add(new Pair<>(s, i));
        s = "1916";
        i = R.drawable.ww1h;
        list.add(new Pair<>(s, i));
        s = "1917";
        i = R.drawable.ww13;
        list.add(new Pair<>(s, i));
        s = "1918";
        i = R.drawable.ww2end;
        list.add(new Pair<>(s, i));
        s = "After Match";
        i = R.drawable.peaceww1;
        list.add(new Pair<>(s, i));
        s = "Quiz";
        i = R.drawable.quizicon;
        list.add(new Pair<>(s, i));
    }

    public ArrayList<Pair<String, Integer>> getList() {
        return list;
    }
}
