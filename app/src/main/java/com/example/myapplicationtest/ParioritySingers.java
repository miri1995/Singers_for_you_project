package com.example.myapplicationtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.myapplicationtest.Logic.Filters;
import com.example.myapplicationtest.Logic.Priority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParioritySingers extends AppCompatActivity {
    Priority priority;
    Spinner spinner1, spinner2, spinner3, spinner4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.praiority_singers);

        //spinner1
        List<String> genresP = new ArrayList<String>(Arrays.asList("select","high","medium","low"));
        spinner1 = findViewById(R.id.spinner);

        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,genresP);
        spinner1.setAdapter(generesAdapter);

        //spinner2
        List<String> loudnessP = new ArrayList<String>(Arrays.asList("select","high","medium","low"));
        spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<String> AudienceAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,loudnessP);
        spinner2.setAdapter(AudienceAdapter);

        //spinner3
        List<String> beatP = new ArrayList<String>(Arrays.asList("select","high","medium","low"));
        spinner3 = findViewById(R.id.spinner3);

        ArrayAdapter<String> beatAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,beatP);
        spinner3.setAdapter(beatAdapter);

        //spinner4
     //   List<String> location = new ArrayList<String>(Arrays.asList("select","l1","l2", "l3","l4","l5","l6","l7"));
      //  spinner4 = findViewById(R.id.spinner4);

     //   ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,location);
      //  spinner4.setAdapter(locationAdapter);

    }
    public void second_click(View view) {

        String genreP2 =null;
        String loudnessP2 = null;
        String beatP2=null;
     //   String location2=null;

        if(spinner1.getSelectedItem()!=null){
            genreP2 =spinner1.getSelectedItem().toString();
        }
        if(spinner2.getSelectedItem()!=null){
            loudnessP2 =spinner2.getSelectedItem().toString();
        }
        if(spinner3.getSelectedItem()!=null){
            beatP2 =spinner3.getSelectedItem().toString();
        }
      /*  if(spinner4.getSelectedItem()!=null){
            location2 =spinner4.getSelectedItem().toString();
        }*/

        if(genreP2==null || loudnessP2==null || beatP2==null ||
                genreP2.equals("select") || loudnessP2.equals("select") ||
                beatP2.equals("select") ){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Error Choose");
            builder.setMessage("Please select all filters");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }else { //only if all filter selected

            Intent intent = getIntent();
            //   if (intent1.hasExtra("com.example.myapplicationtest.Filters")) {
            Filters filters = (Filters) intent.getSerializableExtra("com.example.myapplicationtest.Logic.Filters");
            priority = new Priority(genreP2, loudnessP2, beatP2,filters);

            Intent intent1 = new Intent(ParioritySingers.this, SulationSinger.class);
            intent1.putExtra("com.example.myapplicationtest.Logic.Priority", priority);
            setResult(Activity.RESULT_OK, intent1);
            startActivity(intent1);

            finish();
        }
    }
}