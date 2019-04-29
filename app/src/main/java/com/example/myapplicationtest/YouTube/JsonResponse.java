package com.example.myapplicationtest.YouTube;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonResponse {

    @SerializedName("items")
    private List<JsonVideo> videos;

    public List<JsonVideo> getVideos() {
        return videos;
    }
}