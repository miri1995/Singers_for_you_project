package com.example.ArtistsForYou.RegistrationP;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ArtistsForYou.Enums.EnumAsync;
import com.example.ArtistsForYou.HelperLists;
import com.example.ArtistsForYou.Maps;
import com.example.ArtistsForYou.R;
import com.example.ArtistsForYou.Registration;

import java.util.concurrent.ExecutionException;

public class ComposerRegistration extends AppCompatActivity {


    Spinner spinner1, spinner2, spinner3,spinner4;
    EditText name_txt;
    EditText id_txt;


   // public static Integer lastID;

    public static String genreChoice =null;
    public static String loudness =null;
    public static String tempo =null;
    public static String musicalInstrumentChoice=null;
    public static int count=0;

    private String name=null;
    private String id=null;
    HelperLists helperLists=new HelperLists();
    //Log.d("D","Entered on create");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.composer_registration);
        Log.d("D","Entered on create");
        //genreSinger
        spinner1 = findViewById(R.id.genre);

        //loudness
        spinner2 = findViewById(R.id.loudness);

        //tempo
        spinner3 = findViewById(R.id.tempo);

        //musical instriment
        spinner4 = findViewById(R.id.instrument_spinner);

        //name
        name_txt = findViewById(R.id.nameAdd);

        //id
        id_txt = findViewById(R.id.IDAdd);
        helperLists.InitComposersFilters(this,spinner1,spinner2,spinner3,spinner4);


    }

    public void registration_composer_click(View view) {

        name = name_txt.getText().toString();
        id = id_txt.getText().toString();
        if(helperLists.checkValitationID(this,id)){

            if (helperLists.checkSelectedItem(spinner1, this) && helperLists.checkSelectedItem(spinner2, this) &&
                    helperLists.checkSelectedItem(spinner3, this) && helperLists.checkSelectedItem(spinner4, this)) {
                genreChoice = spinner1.getSelectedItem().toString();
                loudness = spinner2.getSelectedItem().toString();
                tempo = spinner3.getSelectedItem().toString();
                musicalInstrumentChoice = spinner4.getSelectedItem().toString();
            }

            boolean allChoose = helperLists.checkChoice(genreChoice, loudness, tempo, musicalInstrumentChoice);
            boolean hasDuplicateId = helperLists.HasDuplicateId(id, "composer", this);
            if (allChoose && !hasDuplicateId) { //only if all filter selected
                InsertComposer();
            } else {
                if (hasDuplicateId && count==0) {
                   // helperLists.openDuplicateDialog(this);
                   // count++;
                    InsertComposer();
                } else if(!hasDuplicateId) {
                    helperLists.ErrorChoice(this,R.string.errorRegistration);
                }
            }

        }

    }

    public void InsertComposer(){
        //if(str_result!=null) {
            Maps maps=new Maps();
            maps.middleLoudness(loudness);
            maps.middleTempo(tempo);
            String q1 = ("INSERT INTO composers " +
                    "VALUES(\"" + Integer.parseInt(id) + "\",\"" + name + "\",\"" + genreChoice + "\",\"" + musicalInstrumentChoice + "\"" +
                    ",\"" + Maps.middleLoudness + "\",\""+Maps.middleTempo+"\")");

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
       // }

      //  finish();
    }
    public void back_registration_click(View view){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
    public void explanationClick(View view){
        helperLists.openExplationRegistrationDialog(this);

    }

}
