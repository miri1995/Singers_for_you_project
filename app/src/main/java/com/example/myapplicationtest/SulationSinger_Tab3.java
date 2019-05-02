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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

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


        //todo
        //להפןך בין הקי לואלו כי האחוזים הם לא חד ערכיים- מהאתר מיון עפ ערך
        //https://www.mkyong.com/java/how-to-sort-a-map-in-java/
        Map<Double,String> map= new HashMap<Double, String>();
        for(int i=0; i<SulationSinger_Tab1.gradesArrayLess.size();i++){
            map.put(SulationSinger_Tab1.gradesArrayLess.get(i),SulationSinger_Tab1.resultArrayLess.get(i));
        }

      //  Map<Double, String> treeMap = new TreeMap<Double, String>(map);

        Map<Double, String> treeMap = new TreeMap<Double, String>(
                new Comparator<Double>() {

                    @Override
                    public int compare(Double o1, Double o2) {
                        return o2.compareTo(o1);
                    }

                });


        treeMap.putAll(map);


        for (Map.Entry<Double, String> entry : treeMap.entrySet()) {
            sortedListArtists.add(entry.getValue());
            sortedGrades.add(entry.getKey().toString()+"%");
        }


        List<String> listTop10= sortedListArtists.subList(0,10);

        List<String> gradeTop10= sortedGrades.subList(0,10);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, listTop10);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                R.layout.activity_listview, gradeTop10);
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