package com.example.grp20_app;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
//The Wikipedia API call
//Not much more to say
public interface WikiApi {
    @GET("page/mobile-sections/{title}")
    Call<WikiPage> wikiSite(@Path("title") String title);

}
