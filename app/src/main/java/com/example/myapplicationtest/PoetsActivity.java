package com.example.myapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplicationtest.Enums.EnumAsync;
import com.example.myapplicationtest.Poets.PoetsFilters;
import com.example.myapplicationtest.SingersLogic.Filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PoetsActivity extends AppCompatActivity {
    PoetsFilters filters;
    Spinner spinner1, spinner2, spinner3, spinner4;
    public static List<String> genres=new ArrayList<>();
    public static List<String> goals=new ArrayList<>();
    public static List<String> topics=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poet_choice);
        String q3="select genre from genre";
        new AsyncHelper(PoetsActivity.this,q3,"genre",null,null,null,
                EnumAsync.Genre.getEnumAsync()).execute(); //async task for getting data from db


        //categorization
        genres=HelperLists.genersHelperLists;
        genres.add(0,"select");
        spinner1 = findViewById(R.id.register_what_you);

        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,genres);
        spinner1.setAdapter(generesAdapter);

        //subject
        String q2="select distinct song_topic from poets";
        new AsyncHelper(PoetsActivity.this,q2,"song_topic",null,null,null,
                EnumAsync.Topic.getEnumAsync()).execute(); //async task for getting data from db
        topics=HelperLists.topicHelperList;
        topics.add(0,"select");
        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> AudienceAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,topics);
        spinner2.setAdapter(AudienceAdapter);

        //goal
        goals.clear();
        String q="select distinct goal from poets";
        new AsyncHelper(PoetsActivity.this,q,"goal",null,null,null,
                EnumAsync.Goal.getEnumAsync()).execute(); //async task for getting data from db
        goals=HelperLists.goalHelperList;
        goals.add(0,"select");
        spinner3 = findViewById(R.id.spinner3);
        ArrayAdapter<String> beatAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,goals);
        spinner3.setAdapter(beatAdapter);

        //spinner4
        List<String> location = new ArrayList<String>(Arrays.asList("select","l1","l2", "l3","l4","l5","l6","l7"));
        spinner4 = findViewById(R.id.spinner4);

        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,location);
        spinner4.setAdapter(locationAdapter);

    }
    public void poet_choise_click(View view) {

        String genre2 =null;
        String subject2 = null;
        String goal2=null;
        String location2=null;

        if(spinner1.getSelectedItem()!=null){
            genre2 =spinner1.getSelectedItem().toString();
        }
        if(spinner2.getSelectedItem()!=null){
            subject2 =spinner2.getSelectedItem().toString();
        }
        if(spinner3.getSelectedItem()!=null){
            goal2 =spinner3.getSelectedItem().toString();
        }
      /*  if(spinner4.getSelectedItem()!=null){
            location2 =spinner4.getSelectedItem().toString();
        }*/

         filters= new PoetsFilters(genre2,subject2,goal2);

        Intent intent1 = new Intent(PoetsActivity.this, ParioritySingers.class);//todo to change this
        intent1.putExtra("com.example.myapplicationtest.Filters", filters);
        setResult(Activity.RESULT_OK, intent1);
        startActivity(intent1);

        finish();
    }

    public void backSingerORProduct_click(View view){
        Intent intent = new Intent(PoetsActivity.this, ChoiceSingerOrProduct.class);
        startActivity(intent);
    }
}
