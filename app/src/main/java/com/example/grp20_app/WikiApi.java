package com.example.grp20_app;


import android.net.Uri;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WikiApi {
    @GET("page/mobile-sections/{title}")
    Call<WikiPage> wikiSite(@Path("title") String title);

}
