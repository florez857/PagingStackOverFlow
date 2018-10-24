package com.example.android.pagingstackoverflow;

import com.example.android.pagingstackoverflow.Model.ApiStackData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("answers")
    Call<ApiStackData> getAnswers(@Query("page") int page, @Query("pagesize") int pagesize, @Query("site") String site);
}