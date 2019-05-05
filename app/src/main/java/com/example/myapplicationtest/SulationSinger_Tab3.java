package com.example.myapplicationtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//import assets.pair3.txt;

public class SulationSinger_Tab3 extends Activity {

    private static List<String> sortedListArtists =new ArrayList<>();
    private static List<String> sortedGrades =new ArrayList<>();
    HelperLists helperLists=new HelperLists();



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_singers_less_popular);


        Map<String,Integer> map= new HashMap<String,Integer>();
        for(int i=0; i<SulationSinger_Tab1.gradesArrayLess.size();i++){
            map.put(SulationSinger_Tab1.resultArrayLess.get(i),SulationSinger_Tab1.gradesArrayLess.get(i).intValue());
        }
        Map<String, Integer> sortedMap= helperLists.sortMapByValue(map);



        for (Map.Entry<String,Integer> entry : sortedMap.entrySet()) {
            sortedGrades.add(entry.getValue().toString()+"%");
            sortedListArtists.add(entry.getKey());
        }


        List<String> listTop10= sortedListArtists.subList(0,10);
        List<String> gradeTop10= sortedGrades.subList(0,10);

        ListView listView = findViewById(R.id.listViewLess);
        ListView listView2 = findViewById(R.id.listViewLess2);
        helperLists.updateTwoListView(this,listTop10,gradeTop10, listView , listView2);

    }






    public void allSol_click(View view) {
        ListView listView = findViewById(R.id.listViewLess);
        ListView listView2 = findViewById(R.id.listViewLess2);
        helperLists.updateTwoListView(this,sortedListArtists,sortedGrades, listView , listView2);

        Button allSolButton = (Button) findViewById(R.id.btAllSolSingers);
        allSolButton.setVisibility(View.GONE);
    }

    public void back_click(View view){
        Intent intent = new Intent(SulationSinger_Tab3.this, SingersActivity.class);
        startActivity(intent);
    }
}