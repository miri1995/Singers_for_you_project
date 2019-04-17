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


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        CoupleDistance coupleDistance = CoupleDistance.getInstance();
        List<List<String>> GenreCouples = new ArrayList<>();
        try {
            GenreCouples = coupleDistance.ReadFile(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        coupleDistance.countPairs(GenreCouples,"genre");

        String text2="";
        try {
            InputStream is = getAssets().open("topics.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text2 = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        List<List<String>> topicCouples = new ArrayList<>();
        try {
            topicCouples = coupleDistance.ReadFile(text2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        coupleDistance.countPairs(topicCouples,"topic");

        String text3="";
        try {
            InputStream is = getAssets().open("goal.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text3 = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        List<List<String>> goalCouples = new ArrayList<>();
        try {
            goalCouples = coupleDistance.ReadFile(text3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        coupleDistance.countPairs(goalCouples,"goal");

    }

    public void readFile(String fileName){

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

