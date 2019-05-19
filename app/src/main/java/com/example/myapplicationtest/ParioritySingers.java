package com.example.myapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.myapplicationtest.SingersLogic.Filters;
import com.example.myapplicationtest.SingersLogic.Priority;

public class ParioritySingers extends AppCompatActivity {
    Priority priority;
    Spinner spinner1, spinner2, spinner3, spinner4;
   // Switch swPopular;
    HelperLists helperLists=new HelperLists();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.praiority_singers);

        //categorization
        spinner1 = findViewById(R.id.register_what_you);


        //spinner2
        spinner2 = findViewById(R.id.spinner2);


        //spinner3 b
        spinner3 = findViewById(R.id.spinner3);

        helperLists.initPariority(this,spinner1,spinner2,spinner3);

        //spinner4
       // swPopular = findViewById(R.id.swPop);



    }
    public void second_click(View view) {

        String genreP2 =null;
        String loudnessP2 = null;
        String beatP2=null;
        boolean pop;
     //   String location2=null;

        if(helperLists.checkSelectedItem(spinner1,this)&& helperLists.checkSelectedItem(spinner2,this)&&
                helperLists.checkSelectedItem(spinner3,this)){
            genreP2 =spinner1.getSelectedItem().toString();
            loudnessP2 =spinner2.getSelectedItem().toString();
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
        boolean diffrentPriority=helperLists.checkPriority(genreP2,loudnessP2,beatP2);
        if(allChoose && diffrentPriority){
            Intent intent = getIntent();
            //   if (intent1.hasExtra("com.example.myapplicationtest.Filters")) {
            Filters filters = (Filters) intent.getSerializableExtra(Filters.class.getName());
            priority = new Priority(genreP2, loudnessP2, beatP2,filters,true); //todo delete pop

            Intent intent1 = new Intent(ParioritySingers.this, SulationSinger2.class);
            intent1.putExtra(Priority.class.getName(), priority);
            setResult(Activity.RESULT_OK, intent1);
           // Intent intent2 = new Intent(ParioritySingers.this, SulationSinger2.class);
            startActivity(intent1);

            finish();
        }else{
            helperLists.ErrorChoice(this);
        }
    }
}