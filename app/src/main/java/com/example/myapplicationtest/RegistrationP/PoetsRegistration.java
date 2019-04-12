package com.example.myapplicationtest.RegistrationP;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplicationtest.Maps;
import com.example.myapplicationtest.R;
import com.example.myapplicationtest.SingersLogic.Filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PoetsRegistration extends AppCompatActivity {

    Filters filters;
    Spinner spinner1, spinner2, spinner3;
    EditText name_txt;
   //Helper helper=new Helper();
    public static   List<String> geners=new ArrayList<>();
//    public static Integer lastID;
//    public static Integer lastIDSong;
//    public static String genreChoice =null;
//    public static Integer genreID=0;
//    private String loudness2 = null;
//    private String beat2=null;
//    private String name=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poets_registration);



        String q3="select genre from genre";

        new AsyncHelperRegistration(PoetsRegistration.this,q3,"genre","genre_poet").execute();
        geners.add(0,"select");
        spinner1 = findViewById(R.id.register_what_you);

        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,geners);
        spinner1.setAdapter(generesAdapter);

        //spinner2
        List<String> loudness = new ArrayList<String>(Arrays.asList("select","Weak","Normal","Strong"));
        spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<String> LoudnessAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,loudness);
        spinner2.setAdapter(LoudnessAdapter);

        //spinner3
        List<String> beat = new ArrayList<String>(Arrays.asList("select","Slow","Medium","Fast"));
        spinner3 = findViewById(R.id.spinner3);

        ArrayAdapter<String> beatAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,beat);
        spinner3.setAdapter(beatAdapter);

        //spinner4

        name_txt = findViewById(R.id.nameAdd);


    }







//    public void registration_poets_click(View view) {
//
//
//        name = name_txt.getText().toString();
//
//        if(spinner1.getSelectedItem()!=null){
//            genreChoice =spinner1.getSelectedItem().toString();
//        }
//        if(spinner2.getSelectedItem()!=null){
//            loudness2 =spinner2.getSelectedItem().toString();
//        }
//        if(spinner3.getSelectedItem()!=null){
//            beat2 =spinner3.getSelectedItem().toString();
//        }
//
//
//        if(genreChoice==null || loudness2==null || beat2==null ||
//                genreChoice.equals("select") || loudness2.equals("select") ||
//                beat2.equals("select") ){
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setCancelable(true);
//            builder.setTitle("Error Choose");
//            builder.setMessage("Please select all filters");
//            builder.setPositiveButton("Confirm",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
//
//            AlertDialog dialog = builder.create();
//            dialog.show();
//        }else { //only if all filter selected
//            String getLastId="select artist_id from artists order by artist_id desc limit 1";
//            String getLastIdSongs="select song_id from songs order by song_id desc limit 1";
//            String getGenreId="select genre_id from genre where genre=\""+genreChoice+"\"";
//
//            //get id
//            new AsyncHelperRegistration(SingersRegistration.this,getLastId,"artist_id","lastId").execute(); //async task for getting data from db
//            new AsyncHelperRegistration(SingersRegistration.this,getLastIdSongs,"song_id","lastIdSong").execute();
//
//            //get genre
//            new AsyncHelperRegistration(SingersRegistration.this,getGenreId,"genre_id","GenreId").execute();
//
//            System.out.println(genreID);
//            Maps maps=new Maps();
//            maps.middleLoudness(loudness2);
//            maps.middleTempo(beat2);
//            String q1=("INSERT INTO artists(artist_id, artist_name,artist_hotness) " + "VALUES(\""+lastID+"\",\""+name+"\",'0')");
//            String q2=("INSERT INTO songs(song_id,song_name,song_tempo,song_loudness,song_artist_id) " +
//                    "VALUES(\""+SingersRegistration.lastIDSong+"\",'new',\""+Maps.middleTempo+"\",\""+Maps.middleLoudness+"\",\""+lastID+"\")");
//            String q3=("INSERT INTO genreartists " + "VALUES(\""+lastID+"\",\""+genreID+"\")");;
//
//            //insert
//            new AsyncHelperRegistration(SingersRegistration.this,q1,"artist_id","insertSinger").execute(); //async task for getting data from db
//            new AsyncHelperRegistration(SingersRegistration.this,q2,"song_id","insertSinger").execute(); //async task for getting data from db
//            new AsyncHelperRegistration(SingersRegistration.this,q3,"artist_id","insertSinger").execute(); //async task for getting data from db
//
//            System.out.println(lastIDSong);
//            // filters = new Filters(genre2, loudness2, beat2);
//
//            //blabkajj
//           /* Intent intent1 = new Intent(SingersActivity.this, ParioritySingers.class);
//            intent1.putExtra("com.example.myapplicationtest.SingersLogic.Filters", filters);
//            setResult(Activity.RESULT_OK, intent1);
//            startActivity(intent1);*/
//
//            finish();
//        }
}
