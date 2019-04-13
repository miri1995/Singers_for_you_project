package com.example.myapplicationtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.example.myapplicationtest.Enums.EnumAsync;
import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.SingersLogic.Priority;
//import assets.pair3.txt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.lang.StrictMath.round;

public class SulationSinger  extends Activity {


    Priority priority = new Priority();
   public static List<String> artists=new ArrayList<>();
    //public static List<String> artists_id=new ArrayList<>();
   public static List<String> genres=new ArrayList<>();
    public static List<Double> tempo=new ArrayList<>();
    public static List<Double> loudness=new ArrayList<>();
    List<Double>grades = new ArrayList<>();

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
             str_result=new AsyncHelper(SulationSinger.this,q3,"artist_name","song_tempo","song_loudness","genre",
                     EnumAsync.Sol.getEnumAsync()).execute().get();
            // Log.d("D","sol re "+str_result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        if(str_result!=null) {
          /*  for (int i=0;i<artists.size();i++){
                if(moreThanOnce(artists,artists.get(i))){
                    artists.remove(artists.get(i));
                    tempo.remove(tempo.get(i));
                    genres.remove(genres.get(i));
                    loudness.remove(loudness.get(i));
                }

            }*/
            FittingPercents fittingPercents = new FittingPercents(priority);
            if(priority.getPrioGenre().equals(EnumsSingers.High.getEnums())){
                grades = fittingPercents.percentTempoLoudness("both");
            }
            else{
                    grades = fittingPercents.percentGenreElse();
            }

            List<String> resultArray = artists.subList(0,10);
            List<Double> gradesArray = new ArrayList<>();
            for(int i=0;i<grades.size();i++){
                double grade = round(grades.get(i),2);
                gradesArray.add(grade);
            }
            gradesArray = gradesArray.subList(0,10);
            ///ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main3, resultArray);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.activity_listview, resultArray);
            ArrayAdapter<Double> adapter2 = new ArrayAdapter<Double>(this,
                    R.layout.activity_listview, gradesArray);
            ListView listView = findViewById(R.id.listView);
            ListView listView2 = findViewById(R.id.listView2);
            listView.setAdapter(adapter);
            listView2.setAdapter(adapter2);
           // Log.d("D", "ll" + listView.toString());
        }

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    public static boolean moreThanOnce(List<String> list, String searched)
    {
        int numCount = 0;

        for (String thisArtist : list) {
            if (thisArtist.equals(searched)) numCount++;
        }

        return numCount > 0;
    }


    public void allSol_click(View view) {
        List<String> resultArray = artists;
        List<Double> gradesArray = new ArrayList<>(); /*= grades*/;
        for(int i=0;i<grades.size();i++){
          double grade = round(grades.get(i),2);
          gradesArray.add(grade);
        }
        ///ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main3, resultArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, resultArray);
        ArrayAdapter<Double> adapter2 = new ArrayAdapter<Double>(this,
                R.layout.activity_listview, gradesArray);
        ListView listView = findViewById(R.id.listView);
        ListView listView2 = findViewById(R.id.listView2);
        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);

        Button allSolButton = (Button) findViewById(R.id.btAllSolSingers);
        allSolButton.setVisibility(View.GONE);
    }

    public void back_click(View view){
        Intent intent = new Intent(SulationSinger.this, SingersActivity.class);
        startActivity(intent);
    }
}