package com.example.myapplicationtest.RegistrationP;

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
import com.example.myapplicationtest.Enums.EnumAsync;
import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.HelperLists;
import com.example.myapplicationtest.Maps;
import com.example.myapplicationtest.PoetsActivity;
import com.example.myapplicationtest.R;
import com.example.myapplicationtest.Registration;
import com.example.myapplicationtest.SingersLogic.Filters;
import com.example.myapplicationtest.SolutionPoets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SingersRegistration extends AppCompatActivity {

    Spinner spinner1, spinner2, spinner3;
    EditText name_txt;

    public static Integer lastID;
    public static Integer lastIDSong=0;
    public static String genreChoice =null;
    public static Integer genreID=0;
    private String loudness2 = null;
    private String beat2=null;
    private String name=null;
    HelperLists helperLists=new HelperLists();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singers_registration);

        //Name
        name_txt = findViewById(R.id.nameAdd);
        //genre
        spinner1 = findViewById(R.id.register_what_you);
        //loudness
        spinner2 = findViewById(R.id.spinner2);
        //tempo
        spinner3 = findViewById(R.id.spinner3);
        //init all spinner
        helperLists.InitSingerFilters(this,spinner1,spinner2,spinner3);


    }



    public void registration_singers_click(View view) throws InterruptedException {

        name = name_txt.getText().toString();

        if(helperLists.checkSelectedItem(spinner1,this)&& helperLists.checkSelectedItem(spinner2,this)&&
                helperLists.checkSelectedItem(spinner3,this)){
            genreChoice =spinner1.getSelectedItem().toString();
            loudness2 =spinner2.getSelectedItem().toString();
            beat2 =spinner3.getSelectedItem().toString();
        }

        boolean allChoose=helperLists.checkChoise(genreChoice,loudness2,beat2);
        if(allChoose) { //only if all filter selected
            try {
                InsertSinger();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }else{
            helperLists.ErrorChoice(this);
        }
           // finish();

    }
    public void InsertSinger() throws ExecutionException, InterruptedException {
        String str_result3Final=null,str_result2Final=null,str_resultFinal=null;
        String str_result3=null,str_result2=null,str_result=null;
        String getLastIdSongs="select song_id from songs order by song_id desc limit 1";
        String getGenreId="select genre_id from genre where genre=\""+genreChoice+"\"";
        String getLastId="select artist_id from artists order by artist_id desc limit 1";
        //get id
        try {
            str_result = new AsyncHelperRegistration(SingersRegistration.this, getLastId,
                    "artist_id", EnumAsync.LastID.getEnumAsync()).execute().get(); //async task for getting data from db

            str_result2 = new AsyncHelperRegistration(SingersRegistration.this, getLastIdSongs,
                    "song_id", EnumAsync.LastIdSong.getEnumAsync()).execute().get();

            //get genre
            str_result3 = new AsyncHelperRegistration(SingersRegistration.this, getGenreId,
                    "genre_id", EnumAsync.GenreId.getEnumAsync()).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Maps maps=new Maps();
        maps.middleLoudness(loudness2);
        maps.middleTempo(beat2);

        //insert
        if(str_result!=null && str_result2!=null && str_result3!=null) {

            //query
            String q1=("INSERT INTO artists(artist_id, artist_name,artist_hotness) " + "VALUES(\""+lastID+"\",\""+name+"\",'0')");
            String q2=("INSERT INTO songs(song_id,song_name,song_tempo,song_loudness,song_artist_id) " +
                    "VALUES(\""+SingersRegistration.lastIDSong+"\",'new',\""+Maps.middleTempo+"\",\""+Maps.middleLoudness+"\",\""+lastID+"\")");
            String q3=("INSERT INTO genreartists " + "VALUES(\""+lastID+"\",\""+genreID+"\")");

            //async task for getting data from db
            str_resultFinal=new AsyncHelperRegistration(SingersRegistration.this, q1,
                    "artist_id", EnumAsync.InsertSinger.getEnumAsync()).execute().get();
            str_result2Final=new AsyncHelperRegistration(SingersRegistration.this, q2,
                    "song_id", EnumAsync.InsertSinger.getEnumAsync()).execute().get();
            str_result3Final=new AsyncHelperRegistration(SingersRegistration.this, q3,
                    "artist_id", EnumAsync.InsertSinger.getEnumAsync()).execute().get();
        }else{
            try {
                Thread.sleep(5000);//todo check if need this
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(str_resultFinal!=null && str_result2Final!=null && str_result3Final!=null) {
            helperLists.sucsessRegister(this);
        }
    }

    public void back_registration_click(View view){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }


}
