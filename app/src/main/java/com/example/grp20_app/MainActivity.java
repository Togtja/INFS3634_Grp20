package com.example.grp20_app;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

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
    WikiPage wikipage;
    private String pageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintemp);
        ArrayList<Pair<String, Integer>> list = new ArrayList<>();
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
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", list);

        wikifrag = new WW1MainListFragment();
        wikifrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.wikifrag, wikifrag)
                .commit();

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
