package com.example.ArtistsForYou;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ArtistsForYou.Composer.Activity.SolutionComposer;
import com.example.ArtistsForYou.Enums.EnumAsync;
import com.example.ArtistsForYou.Enums.EnumsSingers;
import com.example.ArtistsForYou.Poets.Activity.SolutionPoets;
import com.example.ArtistsForYou.RegistrationP.AsyncHelperRegistration;
import com.example.ArtistsForYou.Singers.Activity.SolutionSinger_Tab1;

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
    public static Map<String,List<String>> poetIdTopic = new HashMap();
    public static Map<String,List<String>> poetIdGenre = new HashMap();
    public static Map<String,List<String>> poetIdGoal = new HashMap();
    public static Map<String,List<String>> composerIdGenre = new HashMap();
    //public static List<String> poetGoalList = new ArrayList<>();
    public static List<String> DuplicateId_Sol=new ArrayList<>();
    public HelperLists(){

    }


    public String getRelevantPoets() {return "select poet_id,genre,song_topic,goal FROM poets order by poet_id";}

    public String getRelevantComposers() {return "select composer_id,composers_genre FROM composers order by composer_id";}

   public String getGenreQuery(){
        return "select genre from genre";
   }

   public String getTopicQuery(){
        return "select topic from topic";
   }

   public String getGoalQuery(){
        return "select goal from goals";
   }

   public String getInstrumentQuery(){ return "select musical_instrument from musical_instruments";}

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

    public void ErrorChoice(Context context,int msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Error Choose");
        builder.setMessage(msg);
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
                result= new AsyncHelper(context, q2, "topic", null, null, null,
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

    }

    /**
     *  init the Priority of Singers- take the data from the data base
     * @param context
     * @param spinner
     * @param spinner2
     * @param spinner3
     */
    public void initPariority(Context context,Spinner spinner,Spinner spinner2,Spinner spinner3){
        List<String> pr = new ArrayList<String>(Arrays.asList(EnumsSingers.select.getEnums(), EnumsSingers.High.getEnums(),
                EnumsSingers.Medium.getEnums(),EnumsSingers.Low.getEnums()));
        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item,pr);
        spinner.setAdapter(generesAdapter);
        spinner2.setAdapter(generesAdapter);
        spinner3.setAdapter(generesAdapter);

    }

    /**
     * init the filters of Singers- take the data from the data base
     * @param context
     * @param spinner
     * @param spinner2
     * @param spinner3
     */
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

    /**
     * init the filters of Poets- take the data from the data base
     * @param context
     * @param spinner1
     * @param spinner2
     * @param spinner3
     */
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

    /**
     * init the filters of Composers- take the data from the data base
     * @param context
     * @param spinner1
     * @param spinner2
     * @param spinner3
     * @param spinner4
     */
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

    /**
     * show dialog that the Registration success
     * @param context
     */
    public void sucsessRegister(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Successful Registration");
        builder.setMessage("Thanks! \n You are now registered.");
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
      //  Intent intent1 = new Intent(context, WhichArtist.class);
      //  context.startActivity(intent1);

    }

    /**
     * check if all the filters are selected and return boolean
     * @param spinner
     * @param context
     * @return
     */
    public boolean checkSelectedItem(Spinner spinner,Context context){
        if(spinner.getSelectedItem()!=null){
            return true;
        }else{
            ErrorChoice(context,R.string.errorFilters);
            return false;
        }
    }

    /**
     * make the reverse of the array list
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
        ArtistsListAdapter adapter = new ArtistsListAdapter(context, R.layout.adapter_view_layout, list);
        listView.setAdapter(adapter);

    }

    /**
     * check Size Of the List Results and show message about how the result sorted
     * @param context
     * @param list
     * @param tab
     * @param counter
     * @param whichArtist
     * @return
     */
   public boolean checkSizeOfListResults(Context context, List<Artist> list, int tab,int counter,String whichArtist){
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
            return false;
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            int listSize;
            if(whichArtist.equals("singer")){
                listSize = SolutionSinger_Tab1.artists.size();
            }
            else if (whichArtist.equals("poet")){
                listSize = SolutionPoets.poets.size();
            }
            else{
                listSize = SolutionComposer.composers.size();
            }
            //builder.setTitle("Success In Registration");
            builder.setMessage("We have found "+ String.valueOf(listSize)+" artists who are for you.\n" +
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

    /**
     * create sorted map
     * @param key
     * @param value
     * @param <T>
     * @return
     */
   public <T> LinkedHashMap<String,Integer> createMap(List<String> key,List<Double> value){
       LinkedHashMap<String,Integer> map= new LinkedHashMap<>();
       for(int i = 0; i< key.size(); i++){
           map.put(key.get(i), value.get(i).intValue());
       }
       return map;
   }

    /**
     * show Explation about the priority
     * @param context
     */
   public void openExplationDialog(Context context){

       AlertDialog.Builder builder = new AlertDialog.Builder(context);
       builder.setCancelable(true);
       builder.setTitle("Explanation of the priorities");
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

    /**
     * show Explation about the Registration
     * @param context
     */
    public void openExplationRegistrationDialog(Context context){

        // context.setContentView(R.v.solution_singers);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Explanation about the tempo & loudness characteristics");
        builder.setMessage(R.string.explanationLoudnessTempo);
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


    /**
     * check to the Registration if has Duplicate Id and return boolean
     * @param id
     * @param schema
     * @param context
     * @return
     */
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

    /**
     * show Dialog that have Duplicate id
     * @param context
     */
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

    }

    /**
     * check Valitation ID in the Registration
     * @param context
     * @param id
     * @return
     */
    public boolean checkValitationID(Context context, String id){
        if(!id.matches("[0-9]+")){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            builder.setTitle("Incorrect id");
            builder.setMessage("Incorrect format of id.\nPlease check your id is only numbers.");
            builder.setPositiveButton(android.R.string.yes,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        }
        return true;
    }

    public void updateTextView(int toThis, TextView textView) {
        textView.setText(toThis);
    }
}
