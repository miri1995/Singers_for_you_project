package com.example.myapplicationtest.RegistrationP;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class Helper  extends AppCompatActivity {

    public static List<String> geners=new ArrayList<>();

    public Helper(){}

    public List<String> getGeners(){return geners;}

    public void GenreQuery(){
        String q3="select genre from genre";
        //new AsyncHelper(SingersRegistration.this,q3,"genre","singer").execute(); //async task for getting data from db
        new AsyncHelperRegistration(Helper.this,q3,"genre","genre").execute();
    }
}
