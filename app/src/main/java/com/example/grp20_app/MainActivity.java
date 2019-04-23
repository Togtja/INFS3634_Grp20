package com.example.grp20_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
// CURRENT PLAN
/*
* Get a list of wiki sites for the diffrent years/time
* Each wiki site has an URI for a thumnail
*
* */
public class MainActivity extends AppCompatActivity {
    private WW1MainListFragment wikifrag;
    public static WW1UserProfile GLOBAL_PROFILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintemp);
        GLOBAL_PROFILE = WW1UserProfile.getData(getApplicationContext());
        if(GLOBAL_PROFILE == null){
            //Create Profile
            startActivity(new Intent(this, CreateUserProfile.class));
        }
        else {
            ArrayList<String> s = new ArrayList<>();
            ArrayList<Integer> i = new ArrayList<>();
            //Make the list
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

            Bundle bundle = new Bundle();
            bundle.putSerializable("name", s);
            bundle.putSerializable("draw", i);

            wikifrag = new WW1MainListFragment();
            wikifrag.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.wikifrag, wikifrag)
                    .commit();
        }


    }
    public void OpenProfile(View view){
        startActivity(new Intent(this, WW1DisplayProfile.class));
    }

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
