package com.example.myapplicationtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplicationtest.Logic.Filters;
import com.example.myapplicationtest.Logic.Helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingersActivity extends AppCompatActivity {
    Filters filters;
    Spinner spinner1, spinner2, spinner3, spinner4;
    public static List<String> geners=new ArrayList<>();

//b



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singer_choice);
//lko
      //  Connector connector;
      //  connector = new Connector();

      //  Connection con=DBConnection.getInstance().getConnection(); // DB connection
        String q3="select genre from genre";
          new Download(SingersActivity.this,q3,"genre","singer").execute(); //async task for getting data from db
        //geners=new Download(SingersActivity.this,q3,"genre").getList();
       // DBConnection.getInstance().makeQuery(q3,genres,"genre");
        /*Thread t = new Thread( () -> {


        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(q3);) {
            while (rs.next() == true) {
                genres.add(rs.getString("genre"));
            }

        } catch (SQLException e) {
            System.out.println("ERROR executeQuery - " + e.getMessage());
        }
        });*/
        //t.start();
        System.out.println(geners);
        Log.d("D","singer activity"+geners);
        //   List<String> l= DBConnection.getInstance().getArtists(); //TODO check here
        // connecting
        //if (!Connector.getConnection())
       //     return;
        //System.out.print(filters.getGenre());
        // executeQuery
        //connector.ExecuteQuery();
        // close the connection
        //connector.closeConnection();
      /*  try {
            Thread.sleep(1500);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }*/
      // System.out.println("Done :)");


     //  Helper helper=new Helper();
      //  List<String> genres=helper.ReadFileGenre();





        //spinner1
        geners.add(0,"select");
        spinner1 = findViewById(R.id.spinner);

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
     //   List<String> location = new ArrayList<String>(Arrays.asList("select","l1","l2", "l3","l4","l5","l6","l7"));
     //   spinner4 = findViewById(R.id.spinner4);

     //   ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,location);
     //   spinner4.setAdapter(locationAdapter);

    }




    public void filters_click(View view) {

        String genre2 =null;
        String loudness2 = null;
        String beat2=null;
       // String location2=null;

        if(spinner1.getSelectedItem()!=null){
            genre2 =spinner1.getSelectedItem().toString();
        }
        if(spinner2.getSelectedItem()!=null){
            loudness2 =spinner2.getSelectedItem().toString();
        }
        if(spinner3.getSelectedItem()!=null){
            beat2 =spinner3.getSelectedItem().toString();
        }
      /*  if(spinner4.getSelectedItem()!=null){
            location2 =spinner4.getSelectedItem().toString();
        }*/

        if(genre2==null || loudness2==null || beat2==null ||
                genre2.equals("select") || loudness2.equals("select") ||
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


            filters = new Filters(genre2, loudness2, beat2);

            //blabkajj
            Intent intent1 = new Intent(SingersActivity.this, ParioritySingers.class);
            intent1.putExtra("com.example.myapplicationtest.Logic.Filters", filters);
            setResult(Activity.RESULT_OK, intent1);
            startActivity(intent1);

            finish();
        }
    }

    public void backSingerORProduct_click(View view){
        Intent intent = new Intent(SingersActivity.this, ChoiceSingerOrProduct.class);
        startActivity(intent);
    }
}
