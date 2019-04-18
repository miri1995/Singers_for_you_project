package com.example.myapplicationtest.RegistrationP;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplicationtest.AsyncHelper;
import com.example.myapplicationtest.ComposersActivity;
import com.example.myapplicationtest.Enums.EnumAsync;
import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.HelperLists;
import com.example.myapplicationtest.Maps;
import com.example.myapplicationtest.ParioritySingers;
import com.example.myapplicationtest.R;
import com.example.myapplicationtest.SingersActivity;
import com.example.myapplicationtest.SingersLogic.Filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ComposerRegistration extends AppCompatActivity {


    Spinner spinner1, spinner2, spinner3,spinner4;
    EditText name_txt;

    public static Integer lastID;

    public static String genreChoice =null;
    public static String loudness =null;
    public static String tempo =null;
    public static String musicalInstrumentChoice=null;

    private String name=null;
    HelperLists helperLists=new HelperLists();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.composer_registration);

        //genre
        spinner1 = findViewById(R.id.genre);

        //loudness
        spinner2 = findViewById(R.id.loudness);

        //tempo
        spinner3 = findViewById(R.id.tempo);

        //musical instriment
        spinner4 = findViewById(R.id.instrument_spinner);

        //name
        name_txt = findViewById(R.id.nameAdd);
        helperLists.InitComposersFilters(this,spinner1,spinner2,spinner3,spinner4);


    }

    public void registration_composer_click(View view) {
        //async task for getting data from db

        name = name_txt.getText().toString();

        if(spinner1.getSelectedItem()!=null){
            genreChoice =spinner1.getSelectedItem().toString();
        }
        if(spinner2.getSelectedItem()!=null){
            loudness =spinner2.getSelectedItem().toString();
        }
        if(spinner3.getSelectedItem()!=null){
            tempo =spinner3.getSelectedItem().toString();
        }
        if(spinner4.getSelectedItem()!=null){
            musicalInstrumentChoice =spinner4.getSelectedItem().toString();
        }


        boolean allChoose=helperLists.checkChoise(genreChoice,loudness,tempo,musicalInstrumentChoice);
        if(allChoose) { //only if all filter selected
            InsertComposer();
        }else{
            helperLists.ErrorChoice(this);
        }

    }

    public void InsertComposer(){
        String str_result=null;
        String getLastId="select composer_id from composers order by composer_id desc limit 1";
        //get id
        try {
            str_result =new AsyncHelperRegistration(ComposerRegistration.this,getLastId,"composer_id",
                    EnumAsync.LastIDComp.getEnumAsync()).execute().get();
        }catch (ExecutionException e) {
            e.printStackTrace();
            Log.d("D","1: "+e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.d("D","2: "+e.getMessage());
        }

        if(str_result!=null) {
            Maps maps=new Maps();
            maps.middleLoudness(loudness);
            maps.middleTempo(tempo);
            String q1 = ("INSERT INTO composers " +
                    "VALUES(\"" + lastID + "\",\"" + name + "\",\"" + genreChoice + "\",\"" + musicalInstrumentChoice + "\"" +
                    ",null,\"" + Maps.middleLoudness + "\",\""+Maps.middleTempo+"\")");

            //insert
            //async task for getting data from db
            String str_result5= null;
            try {
                str_result5 = new AsyncHelperRegistration(ComposerRegistration.this, q1,
                        "composer_id", EnumAsync.InsertSinger.getEnumAsync()).execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(str_result5!=null){
                helperLists.sucsessRegister(this);
            }
        }

        finish();
    }



}
