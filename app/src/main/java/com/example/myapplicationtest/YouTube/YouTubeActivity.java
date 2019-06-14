package com.example.myapplicationtest.YouTube;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import com.example.myapplicationtest.R;
import com.example.myapplicationtest.Singers.Activity.SolutionSinger_Tab1;
import com.google.android.youtube.player.YouTubeBaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YouTubeActivity  extends YouTubeBaseActivity   {
    private static final String TAG = "TAG";
    private ThumbnailVideoAdapter thumbnailVideoAdapter;
    private List<JsonVideo> videos = new ArrayList<>();;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.you_tube);
      //  youTubePlayerView=findViewById(R.id.youTubePlayer);
      //  button=findViewById(R.id.playYouTube);
       // onInitializedListener=new YouTubePlayer.OnInitializedListener() {



        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        thumbnailVideoAdapter = new ThumbnailVideoAdapter(YouTubeActivity.this);
        recyclerView.setAdapter(thumbnailVideoAdapter);

        loadVideo();

    }

    private void loadVideo() {
        App.getVideoSearchApi().callback(SolutionSinger_Tab1.whichArtist).enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "onResponse: " + response.body().getVideos());
                //new Thread() {

                videos = response.body().getVideos();
                if (videos != null) {
                    thumbnailVideoAdapter.setVideos(videos);
                }
           // }.start();
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                Log.d(TAG, "onFailure:");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


}