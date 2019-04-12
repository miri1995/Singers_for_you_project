package com.example.myapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplicationtest.SingersLogic.Filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PoetsActivity extends AppCompatActivity {
    Filters filters;
    Spinner spinner1, spinner2, spinner3, spinner4;
    public static List<String> genres=new ArrayList<>();
    public static List<String> goals=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poet_choice);
        String q3="select genre from genre";
        new AsyncHelper(PoetsActivity.this,q3,"genre",null,null,null,"genre").execute(); //async task for getting data from db


        //categorization
        genres=HelperLists.genersHelperLists;
        genres.add(0,"select");
        spinner1 = findViewById(R.id.register_what_you);

        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,genres);
        spinner1.setAdapter(generesAdapter);

        //subject
        List<String> audience = new ArrayList<String>(Arrays.asList("select","a1","a2", "a3","a4","a5","a6","a7"));
        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> AudienceAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,audience);
        spinner2.setAdapter(AudienceAdapter);

        //goal
        goals.clear();
        String q="select distinct goal from poets";
        new AsyncHelper(PoetsActivity.this,q,"goal",null,null,null,"goal").execute(); //async task for getting data from db
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
        String target_Audience2 = null;
        String beat2=null;
        String location2=null;

        if(spinner1.getSelectedItem()!=null){
            genre2 =spinner1.getSelectedItem().toString();
        }
        if(spinner2.getSelectedItem()!=null){
            target_Audience2 =spinner2.getSelectedItem().toString();
        }
        if(spinner3.getSelectedItem()!=null){
            beat2 =spinner3.getSelectedItem().toString();
        }
        if(spinner4.getSelectedItem()!=null){
            location2 =spinner4.getSelectedItem().toString();
        }

        filters = new Filters(genre2,target_Audience2,beat2);

        Intent intent1 = new Intent(PoetsActivity.this, ParioritySingers.class);
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
