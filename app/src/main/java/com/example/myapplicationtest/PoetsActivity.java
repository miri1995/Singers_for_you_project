package com.example.myapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplicationtest.Enums.EnumAsync;
import com.example.myapplicationtest.Poets.PoetsFilters;
import com.example.myapplicationtest.SingersLogic.Filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PoetsActivity extends AppCompatActivity {
    PoetsFilters filters;
    Spinner spinner1, spinner2, spinner3, spinner4;
    public static List<String> genres=new ArrayList<>();
    public static List<String> goals=new ArrayList<>();
    public static List<String> topics=new ArrayList<>();
    HelperLists helperLists=new HelperLists();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poet_choice);


        //categorization

        spinner1 = findViewById(R.id.register_what_you);

        //subject
        spinner2 = findViewById(R.id.spinner2);


        //goal
        spinner3 = findViewById(R.id.spinner3);
       helperLists.InitPoetsFilters(this,spinner1,spinner2,spinner3);

        //spinner4
        List<String> location = new ArrayList<String>(Arrays.asList("select","l1","l2", "l3","l4","l5","l6","l7"));
        spinner4 = findViewById(R.id.spinner4);
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,location);
        spinner4.setAdapter(locationAdapter);

    }
    public void poet_choise_click(View view) {

        String genre2 =null;
        String subject2 = null;
        String goal2=null;
        String location2=null;

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

    public void backSingerORProduct_click(View view){
        Intent intent = new Intent(PoetsActivity.this, ChoiceSingerOrProduct.class);
        startActivity(intent);
    }
}
