package com.example.myapplicationtest.Composer.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

import com.example.myapplicationtest.Composer.Logic.ComposerFilters;
import com.example.myapplicationtest.HelperLists;
import com.example.myapplicationtest.R;
import com.example.myapplicationtest.WhichArtist;

import java.util.ArrayList;
import java.util.List;


public class ComposersActivity extends AppCompatActivity{
    ComposerFilters filters;
    Spinner spinner1, spinner2, spinner3,spinner4;
    HelperLists helperLists=new HelperLists();
    public static List<String> genres=new ArrayList<>();
    public static List<String> musicalInstrument=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.composer_choice);

        spinner1 = findViewById(R.id.register_what_you);

        //loudness
        spinner2 = findViewById(R.id.spinner2);


        //tempo
        spinner3 = findViewById(R.id.spinner3);


        //musical instriment
        spinner4 = findViewById(R.id.spinner4);

        helperLists.InitComposersFilters(this,spinner1,spinner2,spinner3,spinner4);

    }



    public void composers_choice_click(View view) {

        String genre2 =null;
        String loudness2 = null;
        String beat2=null;
        String instrument2=null;
        // String location2=null;

        if(helperLists.checkSelectedItem(spinner1,this)&& helperLists.checkSelectedItem(spinner2,this)&&
                helperLists.checkSelectedItem(spinner3,this)&& helperLists.checkSelectedItem(spinner4,this)){
            genre2 =spinner1.getSelectedItem().toString();
            loudness2 =spinner2.getSelectedItem().toString();
            beat2 =spinner3.getSelectedItem().toString();
            instrument2=spinner4.getSelectedItem().toString();
        }

      /*  if(spinner4.getSelectedItem()!=null){
            location2 =spinner4.getSelectedItem().toString();
        }*/
        boolean allChoose=helperLists.checkChoice(genre2,loudness2,beat2);
        if(allChoose) {
            filters = new ComposerFilters(genre2, loudness2, beat2, instrument2);
            Intent intent1 = new Intent(ComposersActivity.this, PriorityComposers.class);
            intent1.putExtra(ComposerFilters.class.getName(), filters);
            setResult(Activity.RESULT_OK, intent1);
            startActivity(intent1);
            finish();
        }else{
            helperLists.ErrorChoice(this,R.string.errorFilters);
        }


    }



    public void backSingerORProduct_click(View view){
        Intent intent = new Intent(ComposersActivity.this, WhichArtist.class);
        startActivity(intent);
    }




}
