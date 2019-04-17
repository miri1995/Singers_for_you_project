package com.example.myapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.Composer.ComposerFilters;
import com.example.myapplicationtest.Composer.ComposersPriority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class PriorityComposers extends AppCompatActivity {
    ComposersPriority priority;
    Spinner spinner1, spinner2, spinner3, spinner4;
    HelperLists helperLists=new HelperLists();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.priority_composers);

        //categorization
        List<String> genresP = new ArrayList<String>(Arrays.asList("select", EnumsSingers.High.getEnums(),
                EnumsSingers.Medium.getEnums(),EnumsSingers.Low.getEnums()));
        spinner1 = findViewById(R.id.register_what_you);

        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,genresP);
        spinner1.setAdapter(generesAdapter);

        //spinner2
        List<String> loudnessP = new ArrayList<String>(Arrays.asList("select",EnumsSingers.High.getEnums(),
                EnumsSingers.Medium.getEnums(),EnumsSingers.Low.getEnums()));
        spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<String> AudienceAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,loudnessP);
        spinner2.setAdapter(AudienceAdapter);

        //spinner3 b
        List<String> beatP = new ArrayList<String>(Arrays.asList("select",EnumsSingers.High.getEnums(),
                EnumsSingers.Medium.getEnums(),EnumsSingers.Low.getEnums()));
        spinner3 = findViewById(R.id.spinner3);

        ArrayAdapter<String> beatAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,beatP);
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
        String loudnessP2 = null;
        String beatP2=null;
        //boolean pop;
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
      /*  if(swPopular.isChecked()){
            pop=true;
        }else{
            pop=false;
        }*/
      /*  if(spinner4.getSelectedItem()!=null){
            location2 =spinner4.getSelectedItem().toString();
        }*/
        boolean allChoose=helperLists.checkChoise(genreP2,loudnessP2,beatP2);
        if(allChoose){
            Intent intent = getIntent();
            //   if (intent1.hasExtra("com.example.myapplicationtest.Filters")) {
            ComposerFilters filters = (ComposerFilters) intent.getSerializableExtra("com.example.myapplicationtest.Composer.ComposerFilters");
            priority = new ComposersPriority(genreP2, loudnessP2, beatP2,filters);

            Intent intent1 = new Intent(PriorityComposers.this, SolutionComposer.class);
            intent1.putExtra("com.example.myapplicationtest.Composer.ComposersPriority", priority);
            setResult(Activity.RESULT_OK, intent1);
            startActivity(intent1);

            finish();
        }else{
            helperLists.ErrorChoice(this);
        }
    }
}

