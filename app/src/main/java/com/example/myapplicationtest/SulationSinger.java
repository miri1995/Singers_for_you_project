package com.example.myapplicationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplicationtest.Logic.Priority;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SulationSinger  extends AppCompatActivity {
    Filters filters=new Filters();
    Priority priority;
    List<String> artists = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Query query =new Query();
        String q3= query.UserInput(filters.getGenre(),filters.getLoudness(),priority.getPrioLoudness(),filters.getTempo(),priority.getPrioTempo());
        Connection con = DBConnection.getInstance().getConnection(); // DB connection
        Thread t = new Thread( () -> {
            artists.clear();

            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(q3);) {
                while (rs.next() == true) {
                    artists.add(rs.getString("genre"));
                }

            } catch (SQLException e) {
                System.out.println("ERROR executeQuery - " + e.getMessage());
            }
        });
        t.start();
        System.out.println(artists);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }




        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_singers);



        Intent intent1 = getIntent();
        if (intent1.hasExtra("com.example.myapplicationtest.Filters")) {
            filters = (Filters) intent1.getSerializableExtra("com.example.myapplicationtest.Filters");
        }

        List<String> resultArray = artists;
        //ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main3, resultArray);
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
        });*/

    }

    public void back_click(View view) {
        Intent intent = new Intent(SulationSinger.this, ParioritySingers.class);
        startActivity(intent);
    }
}
