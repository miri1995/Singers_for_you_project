package com.example.myapplicationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplicationtest.Logic.Filters;
import com.example.myapplicationtest.Logic.Priority;
//import assets.pair3.txt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SulationSinger  extends AppCompatActivity {

    Priority priority = new Priority();
    List<String> artists = new ArrayList<String>();
  //  private final Object mutex = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_singers);


        //   }
        Intent intent2 = getIntent();
        // if (intent2.hasExtra("com.example.myapplicationtest.Priority")) {
        priority = (Priority) intent2.getSerializableExtra("com.example.myapplicationtest.Logic.Priority");
        // }

        Query query = new Query();
      //  String q3 = query.UserInput("hip_hop", "Normal","medium", "high", "medium");
        String q3= query.UserInput(priority.getFilters().getGenre(),priority.getFilters().getLoudness(),priority.getFilters().getTempo(),priority.getPrioGenre(),priority.getPrioLoudness(),priority.getPrioTempo());
       // Connection con = DBConnection.getInstance().getConnection(); // DB connection
        artists.clear();
        DBConnection.getInstance().makeQuery(q3,artists,"artist_name");
     /*   Thread t = new Thread(() -> {

//mnk
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(q3);) {
                while (rs.next() == true) {
                    artists.add(rs.getString("genre"));
                }

            } catch (SQLException e) {
                System.out.println("ERROR executeQuery - " + e.getMessage());
            }

        });
        t.start();*/
        System.out.println(artists);
    /*    try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    DBConnection.getInstance().closeConnection();


        List<String> resultArray = artists;
        ///ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main3, resultArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.activity_list_item, android.R.id.text1, resultArray);
        ListView listView = findViewById(R.id.result_list);
        listView.setAdapter(adapter);




       /* Button button = (Button) findViewById(R.id.allSol_buttom);
        button.setOnClickListener( new View.OnClickListener()
        {
            public void onClick (View v){
                setContentView(R.layout.solution_singers);
            }
            */
    }
}