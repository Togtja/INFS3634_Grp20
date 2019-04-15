package com.example.grp20_app;


import android.net.Uri;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WikiApi {
    @GET("api.php/")
    Call<WikiPage> wikiSite(@Query("action") String action, @Query("pageids") String pageid,
                             @Query("prop") String prop,@Query("exintro") String bool ,@Query("format") String format);
    @GET("api.php/")
    Call<String> wikithumFile(@Query("action") String action, @Query("pageids") String pageid,
                              @Query("prop") String prop, @Query("format") String format);
    @GET("api.php/")
    Call<Uri>    wikiURIPhoto(@Query("action") String action, @Query("titles") String nameOfFile,
                              @Query("prop") String prop, @Query("iiprop") String iiprop , @Query("format") String format);
}
