package com.example.myapplicationtest.Logic;

import android.content.res.AssetManager;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Helper {



    public List<String> ReadFileGenre(){

        

       // File sdcard = Environment.getExternalStorageDirectory();

        //Get the text file
        File file = new File("res/genres.txt");

        BufferedReader br = null;
        String genreToAdd="";
        List<String> genreList=new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(file));
            while((genreToAdd = br.readLine()) != null) {
                genreList.add(genreToAdd);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ioe) {
                System.out.println("Error in closing the BufferedReader");
            }
        }
       return genreList;
    }
}
