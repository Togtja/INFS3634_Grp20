package com.example.grp20_app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class WikiPageFragment extends Fragment {
    ImageView image;
    public WikiPageFragment(){}
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.wiki_page, container, false);
            Bundle bundle = getArguments();
            if(bundle != null){
                PairWikiPage tempwikipage = (PairWikiPage) bundle.getSerializable("wiki");
                Pair<WikiPage, Uri> wikipage = tempwikipage.getWikiPages();
                TextView title = view.findViewById(R.id.tvTitle);
                title.setText(wikipage.first.title);
                TextView year = view.findViewById(R.id.tvYear);
                year.setText("must fix");
                TextView wiki = view.findViewById(R.id.tvWiki);
                wiki.setText(Html.fromHtml(wikipage.first.text));
                image = view.findViewById(R.id.ivWW1);
                new DownloadBitMap().execute(wikipage.second.toString());



            }

            return view;
    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        }
        catch (IOException e) {
            e.fillInStackTrace();
            return null;
        }
    }
    private class DownloadBitMap extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... strings) {
            return getBitmapFromURL(strings[0]);
        }
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null){
                image.setImageBitmap(bitmap);
            }
        }
    }
}
