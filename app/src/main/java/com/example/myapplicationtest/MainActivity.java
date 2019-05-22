package com.example.myapplicationtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplicationtest.Enums.EnumTables;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readFile("pair3.txt", EnumTables.genre.getEnums());
        readFile("topics.txt",EnumTables.topic.getEnums());
        readFile("goal.txt",EnumTables.goal.getEnums());
        HelperLists helperLists = new HelperLists();
        Map<String,List<String>> poetIdGenre = helperLists.updatePoetMap(this);

    }

    public void readFile(String fileName,String flag){
        String text="";
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        CoupleDistance coupleDistance = CoupleDistance.getInstance();
        List<List<String>> couples = new ArrayList<>();
        try {
            couples = coupleDistance.ReadFile(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        coupleDistance.countPairs(couples,flag);
    }

    public void find_click(View view) {

        Intent intent = new Intent(MainActivity.this, WhichArtist.class);
        startActivity(intent);
    }



    public void exit_click(View view) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
}

