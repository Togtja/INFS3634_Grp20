package com.example.grp20_app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Type;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
    Pair<WikiPage, Uri> wikipage;
    private String pageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageId = "4764461";
        Type type = new TypeToken<WikiPage>() {
        }.getType();
        Type type2 = new TypeToken<String>() {
        }.getType();
        Type type3 = new TypeToken<Uri>() {
        }.getType();
        //To get wiki page
        WikiApi pageClient = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .registerTypeAdapter(type, new WikiPageDestabilizer())
                        .create()))
                .build().create(WikiApi.class);
        //To get file name
        final WikiApi fileClient = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .registerTypeAdapter(type2, new WikiPageImageDestabilizer())
                        .create()))
                .build().create(WikiApi.class);
        //To get the file URL
        final WikiApi uriClient = new Retrofit.Builder()
                .baseUrl("https://www.mediawiki.org/w/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .registerTypeAdapter(type3, new WikiPageImageURLDestabilizer())
                        .create()))
                .build().create(WikiApi.class);

        Call<WikiPage> call = pageClient.wikiSite("query", pageId, "extracts", "true", "json");
        call.enqueue(new Callback<WikiPage>() {

            @Override
            public void onResponse(Call<WikiPage> call, Response<WikiPage> response) {
                setContentView(R.layout.maintemp);
                final WikiPage wp = response.body();
                Call<String> call2 = fileClient.wikithumFile("query", pageId, "pageimages", "json");
                call2.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String s = "File:" + response.body();
                        Call<Uri> call3 = uriClient.wikiURIPhoto("query", s, "imageinfo", "url", "json");
                        call3.enqueue(new Callback<Uri>() {
                            @Override
                            public void onResponse(Call<Uri> call, Response<Uri> response) {
                                Log.d("URL", response.body().toString());
                                //Bitmap bitmap = getBitmapFromURL(response.body().toString());
                                Bundle bundle = new Bundle();
                                wikipage = new Pair<>(wp, response.body());
                                bundle.putSerializable("key_wiki", new PairWikiPage(wikipage));
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
                                bundle.putSerializable("list", list);

                                wikifrag = new WW1MainListFragment();
                                wikifrag.setArguments(bundle);
                                getSupportFragmentManager().beginTransaction()
                                        .add(R.id.wikifrag, wikifrag)
                                        .commit();
                            }

                            @Override
                            public void onFailure(Call<Uri> call, Throwable t) {
                                Log.d("CALL3", "FAILED");
                                t.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("CALL2", "FAILED");
                        t.printStackTrace();
                    }
                });

            }

            @Override
            public void onFailure(Call<WikiPage> call, Throwable t) {
                Log.d("CALL1", "FAILED");
                t.printStackTrace();
            }
        });


    }

    class WikiPageDestabilizer implements JsonDeserializer<WikiPage> {

        @Override
        public WikiPage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jobj = json.getAsJsonObject().get("query").getAsJsonObject().get("pages").getAsJsonObject().get(pageId).getAsJsonObject();
            if (jobj == null) {
                Log.d("NULL", "jobj is null");
            }
            return new WikiPage(jobj.get("pageid").getAsInt(), jobj.get("title").getAsString(), jobj.get("extract").getAsString());
        }
    }

    class WikiPageImageDestabilizer implements JsonDeserializer<String> {

        @Override
        public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jobj = json.getAsJsonObject().get("query").getAsJsonObject().get("pages").getAsJsonObject().get(pageId).getAsJsonObject();
            if (jobj == null) {
                Log.d("NULL", "jobj is null");
            }
            return jobj.get("pageimage").getAsString();
        }
    }

    class WikiPageImageURLDestabilizer implements JsonDeserializer<Uri> {

        @Override
        public Uri deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jobj = json.getAsJsonObject().get("query").getAsJsonObject().get("pages").getAsJsonObject().get("-1").getAsJsonObject();
            JsonObject jobj2 = jobj.get("imageinfo").getAsJsonArray().get(0).getAsJsonObject();
            if (jobj2 == null) {
                Log.d("NULL", "jobj is null");
            }
            return Uri.parse(jobj2.get("url").getAsString());
        }
    }

}
