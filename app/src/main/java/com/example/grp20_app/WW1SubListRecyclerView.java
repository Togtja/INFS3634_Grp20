package com.example.grp20_app;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
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
    //ArrayList<WikiPage> wikiPage;
    Context context;

    public WW1SubListRecyclerView(FragmentManager fragmentManager, ArrayList<String> wikiSites) {
        this.fragmentManager = fragmentManager;
        this.wikiSites = wikiSites;
        Log.d("SIZE", "wikisites size in sublistRV  " + wikiSites.size());
        Log.d("SIZE", "wikipages size in sublistRV  " + WW1MainListRecyclerView.wikiPages.size());
        //wikiPage = new ArrayList<>();
        //To make sure all pages are in bound
        /*
        for(int i = 0; i < wikiSites.size(); i++){
            wikiPage.add(i, null);
        }*/
    }

    @NonNull
    @Override
    public WW1SubListRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ww1_mainpage, viewGroup, false);
        context = v.getContext();
        return new WW1SubListRecyclerView.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final WW1SubListRecyclerView.ViewHolder viewHolder, final int i) {
        if(WW1MainListRecyclerView.wikiPages.get(i) == null){
            viewHolder.cardView.setVisibility(View.GONE); //Make it invisible till we have loaded it
            Type type = new TypeToken<WikiPage>() {
            }.getType();
            WikiApi pageClient = new Retrofit.Builder()
                    .baseUrl("https://en.wikipedia.org/api/rest_v1/")
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .registerTypeAdapter(type, new MainActivity.WikiPageDeserializer())
                            .create()))
                    .build().create(WikiApi.class);

            Call<WikiPage> call = pageClient.wikiSite(wikiSites.get(i));
            Log.d("Magic", "The nr is " + i);
            call.enqueue(new Callback<WikiPage>() {

                @Override
                public void onResponse(Call<WikiPage> call, Response<WikiPage> response) {
                    final WikiPage theSite = response.body();
                    WW1MainListRecyclerView.wikiPages.add(i, theSite);
                    viewHolder.name.setText(theSite.getTitle());
                    ImageView imageView = viewHolder.image;
                    if (theSite.getImgURL() != null && theSite.getImage() == null) {
                        DownloadBitMap dbm = new DownloadBitMap();
                        dbm.execute(new TheViews(imageView, theSite, viewHolder.cardView, viewHolder.progressBar));
                    }
                    else{
                        viewHolder.cardView.setVisibility(View.VISIBLE);
                        viewHolder.progressBar.setVisibility(View.GONE);
                    }
                    viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {//Give wikiPage to wikiPageFragment
                            Log.d("NULL", "This shouldn't happen :( nr is " + i);
                            if (theSite != null) {


                                final WikiPageFragment wpf = new WikiPageFragment();
                                final Bundle b = new Bundle();
                                b.putSerializable("wiki", theSite);
                                wpf.setArguments(b);
                                fragmentManager.beginTransaction()
                                        .replace(R.id.wikifrag, wpf).addToBackStack(null)
                                        .commit();
                            }
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
        else{
            final WikiPage theSite = WW1MainListRecyclerView.wikiPages.get(i);
            viewHolder.name.setText(theSite.getTitle());
            if (theSite.getImage() != null) {
                ImageView imageView = viewHolder.image;
                imageView.setImageURI(Uri.parse(theSite.getImage()));
            }
            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//Give wikiPage to wikiPageFragment
                    final WikiPageFragment wpf = new WikiPageFragment();
                    final Bundle b = new Bundle();
                    b.putSerializable("wiki", theSite);
                    wpf.setArguments(b);
                    fragmentManager.beginTransaction()
                            .replace(R.id.wikifrag, wpf).addToBackStack(null)
                            .commit();
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return wikiSites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        CardView cardView;
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ww1_cvtxt);
            image = itemView.findViewById(R.id.ww1_cvimg);
            cardView = itemView.findViewById(R.id.ww1_cvCard);
            progressBar = itemView.findViewById(R.id.ww1_loading);
        }
    }
    class DownloadBitMap extends AsyncTask<TheViews, Void, Bitmap> {
        ImageView imageV;
        WikiPage wikiPage;
        private CardView cv;
        private ProgressBar pBar;
        @Override
        protected Bitmap doInBackground(TheViews ... views) {
            try {
                imageV = views[0].getImageV();
                wikiPage = views[0].getWikiPage();
                cv = views[0].getCv();
                pBar= views[0].getpBar();

                URL url = new URL(wikiPage.getImgURL());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap img = BitmapFactory.decodeStream(input);

                ContextWrapper cw = new ContextWrapper(context);
                File mypath = new File(cw.getDir("tempImageDir", Context.MODE_PRIVATE), wikiPage.getTitle() + "_temp.png");
                FileOutputStream fos = new FileOutputStream(mypath);
                // Use the compress method on the BitMap object to write image to the OutputStream
                img.compress(Bitmap.CompressFormat.PNG, 100, fos);
                wikiPage.setImage(mypath.getAbsolutePath());
                return  img;

            }
            catch (IOException e) {
                e.fillInStackTrace();
            }
            return  null;
        }
        protected void onPostExecute(Bitmap bitmap) {
            imageV.setImageBitmap(bitmap);
            //wikiPage.setImage(bitmap);
            cv.setVisibility(View.VISIBLE);
            pBar.setVisibility(View.GONE);
        }
    }

    private class TheViews {
        private ImageView imageV;
        private WikiPage wikiPage;
        private CardView cv;
        private ProgressBar pBar;

        private TheViews(ImageView imageV, WikiPage wikiPage, CardView cv, ProgressBar pBar) {
            this.imageV = imageV;
            this.wikiPage = wikiPage;
            this.cv = cv;
            this.pBar = pBar;
        }

        public ImageView getImageV() {
            return imageV;
        }

        public WikiPage getWikiPage() {
            return wikiPage;
        }

        public CardView getCv() {
            return cv;
        }

        public ProgressBar getpBar() {
            return pBar;
        }
    }
}
