package com.example.myapplicationtest.Singers.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.myapplicationtest.Artist;
import com.example.myapplicationtest.AsyncHelper;
import com.example.myapplicationtest.Enums.EnumAsync;
import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.FittingPercents;
import com.example.myapplicationtest.HelperLists;
import com.example.myapplicationtest.Singers.Logic.Query_Singer;
import com.example.myapplicationtest.R;
import com.example.myapplicationtest.Singers.Logic.Priority;
import com.example.myapplicationtest.YouTube.YouTubeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;



public class SolutionSinger_Tab1 extends Activity {


    Priority priority = new Priority();
   public static List<String> artists=new ArrayList<>();
   public static List<String> genres=new ArrayList<>();
    public static List<Double> tempo=new ArrayList<>();
    public static List<Double> loudness=new ArrayList<>();
    public static String whichArtist;
    List<Double>grades = new ArrayList<>();
    HelperLists helperLists=new HelperLists();
    public static List<String> resultArrayLess=new ArrayList<>();
    public static List<Double> gradesArrayLess=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_singers);
        mostOrLessPop(false);
    }

    public void mostOrLessPop(boolean needToIncreaseSol) {
        String str_result = null;
        Intent intent2 = getIntent();
        priority = (Priority) intent2.getSerializableExtra(Priority.class.getName());
        Query_Singer query = new Query_Singer();
        // String flag=EnumsSingers.singer.getEnums();
        String q3 = query.UserInput(priority.getFilters().getGenre(), priority.getFilters().getLoudness(), priority.getFilters().getTempo(), null,
                priority.getPrioGenre(), priority.getPrioLoudness(), priority.getPrioTempo(), needToIncreaseSol);
        artists.clear();
        tempo.clear();
        loudness.clear();
        try {
            str_result = new AsyncHelper(SolutionSinger_Tab1.this, q3, "artist_name", "song_tempo", "song_loudness", "genre",
                    EnumAsync.Sol.getEnumAsync()).execute().get();
            // Log.d("D","sol re "+str_result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (str_result != null) {

            FittingPercents fittingPercents = new FittingPercents(priority, null, null);
            if (priority.getPrioGenre().equals(EnumsSingers.High.getEnums())) {
                grades = fittingPercents.percentTempoLoudness("both",
                        priority.getFilters().getLoudness(), priority.getFilters().getTempo(),
                        priority.getPrioLoudness(), priority.getPrioTempo(), tempo, loudness,needToIncreaseSol);
            } else {
                grades = fittingPercents.percentGenreElse(priority.getPrioGenre(), genres,
                        priority.getFilters().getGenre(), priority.getPrioLoudness(),
                        priority.getFilters().getLoudness(), priority.getFilters().getTempo(),
                        priority.getPrioTempo(), tempo, loudness,needToIncreaseSol,"genreSinger");
            }

            resultArrayLess = artists;
            gradesArrayLess = grades;



            List<Artist> artistsList= new ArrayList<>();
            for(int i=0;i<grades.size();i++){
                double grade = round(grades.get(i),1);
                Artist artist=new Artist(artists.get(i),grade+"%");
                artistsList.add(artist);
                //gradesArray.add(grade+"%");
            }
            boolean sol=helperLists.checkSizeOfListResults(this,artistsList,1,0,"singer");
            if(sol) {
                artistsList = artistsList.subList(0, 10);
                ListView listView = findViewById(R.id.listView);
                ListView listView2 = findViewById(R.id.listView2);
                helperLists.updateTwoListView(this, artistsList, listView);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView parent, View view, int position, long id) {

                        Intent intent = new Intent(SolutionSinger_Tab1.this, YouTubeActivity.class);
                        startActivity(intent);
                        whichArtist = listView.getItemAtPosition(position).toString();
                    }
                });
            }else{ //need to increase the filters
                mostOrLessPop(true);
            }


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
        TextView textView = (TextView) findViewById(R.id.txtResults);
        helperLists.updateTextView(R.string.result,textView);
        List<String> resultArray = artists;
        List<String> gradesArray = new ArrayList<>(); /*= grades*/;
        List<Artist> artistsList= new ArrayList<>();
        for(int i=0;i<grades.size();i++){
          double grade = round(grades.get(i),1);
            Artist artist=new Artist(artists.get(i),grade+"%");
            artistsList.add(artist);
        }
        ListView listView = findViewById(R.id.listView);
        ListView listView2 = findViewById(R.id.listView2);

        helperLists.updateTwoListView(this, artistsList, listView);

        Button allSolButton = (Button) findViewById(R.id.btAllSolSingers);
        allSolButton.setVisibility(View.GONE);
    }

    public void back_click(View view){
        Intent intent = new Intent(SolutionSinger_Tab1.this, SingersActivity.class);
        startActivity(intent);
    }
}