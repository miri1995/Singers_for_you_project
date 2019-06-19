package com.example.myapplicationtest.Singers.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplicationtest.Artist;
import com.example.myapplicationtest.HelperLists;
import com.example.myapplicationtest.R;

import java.util.ArrayList;
import java.util.List;

//import assets.pair.txt;

public class SolutionSinger_Tab2 extends Activity {

    private static List<String> reverseListArtists =new ArrayList<>();
    private static List<String> reverseGrade=new ArrayList<>();
    private HelperLists helperLists=new HelperLists();
//TODO ROUND TO GRADE LIST

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_singers_less_popular);

        reverseListArtists =helperLists.reverseArrayList(SolutionSinger_Tab1.resultArrayLess);
        List<String> reverseListTop10= reverseListArtists.subList(0,10);

        //convert from list<double> to list of string
        List<String> strings = new ArrayList<String>();
        for (Double d : SolutionSinger_Tab1.gradesArrayLess) {
            // Apply formatting to the string if necessary
            strings.add(d.toString()+"%");
        }

        reverseGrade=  helperLists.reverseArrayList(strings);


        List<String> reverseGradeTop10=reverseGrade.subList(0,10);
        ListView listView = findViewById(R.id.listViewLess);
        ListView listView2 = findViewById(R.id.listViewLess2);
        List<Artist> artistsList=new ArrayList<>();
        for(int i=0;i<reverseListTop10.size();i++) {
            Artist artist=new Artist(reverseListTop10.get(i),reverseGradeTop10.get(i));
            artistsList.add(artist);
        }
        boolean sol=helperLists.checkSizeOfListResults(this,artistsList,3,0,"singer");
      /*  if(sol) {
            artistsList = artistsList.subList(0, 10);
        }*/
        helperLists.updateTwoListView(this,artistsList, listView);

    }






    public void allSol_click(View view) {
        ListView listView = findViewById(R.id.listViewLess);
        ListView listView2 = findViewById(R.id.listViewLess2);

        List<Artist> artistsList=new ArrayList<>();
        for(int i=0;i<reverseListArtists.size();i++) {
            Artist artist=new Artist(reverseListArtists.get(i),reverseGrade.get(i));
            artistsList.add(artist);
        }

        helperLists.updateTwoListView(this,artistsList, listView);

        Button allSolButton = (Button) findViewById(R.id.btAllSolSingers);
        allSolButton.setVisibility(View.GONE);
    }

    public void back_click(View view){
        Intent intent = new Intent(SolutionSinger_Tab2.this, SingersActivity.class);
        startActivity(intent);
    }
}