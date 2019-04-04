package com.example.myapplicationtest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

         final String host = "35.225.34.63";
         final String port = "3306";
         final String schema = "dbProject";
         final String user = "root";
         final String password = "0542015460mb";
         final String url = "jdbc:mysql://" + host + ":" + port + "/" + schema + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Israel";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // DBConnection.getInstance().getConnection(); // DB connection

        String text="";
        try {
            InputStream is = getAssets().open("pair3.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        GenreDistance genreDistance = GenreDistance.getInstance();
        List<List<String>> GenreCouples = new ArrayList<>();
        try {
            GenreCouples = genreDistance.ReadFile(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        genreDistance.countPairs(GenreCouples);

    }

    public void find_click(View view) {
        Intent intent = new Intent(MainActivity.this, ChoiceSingerOrProduct.class);
        startActivity(intent);
    }



    public void exit_click(View view) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
}

