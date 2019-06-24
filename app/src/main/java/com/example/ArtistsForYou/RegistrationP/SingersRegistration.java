package com.example.ArtistsForYou.RegistrationP;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ArtistsForYou.Enums.EnumAsync;
import com.example.ArtistsForYou.Enums.EnumTables;
import com.example.ArtistsForYou.HelperLists;
import com.example.ArtistsForYou.Maps;
import com.example.ArtistsForYou.R;
import com.example.ArtistsForYou.Registration;

import java.util.concurrent.ExecutionException;

public class SingersRegistration extends AppCompatActivity {

    Spinner spinner1, spinner2, spinner3;
    EditText name_txt;
    EditText id_txt;

   // public static Integer lastID;
   public static Integer lastIDSong=0;
    public static String genreChoice =null;
    public static Integer genreID=0;
    private String loudness2 = null;
    private String beat2=null;
    private String name=null;
    private String id=null;
    HelperLists helperLists=new HelperLists();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singers_registration);

        //Name
        name_txt = findViewById(R.id.nameAdd);
        //id
        id_txt = findViewById(R.id.IDAdd);
        //genreSinger
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
        id = id_txt.getText().toString();
        if(helperLists.checkValitationID(this,id)){
            if (helperLists.checkSelectedItem(spinner1, this) && helperLists.checkSelectedItem(spinner2, this) &&
                    helperLists.checkSelectedItem(spinner3, this)) {
                genreChoice = spinner1.getSelectedItem().toString();
                loudness2 = spinner2.getSelectedItem().toString();
                beat2 = spinner3.getSelectedItem().toString();
            }

            boolean allChoose = helperLists.checkChoice(genreChoice, loudness2, beat2);
            boolean hasDuplicateId = helperLists.HasDuplicateId(id, "artist", this);
            if (allChoose && !hasDuplicateId) { //only if all filter selected
                try {
                    InsertSinger();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                if (hasDuplicateId) {
                    helperLists.openDuplicateDialog(this);
                } else {
                    helperLists.ErrorChoice(this,R.string.errorRegistration);
                }
            }
            // finish();
        }

    }
    public void InsertSinger() throws ExecutionException, InterruptedException {
        String str_result3Final=null,str_result2Final=null,str_resultFinal=null;
        String str_result3=null,str_result2=null,str_result=null;
        String getLastIdSongs="select "+ EnumTables.song_id.getEnums()+" from songs order by song_id desc limit 1";
        String getGenreId="select "+EnumTables.genre_id.getEnums()+" from genre where genre=\""+genreChoice+"\"";
        //String getLastId="select "+EnumTables.artist_id.getEnums()+" from artists order by artist_id desc limit 1";
        //get id
        try {
         //   str_result = new AsyncHelperRegistration(SingersRegistration.this, getLastId,
         //           "artist_id", EnumAsync.LastID.getEnumAsync()).execute().get(); //async task for getting data from db

            str_result2 = new AsyncHelperRegistration(SingersRegistration.this, getLastIdSongs,
                    "song_id", EnumAsync.LastIdSong.getEnumAsync()).execute().get();

            //get genreSinger
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
        if(str_result2!=null && str_result3!=null) {

            //query
            String q1=("INSERT INTO artists(artist_id, artist_name,artist_hotness) " + "VALUES(\""+id+"\",\""+name+"\",'0')");
            String q2=("INSERT INTO songs(song_id,song_name,song_tempo,song_loudness,song_artist_id) " +
                    "VALUES(\""+SingersRegistration.lastIDSong+"\",'new',\""+Maps.middleTempo+"\",\""+Maps.middleLoudness+"\",\""+id+"\")");
            String q3=("INSERT INTO genreartists " + "VALUES(\""+id+"\",\""+genreID+"\")");

            //async task for getting data from db
            str_resultFinal=new AsyncHelperRegistration(SingersRegistration.this, q1,
                    "artist_id", EnumAsync.InsertSinger.getEnumAsync()).execute().get();
            str_result2Final=new AsyncHelperRegistration(SingersRegistration.this, q2,
                    "song_id", EnumAsync.InsertSinger.getEnumAsync()).execute().get();
            str_result3Final=new AsyncHelperRegistration(SingersRegistration.this, q3,
                    "artist_id", EnumAsync.InsertSinger.getEnumAsync()).execute().get();
        }else{

        }
        if(str_resultFinal!=null && str_result2Final!=null && str_result3Final!=null) {
            helperLists.sucsessRegister(this);
        }
    }

    public void back_registration_click(View view){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

    public void explanationClick(View view){
        helperLists.openExplationRegistrationDialog(this);

    }
}
