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
import com.example.myapplicationtest.R;
import com.example.myapplicationtest.SingersLogic.Filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SingersRegistration extends AppCompatActivity {

    Spinner spinner1, spinner2, spinner3;
    EditText name_txt;
    public static List<String> geners=new ArrayList<>();
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


        String q3=helperLists.getGenreQuery();

        new AsyncHelper(SingersRegistration.this,q3,"genre",null,null,null,EnumAsync.Genre.getEnumAsync()).execute();
        geners= HelperLists.genersHelperLists;
        geners.add(0,"select");
        spinner1 = findViewById(R.id.register_what_you);

        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,geners);
        spinner1.setAdapter(generesAdapter);

        //spinner2
        List<String> loudness = new ArrayList<String>(Arrays.asList("select", EnumsSingers.Weak.getEnums(),
                EnumsSingers.Normal.getEnums(),EnumsSingers.Strong.getEnums()));
        spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<String> LoudnessAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,loudness);
        spinner2.setAdapter(LoudnessAdapter);

        //spinner3
        List<String> beat = new ArrayList<String>(Arrays.asList("select",EnumsSingers.Slow.getEnums(),
               EnumsSingers.Medium.getEnums(),EnumsSingers.Fast.getEnums()));
        spinner3 = findViewById(R.id.spinner3);

        ArrayAdapter<String> beatAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,beat);
        spinner3.setAdapter(beatAdapter);

        //spinner4

           name_txt = findViewById(R.id.nameAdd);


    }



    public void registration_singers_click(View view) throws InterruptedException {

        name = name_txt.getText().toString();

        if(spinner1.getSelectedItem()!=null){
            genreChoice =spinner1.getSelectedItem().toString();
        }
        if(spinner2.getSelectedItem()!=null){
            loudness2 =spinner2.getSelectedItem().toString();
        }
        if(spinner3.getSelectedItem()!=null){
            beat2 =spinner3.getSelectedItem().toString();
        }


        if(genreChoice==null || loudness2==null || beat2==null ||
                genreChoice.equals("select") || loudness2.equals("select") ||
                beat2.equals("select") ){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Error Choose");
            builder.setMessage("Please select all filters");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }else { //only if all filter selected
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
                Log.d("D","1: "+e.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.d("D","2: "+e.getMessage());
            }


            Maps maps=new Maps();
            maps.middleLoudness(loudness2);
            maps.middleTempo(beat2);

           //insert
            if(str_result!=null && str_result2!=null && str_result3!=null) {
                String q1=("INSERT INTO artists(artist_id, artist_name,artist_hotness) " + "VALUES(\""+lastID+"\",\""+name+"\",'0')");
                String q2=("INSERT INTO songs(song_id,song_name,song_tempo,song_loudness,song_artist_id) " +
                        "VALUES(\""+SingersRegistration.lastIDSong+"\",'new',\""+Maps.middleTempo+"\",\""+Maps.middleLoudness+"\",\""+lastID+"\")");
                String q3=("INSERT INTO genreartists " + "VALUES(\""+lastID+"\",\""+genreID+"\")");
                new AsyncHelperRegistration(SingersRegistration.this, q1, "artist_id", EnumAsync.InsertSinger.getEnumAsync()).execute(); //async task for getting data from db
                new AsyncHelperRegistration(SingersRegistration.this, q2, "song_id", EnumAsync.InsertSinger.getEnumAsync()).execute(); //async task for getting data from db
                new AsyncHelperRegistration(SingersRegistration.this, q3, "artist_id", EnumAsync.InsertSinger.getEnumAsync()).execute(); //async task for getting data from db
            }else{
                Thread.sleep(5000);//todo check if need this
            }
            System.out.println(lastIDSong);
           // filters = new Filters(genre2, loudness2, beat2);

            //blabkajj
          /*  Intent intent1 = new Intent(SingersActivity.this, ParioritySingers.class);
            intent1.putExtra("com.example.myapplicationtest.SingersLogic.Filters", filters);
            setResult(Activity.RESULT_OK, intent1);
            startActivity(intent1);*/

            finish();
        }
    }


}
