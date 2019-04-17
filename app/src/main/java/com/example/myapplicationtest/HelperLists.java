package com.example.myapplicationtest;

import com.example.myapplicationtest.Enums.EnumAsync;

import java.util.ArrayList;
import java.util.List;

public class HelperLists {

    public static List<String> genersHelperLists=new ArrayList<>();
    public static List<String> goalHelperList=new ArrayList<>();
    public static List<String> topicHelperList=new ArrayList<>();

    public HelperLists(){

    }



   public String getGenreQuery(){
        return "select genre from genre";
   }

   public String getTopicQuery(){
        return "select distinct song_topic from poets";
   }

   public String getGoalQuery(){
        return "select distinct goal from poets";
   }


}
