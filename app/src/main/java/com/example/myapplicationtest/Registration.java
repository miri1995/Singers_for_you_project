package com.example.myapplicationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;


import com.example.myapplicationtest.RegistrationP.SingersRegistration;
//import com.example.myapplicationtest.RegistrationP.PoetsRegistration;
import com.example.myapplicationtest.RegistrationP.ComposerRegistration;

public class Registration extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

    }

    public void registration_click(View view) {

        switch (view.getId()){
            case R.id.singers2:
                Intent intent = new Intent(Registration.this, SingersRegistration.class);
                startActivity(intent);
                break;
            /*case R.id.poets2:
                Intent intent2 = new Intent(Registration.this, PoetsRegistration.class);
                startActivity(intent2);
                break;*/
            case R.id.composer2:
                Intent intent3 = new Intent(Registration.this, ComposerRegistration.class);
                startActivity(intent3);
                break;

            default:
                throw new RuntimeException("Unknow button ID");
        }



     /*   String categorization2=null;
        String genre2 =null;
        String loudness2 = null;
        String beat2=null;
        String genre=null,subject=null, goal=null;
        // String location2=null;


        if(spinner2.getSelectedItem()!=null){
            loudness2 =spinner2.getSelectedItem().toString();
        }
        if(spinner3.getSelectedItem()!=null){
            beat2 =spinner3.getSelectedItem().toString();
        }




        if(categorization.getSelectedItem()!=null){
            categorization2 = categorization.getSelectedItem().toString();
            switch (categorization2){
                case "singer":
                    filters = new Filters(genre2, loudness2, beat2);
                    break;
                case "poet":
                    poetsFilters=new PoetsFilters(genre,  subject,  goal);
                    break;
                case "composer":
                    composerFilters=new ComposerFilters();
                    break;

            }
        }*/
    }


}
