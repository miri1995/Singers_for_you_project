package com.example.myapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplicationtest.Enums.EnumAsync;
import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.Composer.ComposerFilters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ComposersActivity extends AppCompatActivity{
    ComposerFilters filters;
    Spinner spinner1, spinner2, spinner3,spinner4,spinner5;
    HelperLists helperLists=new HelperLists();
    public static List<String> genres=new ArrayList<>();
    public static List<String> musicalInstrument=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.composer_choice);
        String q3=helperLists.getGenreQuery();
        new AsyncHelper(ComposersActivity.this,q3,"genre",null,null,null,
                EnumAsync.Genre.getEnumAsync()).execute(); //async task for getting data from db
        //genres
        genres=HelperLists.genersHelperLists;
        genres.add(0,"select");
        spinner1 = findViewById(R.id.register_what_you);

        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,genres);
        spinner1.setAdapter(generesAdapter);

        //loudness
        List<String> loudness = new ArrayList<String>(Arrays.asList("select", EnumsSingers.Weak.getEnums(),
                EnumsSingers.Normal.getEnums(), EnumsSingers.Strong.getEnums()));
        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> LoudnessAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,loudness);
        spinner2.setAdapter(LoudnessAdapter);

        //tempo
        List<String> beat = new ArrayList<String>(Arrays.asList("select",
                EnumsSingers.Slow.getEnums(),EnumsSingers.Medium.getEnums(),EnumsSingers.Fast.getEnums()));
        spinner3 = findViewById(R.id.spinner3);
        ArrayAdapter<String> beatAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,beat);
        spinner3.setAdapter(beatAdapter);

        //musical instriment
        String q2=helperLists.getInstrumentQuery();
        new AsyncHelper(ComposersActivity.this,q2,"musical_instrument",null,null,null,
                EnumAsync.Instrument.getEnumAsync()).execute(); //async task for getting data from db
        musicalInstrument=HelperLists.instrumentHelperList;
        musicalInstrument.add(0,"select");
        spinner4 = findViewById(R.id.spinner4);
        ArrayAdapter<String> AudienceAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,musicalInstrument);
        spinner4.setAdapter(AudienceAdapter);

        //spinner5
        List<String> location = new ArrayList<String>(Arrays.asList("select","l1","l2", "l3","l4","l5","l6","l7"));
        spinner5 = findViewById(R.id.spinner5);

        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,location);
        spinner5.setAdapter(locationAdapter);

    }



    public void composers_choice_click(View view) {

        String genre2 =null;
        String loudness2 = null;
        String beat2=null;
        String instrument2=null;
        // String location2=null;

        if(spinner1.getSelectedItem()!=null){
            genre2 =spinner1.getSelectedItem().toString();
        }
        if(spinner2.getSelectedItem()!=null){
            loudness2 =spinner2.getSelectedItem().toString();
        }
        if(spinner3.getSelectedItem()!=null){
            beat2 =spinner3.getSelectedItem().toString();
        }
        if(spinner4.getSelectedItem()!=null){
            instrument2=spinner4.getSelectedItem().toString();
        }
      /*  if(spinner4.getSelectedItem()!=null){
            location2 =spinner4.getSelectedItem().toString();
        }*/

            filters = new ComposerFilters(genre2, loudness2, beat2,instrument2);
            Intent intent1 = new Intent(ComposersActivity.this, PriorityComposers.class);
            intent1.putExtra("com.example.myapplicationtest.Composer.ComposerFilters", filters);
            setResult(Activity.RESULT_OK, intent1);
            startActivity(intent1);
            finish();


    }



    public void backSingerORProduct_click(View view){
        Intent intent = new Intent(ComposersActivity.this, ChoiceSingerOrProduct.class);
        startActivity(intent);
    }




}
