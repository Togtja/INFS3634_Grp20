
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
        ArrayList<PairWikiPage> wikiPages = new ArrayList<>();
        Bundle b =  getArguments();
        PairWikiPage wikiPage = (PairWikiPage) b.getSerializable("key_wiki");
        wikiPages.add(wikiPage);
        ArrayList<Pair<String, Integer>> list = (ArrayList<Pair<String, Integer>>) b.getSerializable("list");
        FragmentManager fragmentManager = getFragmentManager();
        mAdapter = new WW1MainListRecyclerView(fragmentManager, list, wikiPages);

        ww1MainList.setAdapter(mAdapter);
        return  view;
    }


}
