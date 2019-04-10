package com.example.grp20_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private WikiPage wikipage;
    private WW1MainListFragment wikifrag;
    private String pageId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageId = "4764461";
        Type type = new TypeToken<WikiPage>() {
        }.getType();
        Gson gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(type, new WikiPageDestabilizer())
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/")
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .build();


        WikiApi client = retrofit.create(WikiApi.class);
        Call<WikiPage> call = client.keyOfUser("query", pageId, "extracts", "true","json");
        call.enqueue(new Callback<WikiPage>() {

            @Override
            public void onResponse(Call<WikiPage> call, Response<WikiPage> response) {
                //wikipage = new WikiPage();
                wikipage = response.body();
                setContentView(R.layout.maintemp);

                Bundle bundle = new Bundle();
                //A cheat class to easily serialize a bundle
                bundle.putSerializable("key_wiki", wikipage);
                wikifrag = new WW1MainListFragment();
                wikifrag.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.wikifrag, wikifrag)
                        .commit();

            }

            @Override
            public void onFailure(Call<WikiPage> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    class WikiPageDestabilizer implements JsonDeserializer<WikiPage> {

        @Override
        public WikiPage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jobj = json.getAsJsonObject().get("query").getAsJsonObject().get("pages").getAsJsonObject().get(pageId).getAsJsonObject();
            if(jobj == null){
                Log.d("NULL", "jobj is null");
            }
            return new WikiPage(jobj.get("pageid").getAsInt(), jobj.get("title").getAsString(), jobj.get("extract").getAsString());
        }
    }
}
