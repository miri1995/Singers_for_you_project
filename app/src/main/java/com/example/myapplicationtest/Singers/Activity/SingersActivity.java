package com.example.myapplicationtest.Singers.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

import com.example.myapplicationtest.HelperLists;
import com.example.myapplicationtest.R;
import com.example.myapplicationtest.Singers.Logic.Filters;
import com.example.myapplicationtest.WhichArtist;

import java.util.ArrayList;
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
        spinner1 = findViewById(R.id.register_what_you);

        //loudness
        spinner2 = findViewById(R.id.spinner2);

        //tempo
        spinner3 = findViewById(R.id.spinner3);
        //init all spinner
        helperLists.InitSingerFilters(this,spinner1,spinner2,spinner3);

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

        if(helperLists.checkSelectedItem(spinner1,this)&& helperLists.checkSelectedItem(spinner2,this)&&
                helperLists.checkSelectedItem(spinner3,this)){
            genre2 =spinner1.getSelectedItem().toString();
            loudness2 =spinner2.getSelectedItem().toString();
            beat2 =spinner3.getSelectedItem().toString();
        }

      /*  if(spinner4.getSelectedItem()!=null){
            location2 =spinner4.getSelectedItem().toString();
        }*/
        boolean allChoose=helperLists.checkChoice(genre2,loudness2,beat2);
        if(allChoose) { //only if all filter selected
            filters = new Filters(genre2, loudness2, beat2);
            Intent intent1 = new Intent(SingersActivity.this, PrioritySingers.class);
            intent1.putExtra(Filters.class.getName(), filters);
            setResult(Activity.RESULT_OK, intent1);
            startActivity(intent1);
            finish();
        }else{
           helperLists.ErrorChoice(this,R.string.errorFilters);
        }

    }


    public void backSingerORProduct_click(View view){
        Intent intent = new Intent(this, WhichArtist.class);
        startActivity(intent);
    }




}
