package com.example.grp20_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.File;
import java.lang.reflect.Type;

import java.util.ArrayList;

/*
* Get a list of wiki sites for the diffrent years/time
* Each wiki site has an URI for a thumnail
*
* */
public class MainActivity extends AppCompatActivity {
    private WW1MainListFragment mainList;
    public static WW1UserProfile GLOBAL_PROFILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintemp);
        //First checks if we have a profile on disk
        GLOBAL_PROFILE = WW1UserProfile.getData(getApplicationContext());
        if(GLOBAL_PROFILE == null){
            //Removes the fragment from the stack
            //(Only used if you came from deleted profile and try to go back)
            //If we couldn't find one, create one
            startActivity(new Intent(this, CreateUserProfile.class));
        }
        else {
            ArrayList<String> s = new ArrayList<>();
            ArrayList<Integer> i = new ArrayList<>();
            //Make the main list of activities/fragments you can do
            s.add("Build up");
            i.add(R.drawable.ww1start);

            s.add("1914");
            i.add(R.drawable.archyduke);

            s.add("1915");
            i.add(R.drawable.capehelles);

            s.add("1916");
            i.add(R.drawable.ww1h);

            s.add("1917");
            i.add(R.drawable.ww13);

            s.add("1918");
            i.add(R.drawable.ww2end);

            s.add("After Match");
            i.add(R.drawable.peaceww1);

            s.add("Quiz");
            i.add(R.drawable.quizicon);

            //Bundle the lists all together
            Bundle bundle = new Bundle();
            bundle.putSerializable("name", s);
            bundle.putSerializable("draw", i);
            //And send the bundle to the fragment
            mainList = new WW1MainListFragment();
            mainList.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.wikifrag, mainList)
                    .commit();
        }


    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() < 0) {
            this.finish();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int j = 0; j < WW1MainListRecyclerView.wikiPageSize; j++) {

            if(WW1MainListRecyclerView.wikiPages.get(j) == null){
                continue;
            }
            if(WW1MainListRecyclerView.wikiPages.get(j).getImage() != null){
                File photo = new File(WW1MainListRecyclerView.wikiPages.get(j).getImage());
                photo.delete(); //Delete our use on the app
            }
        }
    }

    //A Deserializer for the wikipedia API that get the Title, A Section (The first Section)
    //And A URL the thumbnail if there is one
    static class WikiPageDeserializer implements JsonDeserializer<WikiPage> {

        @Override
        public WikiPage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jobj = json.getAsJsonObject().get("lead").getAsJsonObject(); //General Information
            if (jobj == null) {
                Log.d("NULL", "jobj is null");
            }
            String photo = null;
            if(jobj.get("image") != null){
                photo = jobj.get("image").getAsJsonObject().get("urls").getAsJsonObject().get("1024").getAsString();
            }
            return new WikiPage(jobj.get("id").getAsInt(),
                    jobj.get("displaytitle").getAsString(),
                    jobj.get("sections").getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString(),
                    photo);
        }
    }
    

}
