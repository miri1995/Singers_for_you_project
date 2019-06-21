package com.example.ArtistsForYou.Poets.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

import com.example.ArtistsForYou.HelperLists;
import com.example.ArtistsForYou.Poets.Logic.PoetsFilters;
import com.example.ArtistsForYou.R;

public class PoetsActivity extends AppCompatActivity {
    PoetsFilters filters;
    Spinner spinner1, spinner2, spinner3;
    HelperLists helperLists=new HelperLists();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poet_choice);

        //genre
        spinner1 = findViewById(R.id.register_what_you);

        //subject
        spinner2 = findViewById(R.id.spinner2);


        //goal
        spinner3 = findViewById(R.id.spinner3);

        //init filters
       helperLists.InitPoetsFilters(this,spinner1,spinner2,spinner3);

    }
    public void poet_choise_click(View view) {

        String genre2 =null;
        String subject2 = null;
        String goal2=null;

        //check if all filters selected
        if(helperLists.checkSelectedItem(spinner1,this)&& helperLists.checkSelectedItem(spinner2,this)&&
                helperLists.checkSelectedItem(spinner3,this)){
            genre2 =spinner1.getSelectedItem().toString();
            subject2 =spinner2.getSelectedItem().toString();
            goal2 =spinner3.getSelectedItem().toString();
        }


        filters= new PoetsFilters(genre2,subject2,goal2);

        Intent intent1 = new Intent(PoetsActivity.this, PrioirtyPoets.class);//todo to change this
        intent1.putExtra(PoetsFilters.class.getName(), filters);
        setResult(Activity.RESULT_OK, intent1);
        startActivity(intent1);

        finish();
    }

}
