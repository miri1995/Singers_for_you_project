package com.example.myapplicationtest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.myapplicationtest.Enums.EnumAsync;
import com.example.myapplicationtest.Enums.EnumsSingers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HelperLists {

    public static List<String> genersHelperLists=new ArrayList<>();
    public static List<String> goalHelperList=new ArrayList<>();
    public static List<String> topicHelperList=new ArrayList<>();
    public static List<String> instrumentHelperList = new ArrayList<>();
    public static List<String> composerIDHelperList = new ArrayList<>();
    public static List<String> composerGenreHelperList = new ArrayList<>();

    public HelperLists(){

    }

    public String getComposerId(){return "select composers.composer_id from composers";}
    public String getComposerGenre(){return "select composers.composers_genre from composers";}

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

    public boolean checkPriority(String pr1,String pr2,String pr3){
        if(!pr1.equals(pr2) && !pr1.equals(pr3) && !pr2.equals(pr3)){
            return true;
        }else
            return false;
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
        if(!geners.contains(EnumsSingers.select.getEnums())) {
            geners.add(0, EnumsSingers.select.getEnums());
        }
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
        ArrayAdapter<String> topicAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item,topics);
        spinner2.setAdapter(topicAdapter);

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
        Intent intent1 = new Intent(context, WhichArtist.class);
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

    /**
     *
     * @param alist
     * @return reverse of a list
     */
    public List<String> reverseArrayList(List<String> alist)
    {
        // Arraylist for storing reversed elements
        List<String> revArrayList = new ArrayList<String>();
        for (int i = alist.size() - 1; i >= 0; i--) {

            // Append the elements in reverse order
            revArrayList.add(alist.get(i));
        }

        // Return the reversed arraylist
        return revArrayList;
    }

    /**
     * sorted generic function that sorte map by value(sorting from big to small).
     * @param unsortMap
     * @param <K>
     * @param <V>
     * @return sorted map
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> unsortMap) {

        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>(unsortMap.entrySet());



        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;

    }

    /**
     * Update the list view in accordance 2 list input
     * @param context
     * @param listView
     * @param listView2
     */
    public void updateTwoListView(Context context,List<Artist> list, ListView listView, ListView listView2){
      /*  ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.activity_listview, list1);*/
        /*ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context,
                R.layout.activity_listview, list2);*/
     //   listView.setAdapter(adapter);

        ArtistsListAdapter adapter = new ArtistsListAdapter(context, R.layout.adapter_view_layout, list);
        listView.setAdapter(adapter);

    }

   public boolean checkSizeOfListResults(Context context, List<Artist> list, int tab){
        Maps maps=new Maps();
        String message="";
        switch (tab){
            case 1:
                message="They are arranged according to their popularity," +
                        " so that the most popular artist appears at the top of the list\n\n";
                break;
            case 2:
                message="They are arranged according to the match percentage to you," +
                        " according to your preferences so that the artist most appropriate to you appears at the top of the list\n\n";
                break;
            case 3:
                message="They are arranged according to the match percentage to you," +
                        " according to your preferences so that the artist less appropriate to you appears at the top of the list\n\n";
                break;
        }
        if(list.size()<10){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            //builder.setTitle("Success In Registration");
            builder.setMessage("Sorry we found only "+ String.valueOf(list.size())+" artists for you.\n " +
                    "Do you want that we increase the filter and get back artists with a wider match range?");
            builder.setPositiveButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //todo change the function with range
                            dialog.cancel();
                        }
                    });
            builder.setNegativeButton(android.R.string.no,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
           // Intent intent1 = new Intent(context, WhichArtist.class);
           // context.startActivity(intent1);
            return false;
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            //builder.setTitle("Success In Registration");
            builder.setMessage("We found "+ String.valueOf(list.size())+" artists that match for you.\n" +
                    message
                    +"Enjoyable use :)");
            builder.setPositiveButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }
   }
}
