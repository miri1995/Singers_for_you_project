package com.example.myapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.example.myapplicationtest.SingersLogic.Priority;
//import assets.pair3.txt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SulationSinger  extends Activity {


    Priority priority = new Priority();
   public static List<String> artists=new ArrayList<>();
    //public static List<String> artists_id=new ArrayList<>();
   public static List<String> genres=new ArrayList<>();
    public static List<String> tempo=new ArrayList<>();
    public static List<String> loudness=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String str_result=null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_singers);
        Intent intent2 = getIntent();
        priority = (Priority) intent2.getSerializableExtra("com.example.myapplicationtest.SingersLogic.Priority");


        Query query = new Query();
        String q3= query.UserInput(priority.getFilters().getGenre(),priority.getFilters().getLoudness(),priority.getFilters().getTempo(),
                priority.getPrioGenre(),priority.getPrioLoudness(),priority.getPrioTempo(),priority.getPopular());
        artists.clear();
        tempo.clear();
        loudness.clear();
       // artists_id.clear();
       // new AsyncHelper(SulationSinger.this,q3,"artist_name","artist_id","sol").execute(); //async task for getting data from db
        try {
             str_result=new AsyncHelper(SulationSinger.this,q3,"artist_name","song_tempo","song_loudness","genre","sol").execute().get();
            // Log.d("D","sol re "+str_result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(str_result!=null) {
          //  FittingPercents fittingPercents = new FittingPercents(priority);
           // List<Double> list = fittingPercents.percent();
            List<String> resultArray = artists.subList(0,10);
            ///ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main3, resultArray);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.activity_listview, resultArray);
            ListView listView = findViewById(R.id.listView);
            listView.setAdapter(adapter);
           // Log.d("D", "ll" + listView.toString());
        }

    }

    public void allSol_click(View view) {
        List<String> resultArray = artists;
        ///ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main3, resultArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, resultArray);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        Button allSolButton = (Button) findViewById(R.id.btAllSolSingers);
        allSolButton.setVisibility(View.GONE);
    }

    public void back_click(View view){
        Intent intent = new Intent(SulationSinger.this, SingersActivity.class);
        startActivity(intent);
    }
}