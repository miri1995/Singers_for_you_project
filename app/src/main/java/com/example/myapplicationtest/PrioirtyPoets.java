package com.example.myapplicationtest;

//package com.example.myapplicationtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.Poets.PoetsPriority;
import com.example.myapplicationtest.Poets.PoetsFilters;
//import com.example.myapplicationtest.SingersLogic.Priority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrioirtyPoets extends AppCompatActivity {
    PoetsPriority poetsPriority;
    Spinner spinner1, spinner2, spinner3, spinner4;
    //Switch swPopular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.priority_poets);

        //categorization
        List<String> genresP = new ArrayList<String>(Arrays.asList("select", EnumsSingers.High.getEnums(),
                EnumsSingers.Medium.getEnums(),EnumsSingers.Low.getEnums()));
        spinner1 = findViewById(R.id.register_what_you);

        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,genresP);
        spinner1.setAdapter(generesAdapter);

        //spinner2
        List<String> subjectP = new ArrayList<String>(Arrays.asList("select",EnumsSingers.High.getEnums(),
                EnumsSingers.Medium.getEnums(),EnumsSingers.Low.getEnums()));
        spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<String> AudienceAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,subjectP);
        spinner2.setAdapter(AudienceAdapter);

        //spinner3 b
        List<String> goalP = new ArrayList<String>(Arrays.asList("select",EnumsSingers.High.getEnums(),
                EnumsSingers.Medium.getEnums(),EnumsSingers.Low.getEnums()));
        spinner3 = findViewById(R.id.spinner3);

        ArrayAdapter<String> beatAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,goalP);
        spinner3.setAdapter(beatAdapter);

        //spinner4
        //   List<String> location = new ArrayList<String>(Arrays.asList("select","l1","l2", "l3","l4","l5","l6","l7"));
        //  spinner4 = findViewById(R.id.spinner4);

        //   ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,location);
        //  spinner4.setAdapter(locationAdapter);

        //switch popular
        // List<String> genresP = new ArrayList<String>(Arrays.asList("select","high","medium","low"));
       // swPopular = findViewById(R.id.swPop);

       /* ArrayAdapter<String> popularAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,genresP);
        swPopular.setAdapter(popularAdapter);*/

    }
    public void second_click(View view) {

        String genreP2 =null;
        String subjectP2 = null;
        String goalP2=null;
        boolean pop;
        //   String location2=null;

        if(spinner1.getSelectedItem()!=null){
            genreP2 =spinner1.getSelectedItem().toString();
        }
        if(spinner2.getSelectedItem()!=null){
            subjectP2 =spinner2.getSelectedItem().toString();
        }
        if(spinner3.getSelectedItem()!=null){
            genreP2 =spinner3.getSelectedItem().toString();
        }
      /*  if(swPopular.isChecked()){
            pop=true;
        }else{
            pop=false;
        }*/
      /*  if(spinner4.getSelectedItem()!=null){
            location2 =spinner4.getSelectedItem().toString();
        }*/

        if(genreP2==null || subjectP2==null || goalP2==null ||
                genreP2.equals("select") || subjectP2.equals("select") ||
                goalP2.equals("select") ){
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
            PoetsFilters filters = (PoetsFilters) intent.getSerializableExtra("com.example.myapplicationtest.Poets.PoetsFilters");
            poetsPriority = new PoetsPriority(genreP2, subjectP2, goalP2,filters);

            Intent intent1 = new Intent(PrioirtyPoets.this, SolutionPoets.class);
            intent1.putExtra("com.example.myapplicationtest.Poets.PoetsPriority", poetsPriority);
            setResult(Activity.RESULT_OK, intent1);
            startActivity(intent1);

            finish();
        }
    }
}


