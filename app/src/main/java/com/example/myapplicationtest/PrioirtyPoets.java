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
   HelperLists helperLists=new HelperLists();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.priority_poets);

        //categorization
        spinner1 = findViewById(R.id.register_what_you);


        //spinner2
        spinner2 = findViewById(R.id.spinner2);


        //spinner3 b
        spinner3 = findViewById(R.id.spinner3);

       helperLists.initPariority(this,spinner1,spinner2,spinner3);



    }
    public void second_click(View view) {

        String genreP2 =null;
        String subjectP2 = null;
        String goalP2=null;
        boolean pop;
        //   String location2=null;

        if(helperLists.checkSelectedItem(spinner1,this)&& helperLists.checkSelectedItem(spinner2,this)&&
                helperLists.checkSelectedItem(spinner3,this)){
            genreP2 =spinner1.getSelectedItem().toString();
            subjectP2 =spinner2.getSelectedItem().toString();
            goalP2 =spinner3.getSelectedItem().toString();
        }


            Intent intent = getIntent();
            PoetsFilters filters = (PoetsFilters) intent.getSerializableExtra(PoetsFilters.class.getName());
            poetsPriority = new PoetsPriority(genreP2, subjectP2, goalP2,filters);

            Intent intent1 = new Intent(PrioirtyPoets.this, SolutionPoets.class);
            intent1.putExtra(PoetsPriority.class.getName(), poetsPriority);
            setResult(Activity.RESULT_OK, intent1);
            startActivity(intent1);

            finish();
      //  }
    }
}


