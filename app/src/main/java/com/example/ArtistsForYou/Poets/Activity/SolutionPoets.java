package com.example.ArtistsForYou.Poets.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.ArtistsForYou.Artist;
import com.example.ArtistsForYou.AsyncHelper;
import com.example.ArtistsForYou.CoupleDistance;
import com.example.ArtistsForYou.Enums.EnumAsync;
import com.example.ArtistsForYou.Enums.EnumTables;
import com.example.ArtistsForYou.Enums.EnumsSingers;
import com.example.ArtistsForYou.FittingPercents;
import com.example.ArtistsForYou.HelperLists;
import com.example.ArtistsForYou.Poets.Logic.Query_Poet;
import com.example.ArtistsForYou.Poets.Logic.PoetsPriority;
import com.example.ArtistsForYou.R;

//import assets.pair.txt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static java.lang.StrictMath.round;

public class SolutionPoets extends Activity {
    PoetsPriority poetsPriority = new PoetsPriority();
    public static List<String> poets=new ArrayList<>();
    //public static List<String> artists_id=new ArrayList<>();
    public static List<String> genres=new ArrayList<>();
    public static List<String> subject=new ArrayList<>();
    public static List<String> goal=new ArrayList<>();
    List<Double>grades = new ArrayList<>();
    List<Double>gradesElement1 = new ArrayList<>();
    List<Double>gradesElement2 = new ArrayList<>();
    private int counter=0;
    HelperLists helperLists = new HelperLists();
    private List<Artist> artistList=new ArrayList<>();
    private List<Double> gradesArray = new ArrayList<>();
    private List<String> poetsBeforeIncrecement = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_poets);
        doQueryAndUpdate(false,true);
    }

     public void  doQueryAndUpdate(boolean needToIncrease,boolean firstTime){
         String str_result = null;
        Intent intent2 = getIntent();
        poetsPriority = (PoetsPriority) intent2.getSerializableExtra(PoetsPriority.class.getName());
         Query_Poet query = new Query_Poet();


         if(firstTime) {
             if(helperLists.updatePoetMap(this)) {
               //  helperLists.updatePoetMap(this);
                 CoupleDistance coupleDistance = CoupleDistance.getInstance();
                 List<List<String>> genreCouplesPoets = new ArrayList<>();
                 genreCouplesPoets = coupleDistance.CreatePairFromMap(HelperLists.poetIdGenre, genreCouplesPoets);
                 List<List<String>> topicCouplesPoets = new ArrayList<>();
                 topicCouplesPoets = coupleDistance.CreatePairFromMap(HelperLists.poetIdTopic, topicCouplesPoets);
                 List<List<String>> goalCouplesPoets = new ArrayList<>();
                 goalCouplesPoets = coupleDistance.CreatePairFromMap(HelperLists.poetIdGoal, goalCouplesPoets);

                 coupleDistance.countPairs(genreCouplesPoets, EnumTables.genrePoet.getEnums());
                 coupleDistance.countPairs(topicCouplesPoets, EnumTables.topic.getEnums());
                 coupleDistance.countPairs(goalCouplesPoets, EnumTables.goal.getEnums());
             }
    }

        String q3= query.UserInput(poetsPriority.getFilters().getGenre(),poetsPriority.getFilters().getSubject(),poetsPriority.getFilters().getGoal(),null,
                poetsPriority.getPrioGenre(),poetsPriority.getPrioSubject(),poetsPriority.getPrioGoal(),needToIncrease);

        try {

            str_result=new AsyncHelper(SolutionPoets.this,q3,"poet_name","song_topic","goal","genre",
                    EnumAsync.Poet.getEnumAsync()).execute().get();


            // Log.d("D","sol re "+str_result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        if(str_result!=null) {

            FittingPercents fittingPercents = new FittingPercents(null,poetsPriority,null);
            Log.d("D","reach to result"+str_result);
            if(poetsPriority.getPrioGenre().equals(EnumsSingers.High.getEnums())){
                gradesElement1 = fittingPercents.percentElement("topic");
                gradesElement2 = fittingPercents.percentElement("goal");
            }
            else if(poetsPriority.getPrioSubject().equals(EnumsSingers.High.getEnums())){
                gradesElement1 = fittingPercents.percentElement("genrePoet");
                gradesElement2 = fittingPercents.percentElement("goal");
            }
            else{
                gradesElement1 = fittingPercents.percentElement("topic");
                Log.d("D","reach to topic"+str_result);
                gradesElement2 = fittingPercents.percentElement("genrePoet");
                Log.d("D","reach to genre"+str_result);
            }
            grades=fittingPercents.uniteTwoListd(gradesElement1,gradesElement2);

            List<String> resultArray = new ArrayList<>();
            for(int i=0;i<grades.size();i++){
                double grade = round(grades.get(i),2);
               // Artist artist=new Artist(poets.get(i),grade+"%");
                boolean contains;
                if(poetsBeforeIncrecement.contains(poets.get(i))){
                    contains = true;
                }
                else {
                    poetsBeforeIncrecement.add(poets.get(i));
                    contains = false;
                }
                if(!contains) {
                   // artistList.add(artist);
                    gradesArray.add(grade);
                }
            }
            //sorted that the high percent in the top
            Map<String,Integer> map= helperLists.createMap(poets,gradesArray);
            Map<String, Integer> sortedMap= helperLists.sortMapByValue(map);
            int index=1;
            for (Map.Entry<String,Integer> entry : sortedMap.entrySet()) {
                Artist artist=new Artist(entry.getKey(),entry.getValue().toString()+"%");
                artistList.add(artist);
                index++;
            }

            boolean sol=helperLists.checkSizeOfListResults(this,artistList,2,counter,"poet");
            if(sol){
                if(poets.size()<10){
                    //resultArray = poets;
                    Button allSolButton = (Button) findViewById(R.id.btAllSolPoets);
                    allSolButton.setVisibility(View.GONE);
                }


                ListView listView = findViewById(R.id.listView);
                if(artistList.size()>10){
                    helperLists.updateTwoListView(this, artistList.subList(0,10), listView);
                }else{
                    helperLists.updateTwoListView(this, artistList, listView);
                }


            }else if(counter==0){
                counter++;
                doQueryAndUpdate(true,false);

            }


        }

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    public static boolean moreThanOnce(List<String> list, String searched)
    {
        int numCount = 0;

        for (String thisArtist : list) {
            if (thisArtist.equals(searched)) numCount++;
        }

        return numCount > 0;
    }


    public void allSol_click(View view) {
        TextView textView = (TextView) findViewById(R.id.txtResults);
        helperLists.updateTextView(R.string.result,textView);
        List<String> resultArray = poets;
        List<Double> gradesArray = new ArrayList<>(); /*= grades*/;

        List<Artist> artistsList=new ArrayList<>();
        for(int i=0;i<grades.size();i++){
            double grade = round(grades.get(i),2);
            gradesArray.add(grade);
        }
        Map<String,Integer> map= helperLists.createMap(resultArray,gradesArray);
        Map<String, Integer> sortedMap= helperLists.sortMapByValue(map);
        int index=1;
        for (Map.Entry<String,Integer> entry : sortedMap.entrySet()) {
           // sortedGrades.add(entry.getValue().toString()+"%");
           // sortedArtist.add(entry.getKey());
           Artist artist=new Artist(entry.getKey(),entry.getValue().toString()+"%");
           artistsList.add(artist);
           index++;
        }


        ListView listView = findViewById(R.id.listView);
        helperLists.updateTwoListView(this, artistsList, listView);


        Button allSolButton = (Button) findViewById(R.id.btAllSolPoets);
        allSolButton.setVisibility(View.GONE);
    }


}

