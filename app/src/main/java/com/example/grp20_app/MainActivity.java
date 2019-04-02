package com.example.grp20_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ArrayList<WikiPage> wikipages;
    private WikiPageFragment wikifrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Type type =  new TypeToken<List<WikiPage>>(){}.getType();
        Gson gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(type, new WikiPageDestabilizer())
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/api.php")
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .build();


        WikiApi client = retrofit.create(WikiApi.class);
        Call<ArrayList<WikiPage>> call = client.keyOfUser("query", "4764461","extract","jsonfm");
        call.enqueue(new Callback<ArrayList<WikiPage>>(){

            @Override
            public void onResponse(Call<ArrayList<WikiPage>> call, Response<ArrayList<WikiPage>> response) {
                wikipages = new ArrayList<>();
                wikipages = response.body();
                setContentView(R.layout.activity_main);

                Bundle bundle = new Bundle();
                //A cheat class to easily serialize a bundle
                bundle.putSerializable("key_beer", new BundleWikiPageArray((ArrayList<WikiPage>) wikipages).getWikiPages());
               wikifrag = new WikiPageFragment();
                wikifrag.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.WikiFragment, wikifrag)
                        .commit();

            }

            @Override
            public void onFailure(Call<ArrayList<WikiPage>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }
}
