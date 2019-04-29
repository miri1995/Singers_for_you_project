package com.example.myapplicationtest.YouTube;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VideoSearchApi {
    @GET("/youtube/v3/search?part=snippet&maxResult=10&key=AIzaSyAdac6e69PTicfGarfhf1g1DJlTQs_Jbck")

    Call<JsonResponse> callback(@Query("q") String query);
}
