package com.example.ArtistsForYou.RegistrationP;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ArtistsForYou.Enums.EnumAsync;
import com.example.ArtistsForYou.HelperLists;
import com.example.ArtistsForYou.R;
import com.example.ArtistsForYou.Singers.Logic.Filters;

import java.util.concurrent.ExecutionException;

public class PoetsRegistration extends AppCompatActivity {

    Filters filters;
    Spinner spinner1, spinner2, spinner3;
    EditText name_txt;
    EditText id_txt;
    //public static Integer lastID;
    public static String genreChoice =null;


    private String topic=null;
    private String goal =null;
    private String name=null;
    private String ID=null;
    HelperLists helperLists=new HelperLists();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poets_registration);

        //name
        name_txt = findViewById(R.id.nameAdd);
        //id
        id_txt = findViewById(R.id.IDAdd);
        //genreSinger
        spinner1 = findViewById(R.id.register_what_you);
        //topic
        spinner2 = findViewById(R.id.spinner2);
        //goal
        spinner3 = findViewById(R.id.spinner3);
        //init all spinner
        helperLists.InitPoetsFilters(this,spinner1,spinner2,spinner3);
    }


    public void registration_poets_click(View view) {

        name = name_txt.getText().toString();
        ID = id_txt.getText().toString();
        if(helperLists.checkValitationID(this,ID)){
            if (helperLists.checkSelectedItem(spinner1, this) && helperLists.checkSelectedItem(spinner2, this) &&
                    helperLists.checkSelectedItem(spinner3, this)) {
                genreChoice = spinner1.getSelectedItem().toString();
                topic = spinner2.getSelectedItem().toString();
                goal = spinner3.getSelectedItem().toString();
            }

            boolean allChoose = helperLists.checkChoice(genreChoice, topic, goal);
            boolean hasDuplicateId = helperLists.HasDuplicateId(ID, "poet", this);
            if (allChoose && !hasDuplicateId) { //only if all filter selected
                InsertPoet();
            } else {
                if (hasDuplicateId) {
                    //helperLists.openDuplicateDialog(this);

                        InsertPoet();

                } else {
                    helperLists.ErrorChoice(this,R.string.errorRegistration);
                }
            }
           // finish();
        }
    }


    public void InsertPoet() {
        String succsess=null;
        String str_result=null;
       // if(str_result!=null) {
            String q1 = ("INSERT INTO poets " +
                    "VALUES(\"" + Integer.parseInt(ID) + "\",\"" + name + "\",\"" + genreChoice + "\",\"" + topic + "\",\"" + goal + "\")");

            //insert
            try {
                succsess=new AsyncHelperRegistration(PoetsRegistration.this, q1,
                        "poet_id", EnumAsync.InsertSinger.getEnumAsync()).execute().get(); //async task for getting data from db
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(succsess!=null){
                helperLists.sucsessRegister(this);
            }


       // }

    }

}





