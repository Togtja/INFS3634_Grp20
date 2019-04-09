package com.example.grp20_app;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WikiApi {
    Call<ArrayList<WikiPage>> keyOfUser(@Query("action") String action, @Query("pageids") String pageids, @Query("prop") String prop, @Query("format") String format);
}
