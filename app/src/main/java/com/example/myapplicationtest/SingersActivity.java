package com.example.myapplicationtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplicationtest.Enums.EnumAsync;
import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.SingersLogic.Filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingersActivity extends AppCompatActivity {
    Filters filters;
    Spinner spinner1, spinner2, spinner3, spinner4;
    HelperLists helperLists=new HelperLists();
    public static List<String> geners=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singer_choice);

       //genres
        updateGenreList();
        spinner1 = findViewById(R.id.register_what_you);
        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,geners);
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

        //spinner4
     //   List<String> location = new ArrayList<String>(Arrays.asList("select","l1","l2", "l3","l4","l5","l6","l7"));
     //   spinner4 = findViewById(R.id.spinner4);

     //   ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,location);
     //   spinner4.setAdapter(locationAdapter);

    }



    public void filters_click(View view) {

        String genre2 =null;
        String loudness2 = null;
        String beat2=null;
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
      /*  if(spinner4.getSelectedItem()!=null){
            location2 =spinner4.getSelectedItem().toString();
        }*/
        boolean allChoose=checkChoise(genre2,loudness2,beat2);
        if(allChoose) { //only if all filter selected
            filters = new Filters(genre2, loudness2, beat2);
            Intent intent1 = new Intent(SingersActivity.this, ParioritySingers.class);
            intent1.putExtra("com.example.myapplicationtest.SingersLogic.Filters", filters);
            setResult(Activity.RESULT_OK, intent1);
            startActivity(intent1);
            finish();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Error Choose");
            builder.setMessage("Please select all filters");
            builder.setPositiveButton(android.R.string.yes,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    public void updateGenreList(){
        String q3=helperLists.getGenreQuery();
        //async task for getting data from db
        new AsyncHelper(SingersActivity.this,q3,"genre",
                null,null,null, EnumAsync.Genre.getEnumAsync()).execute();
        geners=HelperLists.genersHelperLists;
        geners.add(0,"select");
    }

    public void backSingerORProduct_click(View view){
        Intent intent = new Intent(SingersActivity.this, ChoiceSingerOrProduct.class);
        startActivity(intent);
    }

    public boolean checkChoise(String genre2, String loudness2, String beat2){
        if(genre2==null || loudness2==null || beat2==null ||
                genre2.equals("select") || loudness2.equals("select") ||
                beat2.equals("select") ){
          return false;
        }else{
            return true;
        }
    }


}
