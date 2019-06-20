package com.example.myapplicationtest.Singers.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplicationtest.Artist;
import com.example.myapplicationtest.HelperLists;
import com.example.myapplicationtest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import assets.pair.txt;

public class SulationSinger_Tab3 extends Activity {

    private static List<String> sortedListArtists =new ArrayList<>();
    private static List<String> sortedGrades =new ArrayList<>();
    HelperLists helperLists=new HelperLists();



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_singers_less_popular);


        Map<String,Integer> map= helperLists.createMap(SolutionSinger_Tab1.resultArrayLess,SolutionSinger_Tab1.gradesArrayLess);
        Map<String, Integer> sortedMap= helperLists.sortMapByValue(map);

        for (Map.Entry<String,Integer> entry : sortedMap.entrySet()) {
            sortedGrades.add(entry.getValue().toString()+"%");
            sortedListArtists.add(entry.getKey());
        }


        List<String> listTop10= sortedListArtists.subList(0,10);
        List<String> gradeTop10= sortedGrades.subList(0,10);

        ListView listView = findViewById(R.id.listViewLess);

        List<Artist> artistsList=new ArrayList<>();
        for(int i=0;i<listTop10.size();i++) {
            Artist artist=new Artist(listTop10.get(i),gradeTop10.get(i));
            artistsList.add(artist);
        }
        boolean sol=helperLists.checkSizeOfListResults(this,artistsList,2,0,"singer");
       /* if(sol) {
            artistsList = artistsList.subList(0, 10);
        }*/
        helperLists.updateTwoListView(this,artistsList, listView);

    }



    public void allSol_click(View view) {
        TextView textView = (TextView) findViewById(R.id.txtResults);
        helperLists.updateTextView(R.string.result,textView);
        ListView listView = findViewById(R.id.listViewLess);

        List<Artist> artistsList=new ArrayList<>();
        for(int i=0;i<sortedListArtists.size();i++) {
            Artist artist=new Artist(sortedListArtists.get(i),sortedGrades.get(i));
            artistsList.add(artist);
        }

        helperLists.updateTwoListView(this,artistsList, listView);
        Button allSolButton = (Button) findViewById(R.id.btAllSolSingers);
        allSolButton.setVisibility(View.GONE);
    }


}