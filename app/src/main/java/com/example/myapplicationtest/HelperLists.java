package com.example.myapplicationtest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.myapplicationtest.Enums.EnumAsync;

import java.util.ArrayList;
import java.util.List;

public class HelperLists {

    public static List<String> genersHelperLists=new ArrayList<>();
    public static List<String> goalHelperList=new ArrayList<>();
    public static List<String> topicHelperList=new ArrayList<>();
    public static List<String> instrumentHelperList = new ArrayList<>();

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

   public String getInstrumentQuery(){ return "select distinct musical_instrument from composers";}

    public boolean checkChoise(String genre2, String loudness2, String beat2){
        if(genre2==null || loudness2==null || beat2==null ||
                genre2.equals("select") || loudness2.equals("select") ||
                beat2.equals("select") ){
            return false;
        }else{
            return true;
        }
    }

    public void ErrorChoice(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Error Choose");
        builder.setMessage("Please select all filters");
        builder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
