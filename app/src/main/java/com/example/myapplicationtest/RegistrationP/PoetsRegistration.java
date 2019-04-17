package com.example.myapplicationtest.RegistrationP;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplicationtest.AsyncHelper;
import com.example.myapplicationtest.Enums.EnumAsync;
import com.example.myapplicationtest.HelperLists;
import com.example.myapplicationtest.R;
import com.example.myapplicationtest.SingersLogic.Filters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PoetsRegistration extends AppCompatActivity {

    Filters filters;
    Spinner spinner1, spinner2, spinner3;
    EditText name_txt;
    public static List<String> geners=new ArrayList<>();
    public static Integer lastID;
    public static Integer lastIDSong=0;
    public static String genreChoice =null;
    public static Integer genreID=0;
    private List<String> topics=new ArrayList<>();
    private List<String> goals =new ArrayList<>();
    private String topic=null;
    private String goal =null;
    private String name=null;
    HelperLists helperLists=new HelperLists();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poets_registration);

        String q3=helperLists.getGenreQuery();
        new AsyncHelper(PoetsRegistration.this,q3,"genre",null,null,null,
                EnumAsync.Genre.getEnumAsync()).execute(); //async task for getting data from db


        //categorization
        geners=HelperLists.genersHelperLists;
        geners.add(0,"select");
        spinner1 = findViewById(R.id.register_what_you);

        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,geners);
        spinner1.setAdapter(generesAdapter);

        //subject
        String q2=helperLists.getTopicQuery();
        new AsyncHelper(PoetsRegistration.this,q2,"song_topic",null,null,null,
                EnumAsync.Topic.getEnumAsync()).execute(); //async task for getting data from db
        topics=HelperLists.topicHelperList;
        topics.add(0,"select");
        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> AudienceAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,topics);
        spinner2.setAdapter(AudienceAdapter);

        //goal
        goals.clear();
        String q=helperLists.getGoalQuery();
        new AsyncHelper(PoetsRegistration.this,q,"goal",null,null,null,
                EnumAsync.Goal.getEnumAsync()).execute(); //async task for getting data from db
        goals=HelperLists.goalHelperList;
        goals.add(0,"select");
        spinner3 = findViewById(R.id.spinner3);
        ArrayAdapter<String> beatAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,goals);
        spinner3.setAdapter(beatAdapter);



        //spinner4

        name_txt = findViewById(R.id.nameAdd);


    }


    public void registration_poets_click(View view) {
        //async task for getting data from db

        name = name_txt.getText().toString();

        if(spinner1.getSelectedItem()!=null){
            genreChoice =spinner1.getSelectedItem().toString();
        }
        if(spinner2.getSelectedItem()!=null){
            topic =spinner2.getSelectedItem().toString();
        }
        if(spinner3.getSelectedItem()!=null){
            goal =spinner3.getSelectedItem().toString();
        }


        if(genreChoice==null || topic ==null || goal ==null ||
                genreChoice.equals("select") || topic.equals("select") ||
                goal.equals("select") ){
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
            String str_result=null;
            String getLastId="select poet_id from poets order by poet_id desc limit 1";


            //get id
            try {
                str_result =new AsyncHelperRegistration(PoetsRegistration.this,getLastId,"poet_id",
                        EnumAsync.LastIDPoet.getEnumAsync()).execute().get();
            }catch (ExecutionException e) {
                e.printStackTrace();
                Log.d("D","1: "+e.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.d("D","2: "+e.getMessage());
            }

            if(str_result!=null) {
                String q1 = ("INSERT INTO poets " +
                        "VALUES(\"" + lastID + "\",\"" + name + "\",null,null,\"" + genreChoice + "\",\"" + topic + "\",\"" + goal + "\")");

                //insert
                new AsyncHelperRegistration(PoetsRegistration.this, q1, "poet_id", EnumAsync.InsertSinger.getEnumAsync()).execute(); //async task for getting data from db
            }

            finish();
        }
    }




}
