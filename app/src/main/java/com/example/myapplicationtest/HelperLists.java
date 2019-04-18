package com.example.myapplicationtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplicationtest.Enums.EnumAsync;
import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.RegistrationP.ComposerRegistration;
import com.example.myapplicationtest.RegistrationP.PoetsRegistration;

import java.util.ArrayList;
import java.util.Arrays;
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
                genre2.equals(EnumsSingers.select.getEnums()) || loudness2.equals(EnumsSingers.select.getEnums()) ||
                beat2.equals(EnumsSingers.select.getEnums()) ){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkChoise(String genre2, String loudness2, String beat2,String check4){
        if(genre2==null || loudness2==null || beat2==null || check4==null||
                genre2.equals(EnumsSingers.select.getEnums()) || loudness2.equals(EnumsSingers.select.getEnums()) ||
                beat2.equals(EnumsSingers.select.getEnums()) || check4.equals(EnumsSingers.select.getEnums())){
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

    public List<String> updateGenreList(Context context){
        List<String> geners=new ArrayList<>();
        String q3=getGenreQuery();
        //async task for getting data from db
        new AsyncHelper(context,q3,"genre",
                null,null,null, EnumAsync.Genre.getEnumAsync()).execute();
        geners=HelperLists.genersHelperLists;
        geners.add(0,EnumsSingers.select.getEnums());
        return geners;
    }

    public List<String> updateLoudnessList(){
        List<String> loudness = new ArrayList<String>(Arrays.asList(EnumsSingers.select.getEnums(), EnumsSingers.Weak.getEnums(),
                EnumsSingers.Normal.getEnums(),EnumsSingers.Strong.getEnums()));
        return loudness;
    }

    public List<String> updateTempoList(){
        List<String> tempo = new ArrayList<String>(Arrays.asList(EnumsSingers.select.getEnums(),EnumsSingers.Slow.getEnums(),
                EnumsSingers.Medium.getEnums(),EnumsSingers.Fast.getEnums()));
        return tempo;
    }

    public List<String> updateTopicList(Context context){
        List<String> topics=new ArrayList<>();
        String q2=getTopicQuery();
        new AsyncHelper(context,q2,"song_topic",null,null,null,
                EnumAsync.Topic.getEnumAsync()).execute(); //async task for getting data from db
        topics=HelperLists.topicHelperList;
        topics.add(0,EnumsSingers.select.getEnums());
        return topics;
    }

    public List<String> updateGoalList(Context context){
        List<String> goals=new ArrayList<>();

        String q=getGoalQuery();
        new AsyncHelper(context,q,"goal",null,null,null,
                EnumAsync.Goal.getEnumAsync()).execute(); //async task for getting data from db
        goals=HelperLists.goalHelperList;
        goals.add(0,EnumsSingers.select.getEnums());
        return goals;
    }

    public List<String> updateMusicalInstrimentList(Context context){
        List<String> musicalInstrument=new ArrayList<>();
        String q2=getInstrumentQuery();
        new AsyncHelper(context,q2,"musical_instrument",null,null,null,
                EnumAsync.Instrument.getEnumAsync()).execute(); //async task for getting data from db
        musicalInstrument=HelperLists.instrumentHelperList;
        musicalInstrument.add(0,EnumsSingers.select.getEnums());
        return musicalInstrument;
    }

    public void initPariority(Context context,Spinner spinner,Spinner spinner2,Spinner spinner3){
        List<String> pr = new ArrayList<String>(Arrays.asList(EnumsSingers.select.getEnums(), EnumsSingers.High.getEnums(),
                EnumsSingers.Medium.getEnums(),EnumsSingers.Low.getEnums()));
        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item,pr);
        spinner.setAdapter(generesAdapter);
        spinner2.setAdapter(generesAdapter);
        spinner3.setAdapter(generesAdapter);

    }

    public void InitSingerFilters(Context context, Spinner spinner,Spinner spinner2,Spinner spinner3){
        List<String> geners=new ArrayList<>();
        //genre
        geners=updateGenreList(context);

        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item,geners);
        spinner.setAdapter(generesAdapter);

        //loudness
        ArrayAdapter<String> LoudnessAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item,
                updateLoudnessList());
        spinner2.setAdapter(LoudnessAdapter);

        //tempo
        ArrayAdapter<String> beatAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item,
               updateTempoList());
        spinner3.setAdapter(beatAdapter);
    }

    public void InitPoetsFilters(Context context, Spinner spinner1,Spinner spinner2,Spinner spinner3){
        List<String> geners=new ArrayList<>();
        List<String> topics=new ArrayList<>();
         List<String> goals =new ArrayList<>();
        //genre
        geners=updateGenreList(context);
        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item,geners);
        spinner1.setAdapter(generesAdapter);

        //topic
        topics=updateTopicList(context);
        ArrayAdapter<String> AudienceAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item,topics);
        spinner2.setAdapter(AudienceAdapter);

        //goal
        goals=updateGoalList(context);
        ArrayAdapter<String> beatAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item,goals);
        spinner3.setAdapter(beatAdapter);
    }

    public void InitComposersFilters(Context context, Spinner spinner1,Spinner spinner2,Spinner spinner3,Spinner spinner4){
         List<String> geners=new ArrayList<>();
         List<String> musicalInstrument =new ArrayList<>();
        //genre
        geners=updateGenreList(context);
        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item,geners);
        spinner1.setAdapter(generesAdapter);

        //loudness
        ArrayAdapter<String> LoudnessAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item,
               updateLoudnessList());
        spinner2.setAdapter(LoudnessAdapter);

        //tempo
        ArrayAdapter<String> beatAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item,
                updateTempoList());
        spinner3.setAdapter(beatAdapter);

        //musical instriment
        musicalInstrument=updateMusicalInstrimentList(context);
        ArrayAdapter<String> AudienceAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item,musicalInstrument);
        spinner4.setAdapter(AudienceAdapter);
    }

    public void sucsessRegister(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Success In Registration");
        builder.setMessage("Thanks! \n Your registration has been registered.");
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            dialog.wait(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
        Intent intent1 = new Intent(context, ChoiceSingerOrProduct.class);
        context.startActivity(intent1);

    }

    public boolean checkSelectedItem(Spinner spinner,Context context){
        if(spinner.getSelectedItem()!=null){
            return true;
        }else{
            ErrorChoice(context);
            return false;
        }
    }
}
