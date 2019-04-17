package com.example.grp20_app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WW1SubListRecyclerView extends RecyclerView.Adapter<WW1SubListRecyclerView.ViewHolder> {
    private FragmentManager fragmentManager;
    ArrayList<String> wikiSites;
    ArrayList<WikiPage> wikiPage;

    public WW1SubListRecyclerView(FragmentManager fragmentManager, ArrayList<String> wikiSites) {
        this.fragmentManager = fragmentManager;
        this.wikiSites = wikiSites;
        wikiPage = new ArrayList<>();
        //To make sure all pages are in bound
        for(int i = 0; i < wikiSites.size(); i++){
            wikiPage.add(i, null);
        }
    }

    @NonNull
    @Override
    public WW1SubListRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ww1_mainpage, viewGroup, false);
        return new WW1SubListRecyclerView.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final WW1SubListRecyclerView.ViewHolder viewHolder, final int i) {
        Type type = new TypeToken<WikiPage>() {
        }.getType();
        WikiApi pageClient = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/api/rest_v1/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .registerTypeAdapter(type, new MainActivity.WikiPageDeserializer())
                        .create()))
                .build().create(WikiApi.class);

        Call<WikiPage> call = pageClient.wikiSite(wikiSites.get(i));
        Log.d("Magic", "The nr is " + Integer.toString(i));
        call.enqueue(new Callback<WikiPage>() {

            @Override
            public void onResponse(Call<WikiPage> call, Response<WikiPage> response) {
                wikiPage.add(i, response.body());
                viewHolder.name.setText(wikiPage.get(i).getTitle());
                ImageView imageView = viewHolder.image;
                if (wikiPage.get(i).getImgURL() != null) {
                    new DownloadBitMap().execute(new Pair<>(wikiPage.get(i), imageView));
                }



                viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Give wikiPage to wikiPageFragment
                        final WikiPageFragment wpf = new WikiPageFragment();
                        final Bundle b = new Bundle();
                        b.putSerializable("wiki", wikiPage.get(i));
                        wpf.setArguments(b);
                        fragmentManager.beginTransaction()
                                .replace(R.id.wikifrag, wpf).addToBackStack(null)
                                .commit();
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

    @Override
    public int getItemCount() {
        return wikiSites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ww1_cvtxt);
            image = itemView.findViewById(R.id.ww1_cvimg);
            cardView = itemView.findViewById(R.id.ww1_cvCard);
        }
    }
    class DownloadBitMap extends AsyncTask<Pair<WikiPage, ImageView>, Void, Bitmap> {
        ImageView imageV;
        WikiPage wikiPage;
        @Override
        protected Bitmap doInBackground(Pair<WikiPage, ImageView> ... pairs) {
            try {
                imageV = pairs[0].second;
                wikiPage = pairs[0].first;
                URL url = new URL(pairs[0].first.getImgURL());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap img = BitmapFactory.decodeStream(input);
                return  img;

            }
            catch (IOException e) {
                e.fillInStackTrace();
            }
            return  null;
        }
        protected void onPostExecute(Bitmap bitmap) {
            imageV.setImageBitmap(bitmap);
            wikiPage.setImage(bitmap);
        }
    }

}
