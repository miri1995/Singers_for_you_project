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
        spinner1 = findViewById(R.id.register_what_you);

        //spinner2
        spinner2 = findViewById(R.id.spinner2);


        //spinner3 b
        spinner3 = findViewById(R.id.spinner3);

        helperLists.initPariority(this,spinner1,spinner2,spinner3);
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

        if(helperLists.checkSelectedItem(spinner1,this)&& helperLists.checkSelectedItem(spinner2,this)&&
                helperLists.checkSelectedItem(spinner3,this)){
            genreP2 =spinner1.getSelectedItem().toString();
            loudnessP2 =spinner2.getSelectedItem().toString();
            beatP2 =spinner3.getSelectedItem().toString();
        }

        boolean allChoose=helperLists.checkChoise(genreP2,loudnessP2,beatP2);
        if(allChoose){
            Intent intent = getIntent();
            //   if (intent1.hasExtra("com.example.myapplicationtest.Filters")) {
            ComposerFilters filters = (ComposerFilters) intent.getSerializableExtra(ComposerFilters.class.getName());
            priority = new ComposersPriority(genreP2, loudnessP2, beatP2,filters);

            Intent intent1 = new Intent(PriorityComposers.this, SolutionComposer.class);
            intent1.putExtra(ComposersPriority.class.getName(), priority);
            setResult(Activity.RESULT_OK, intent1);
            startActivity(intent1);

            finish();
        }else{
            helperLists.ErrorChoice(this);
        }
    }
}

