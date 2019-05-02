package com.example.myapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//import assets.pair3.txt;

public class SulationSinger_Tab3 extends Activity {

    private static List<String> sortedListArtists =new ArrayList<>();
    private static List<String> sortedGrades =new ArrayList<>();
    private QuickSort quickSort=new QuickSort();
//TODO ROUND TO GRADE LIST

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_singers_less_popular);
        //convert from list<double> to list of string
        List<String> strings = new ArrayList<String>();
        for (Double d : SulationSinger_Tab1.gradesArrayLess) {
            // Apply formatting to the string if necessary
            strings.add(d.toString()+"%");
        }
        sortedListArtists =strings;
        Collections.sort(sortedListArtists);

        List<String> reverseListTop10= sortedListArtists.subList(0,10);

        //convert from list<double> to list of string
      /*  List<String> strings = new ArrayList<String>();
        for (Double d : SulationSinger_Tab1.gradesArrayLess) {
            // Apply formatting to the string if necessary
            strings.add(d.toString()+"%");
        }*/

       // sortedGrades =  helperLists.reverseArrayList(strings);
        List<String> reverseGradeTop10= sortedGrades.subList(0,10);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, reverseListTop10);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                R.layout.activity_listview, reverseGradeTop10);
        ListView listView = findViewById(R.id.listViewLess);
        ListView listView2 = findViewById(R.id.listViewLess2);
        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);

    }






    public void allSol_click(View view) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, sortedListArtists);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                R.layout.activity_listview, sortedGrades);
        ListView listView = findViewById(R.id.listViewLess);
        ListView listView2 = findViewById(R.id.listViewLess2);
        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);

        Button allSolButton = (Button) findViewById(R.id.btAllSolSingers);
        allSolButton.setVisibility(View.GONE);
    }

    public void back_click(View view){
        Intent intent = new Intent(SulationSinger_Tab3.this, SingersActivity.class);
        startActivity(intent);
    }
}