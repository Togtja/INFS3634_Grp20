
package com.example.grp20_app;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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
        ArrayList<ArrayList<String>> wikiPages = new ArrayList<>();
        //Make all the list of list for the webpages
        //Websites for build up
        ArrayList<String> ww1_buildUp = new ArrayList<>();
        ww1_buildUp.add("Causes_of_World_War_I");
        ww1_buildUp.add("Assassination_of_Archduke_Franz_Ferdinand");

        //Websites for 1914
        ArrayList<String> ww1_1914 = new ArrayList<>();
        ww1_1914.add("Skirmish_at_Joncherey");
        ww1_1914.add("German_invasion_of_Belgium");
        //Websites for 1915
        ArrayList<String> ww1_1915 = new ArrayList<>();
        ww1_1915.add("Battle_of_Jassin");
        ww1_1915.add("Battle_of_Hartmannswillerkopf");
        ww1_1915.add("Battle_of_Dogger_Bank_(1915)");
            //NOT IN ORDER
        ww1_1915.add("Gallipoli_Campaign");
        //Websites for 1916
        ArrayList<String> ww1_1916 = new ArrayList<>();
        ww1_1916.add("Battle_of_Mojkovac");
        ww1_1916.add("Battle_of_Sheikh_Sa'ad");
        ww1_1916.add("Erzurum_Offensive");
        //Websites for 1917
        ArrayList<String> ww1_1917 = new ArrayList<>();
        ww1_1917.add("Battle_of_Behobeho");
        ww1_1917.add("Battle_of_Rafa");
        ww1_1917.add("Zimmermann_Telegram");
        ww1_1917.add("Battle_of_Nambanje");
        ww1_1917.add("Samarrah_Offensive");

        //Websites for 1918
        ArrayList<String> ww1_1918 = new ArrayList<>();
        ww1_1918.add("Fourteen_Points");
        ww1_1918.add("British_occupation_of_the_Jordan_Valley");
        ww1_1918.add("Treaty_of_Brest-Litovsk_(Ukraine–Central_Powers)");
        ww1_1918.add("Battle_of_Rarańcza");
        ww1_1918.add("Operation_Faustschlag");
        //Websites for the after match
        ArrayList<String> ww1_afterMatch = new ArrayList<>();
        ww1_afterMatch.add("1920_Schleswig_plebiscites");

        //Add to list
        wikiPages.add(ww1_buildUp);
        wikiPages.add(ww1_1914);
        wikiPages.add(ww1_1915);
        wikiPages.add(ww1_1916);
        wikiPages.add(ww1_1917);
        wikiPages.add(ww1_1918);
        wikiPages.add(ww1_afterMatch);

        Bundle b =  getArguments();
        ArrayList<String> s = (ArrayList<String>) b.getSerializable("name");
        ArrayList<Integer> i = (ArrayList<Integer>) b.getSerializable("draw");
        ArrayList<Pair<String, Integer>> list = new ArrayList<>();
        for(int x = 0; x < s.size(); x++){
            list.add(new Pair<>(s.get(x), i.get(x)));
        }
        FragmentManager fragmentManager = getFragmentManager();
        mAdapter = new WW1MainListRecyclerView(fragmentManager, list, wikiPages);

        ww1MainList.setAdapter(mAdapter);
        return  view;
    }


}
