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
import com.example.myapplicationtest.RegistrationP.AsyncHelperRegistration;
import com.example.myapplicationtest.RegistrationP.SingersRegistration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class HelperLists {

    public static List<String> genersHelperLists=new ArrayList<>();
    public static List<String> goalHelperList=new ArrayList<>();
    public static List<String> topicHelperList=new ArrayList<>();
    public static List<String> instrumentHelperList = new ArrayList<>();
   // public static List<String> composerIDHelperList = new ArrayList<>();
    //public static List<String> composerGenreHelperList = new ArrayList<>();
    public static Map<String,List<String>> poetIdTopic = new HashMap();
    public static Map<String,List<String>> poetIdGenre = new HashMap();
    public static Map<String,List<String>> poetIdGoal = new HashMap();
    public static Map<String,List<String>> composerIdGenre = new HashMap();
    //public static List<String> poetGoalList = new ArrayList<>();
    public static List<String> DuplicateId_Sol=new ArrayList<>();
    public HelperLists(){

    }

   // public String getComposerId(){return "select composers.composer_id from composers";}
    //public String getComposerGenre(){return "select composers.composers_genre from composers";}

    public String getRelevantPoets() {return "select poet_id,genre,song_topic,goal FROM poets order by poet_id";}

    public String getRelevantComposers() {return "select composer_id,composers_genre FROM composers order by composer_id";}

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

    public boolean checkChoice(String genre2, String loudness2, String beat2){
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



    public boolean checkChoice(String genre2, String loudness2, String beat2, String check4){
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
        String result=null;
        if(HelperLists.genersHelperLists.size()==0) {
            //async task for getting data from db
            try {
                result=new AsyncHelper(context, q3, "genre",
                        null, null, null, EnumAsync.Genre.getEnumAsync()).execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(result!=null) {
                geners = HelperLists.genersHelperLists;
                // if(!geners.contains(EnumsSingers.select.getEnums())) {
                geners.add(0, EnumsSingers.select.getEnums());
            }
        }else{
            geners = HelperLists.genersHelperLists;
        }
        //}
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
        String result=null;
        if(HelperLists.topicHelperList.size()==0) {
            try {
                result= new AsyncHelper(context, q2, "song_topic", null, null, null,
                         EnumAsync.Topic.getEnumAsync()).execute().get(); //async task for getting data from db
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (result!=null) {
                topics = HelperLists.topicHelperList;
                topics.add(0, EnumsSingers.select.getEnums());
            }
        }else{
            topics = HelperLists.topicHelperList;
        }
        return topics;
    }

    public List<String> updateGoalList(Context context){
        List<String> goals=new ArrayList<>();
        String result=null;
        String q=getGoalQuery();
        if(HelperLists.goalHelperList.size()==0) {
            try {
                result= new AsyncHelper(context, q, "goal", null, null, null,
                         EnumAsync.Goal.getEnumAsync()).execute().get(); //async task for getting data from db
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(result!=null) {
                goals = HelperLists.goalHelperList;
                goals.add(0, EnumsSingers.select.getEnums());
            }
        }else{
            goals = HelperLists.goalHelperList;
        }
        return goals;
    }

    public List<String> updateMusicalInstrimentList(Context context){
        List<String> musicalInstrument=new ArrayList<>();
        String q2=getInstrumentQuery();
        String result=null;
        if(HelperLists.instrumentHelperList.size()==0) {
            try {
                result= new AsyncHelper(context, q2, "musical_instrument", null, null, null,
                         EnumAsync.Instrument.getEnumAsync()).execute().get(); //async task for getting data from db
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(result!=null) {
                musicalInstrument = HelperLists.instrumentHelperList;
                musicalInstrument.add(0, EnumsSingers.select.getEnums());
            }
        }else{
            musicalInstrument = HelperLists.instrumentHelperList;
        }
        return musicalInstrument;
    }

    public boolean updatePoetMap(Context context){
        //Map<String,List<String>> poetIdGenre2 = new HashMap<>();
        String result=null;
        String q2= getRelevantPoets();
        try {
            result=new AsyncLearnedData(context,q2,"poet_id","genre","song_topic","goal",
                    EnumAsync.RelevantPoets.getEnumAsync()).execute().get(); //async task for getting data from db
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(result!=null){
            return true;
        }
        return false;

        //poetIdGenre2 = HelperLists.poetIdGenre;
        //return poetIdGenre2;
    }

    public boolean updateComposersMap(Context context){
        //Map<String,List<String>> poetIdGenre2 = new HashMap<>();
        String q2= getRelevantComposers();
        String result=null;
        try {
            result=new AsyncLearnedData(context,q2,"composer_id","composers_genre",null,null,
                    EnumAsync.RelevantComposers.getEnumAsync()).execute().get(); //async task for getting data from db
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(result!=null){
            return true;
        }
        return false;
        //poetIdGenre2 = HelperLists.poetIdGenre;
        //return poetIdGenre2;
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
        //genreSinger
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
        //genreSinger
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
        //genreSinger
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
        builder.setTitle("Successful Registration");
        builder.setMessage("Thanks! \n You are now registered.");
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
     *
     */
    public void updateTwoListView(Context context,List<Artist> list, ListView listView){
      /*  ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.activity_listview, list1);*/
        /*ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context,
                R.layout.activity_listview, list2);*/
     //   listView.setAdapter(adapter);

        ArtistsListAdapter adapter = new ArtistsListAdapter(context, R.layout.adapter_view_layout, list);
        listView.setAdapter(adapter);

    }

   public boolean checkSizeOfListResults(Context context, List<Artist> list, int tab,int counter){
        Maps maps=new Maps();
        String message="";
        switch (tab){
            case 1:
                message="They are arranged according to their popularity," +
                        " so that the most popular artist appears at the top of the list\n\n";
                break;
            case 2:
                message="They are arranged according to the percentage of matching to you," +
                        " based on your preferences so that the best artist for you will appear at the top of the list\n\n";
                break;
            case 3:
                message="They are arranged according to their popularity," +
                        " so that the least popular artist appears at the top of the list\n\n";
                break;
        }

        if(list.size()<10 && counter==0){
           /* AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
            dialog.show();*/
           // Intent intent1 = new Intent(context, WhichArtist.class);
           // context.startActivity(intent1);
            return false;
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            //builder.setTitle("Success In Registration");
            builder.setMessage("We have found "+ String.valueOf(list.size())+" artists who are for you.\n" +
                    message
                    +"Good luck :)");
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

   public <T> LinkedHashMap<String,Integer> createMap(List<String> key,List<Double> value){
       LinkedHashMap<String,Integer> map= new LinkedHashMap<>();
       for(int i = 0; i< key.size(); i++){
           map.put(key.get(i), value.get(i).intValue());
       }
       return map;
   }

   public void openExplationDialog(Context context){

      // context.setContentView(R.v.solution_singers);

       AlertDialog.Builder builder = new AlertDialog.Builder(context);
       builder.setCancelable(true);
       builder.setTitle("Explanation priority");
       builder.setMessage(R.string.explationPriority);
       builder.setPositiveButton(android.R.string.ok,
               new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                   }
               });
       AlertDialog dialog = builder.create();
       dialog.show();

   }

   public boolean HasDuplicateId(String id, String schema,Context context){
       String findStrID_column=schema+"_id";
       String query="select "+findStrID_column+" from "+schema+"s"+" where "+findStrID_column+"="+id;
       String str_result=null;
       //get async result
       try {
              str_result = new AsyncHelperRegistration(context, query,
                      findStrID_column, EnumAsync.DuplicateId.getEnumAsync()).execute().get(); //async task for getting data from db

       } catch (ExecutionException e) {
           e.printStackTrace();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       if(str_result!=null){
           if(DuplicateId_Sol.size()==0){
               return false;
           }
       }
       return true;
   }
    public void openDuplicateDialog(Context context){

        // context.setContentView(R.v.solution_singers);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Duplication ID");
        builder.setMessage(R.string.DuplicationID);
        //todo add cencel artist
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //todo
                        //להעביר לפונקציה שתכלס רושמת
                        dialog.dismiss();

                    }
                });
        builder.setNegativeButton(android.R.string.no,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
