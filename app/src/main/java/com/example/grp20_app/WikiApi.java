package com.example.grp20_app;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface WikiApi {
    //@Headers("togtja@gmail.com") //For the wikiAPI
    @GET("page/mobile-sections/{title}")
    Call<WikiPage> wikiSite(@Path("title") String title);

}
