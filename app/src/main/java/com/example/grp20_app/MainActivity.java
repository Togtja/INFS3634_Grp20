package com.example.grp20_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import java.util.ArrayList;

// CURRENT PLAN
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
    protected void onStop() {
        super.onStop();
        //save user data to file
        if(MainActivity.GLOBAL_PROFILE != null){
            MainActivity.GLOBAL_PROFILE.saveData(getApplicationContext());
        }

    }

    //If you click on your profile photo it opens some information about your profile
    public void OpenProfile(View view){
        startActivity(new Intent(this, WW1DisplayProfile.class));
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
