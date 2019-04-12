package com.example.myapplicationtest.Poets;

import com.example.myapplicationtest.IQuery;



public class QueryPoets {


    public String UserInput(String genre, String subject, String goal, String language,
                            String prioGenre, String prioSubject, String prioGoal,String prioLanguage,boolean popular){

        String q="select * from poets where\n" +
                "poets.genre=\""+genre+"\""+"and poets.subject=\""+subject+"\" and poets.goal=\""+goal+"\"";


        return null;
    }


}
