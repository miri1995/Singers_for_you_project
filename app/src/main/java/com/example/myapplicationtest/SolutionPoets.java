package com.example.myapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.example.myapplicationtest.Enums.EnumAsync;
import com.example.myapplicationtest.Enums.EnumTables;
import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.Poets.PoetsPriority;
import com.example.myapplicationtest.SingersLogic.Helper;
//import assets.pair3.txt;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_poets);
        doQueryAndUpdate(false);
    }

     public void  doQueryAndUpdate(boolean needToIncrease){
         String str_result = null;
        Intent intent2 = getIntent();
        poetsPriority = (PoetsPriority) intent2.getSerializableExtra(PoetsPriority.class.getName());


        Query_Poet query = new Query_Poet();
        //String flag = EnumsSingers.poets.getEnums();
        String q3= query.UserInput(poetsPriority.getFilters().getGenre(),poetsPriority.getFilters().getSubject(),poetsPriority.getFilters().getGoal(),null,
                poetsPriority.getPrioGenre(),poetsPriority.getPrioSubject(),poetsPriority.getPrioGoal(),needToIncrease);

        try {

            HelperLists helperLists = new HelperLists();
            helperLists.updatePoetMap(this);
            CoupleDistance coupleDistance = CoupleDistance.getInstance();
            List<List<String>> genreCouplesPoets = new ArrayList<>();
            genreCouplesPoets=coupleDistance.CreatePairFromMap(HelperLists.poetIdGenre,genreCouplesPoets);
            List<List<String>> topicCouplesPoets = new ArrayList<>();
            topicCouplesPoets.addAll(coupleDistance.CreatePairFromMap(HelperLists.poetIdTopic,topicCouplesPoets));
            List<List<String>> goalCouplesPoets = new ArrayList<>();
            goalCouplesPoets.addAll(coupleDistance.CreatePairFromMap(HelperLists.poetIdGoal,goalCouplesPoets));

            //List<List<String>> genreCouplesComposers = coupleDistance.CreatePairFromMap(HelperLists.poetIdGenre,genre);
            coupleDistance.countPairs(genreCouplesPoets, EnumTables.genrePoet.getEnums());
            coupleDistance.countPairs(topicCouplesPoets,EnumTables.topic.getEnums());
            coupleDistance.countPairs(goalCouplesPoets,EnumTables.goal.getEnums());
            str_result=new AsyncHelper(SolutionPoets.this,q3,"poet_name","song_topic","goal","genreSinger",
                    EnumAsync.Poet.getEnumAsync()).execute().get();


            // Log.d("D","sol re "+str_result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        if(str_result!=null) {
            FittingPercents fittingPercents = new FittingPercents(null,poetsPriority,null);

            if(poetsPriority.getPrioGenre().equals(EnumsSingers.High.getEnums())){
                gradesElement1 = fittingPercents.percentElement("topic");
                gradesElement2 = fittingPercents.percentElement("goal");
            }
            else if(poetsPriority.getPrioSubject().equals(EnumsSingers.High.getEnums())){
                gradesElement1 = fittingPercents.percentElement("genreSinger");
                gradesElement2 = fittingPercents.percentElement("goal");
            }
            else{
                gradesElement1 = fittingPercents.percentElement("topic");
                gradesElement2 = fittingPercents.percentElement("genreSinger");
            }
            grades=fittingPercents.uniteTwoListd(gradesElement1,gradesElement2);

            List<String> resultArray = new ArrayList<>();
           /* if(poets.size()>10){

            }
            else{
                resultArray=poets;
            }*/

            List<Artist> artistList=new ArrayList<>();
            List<String> gradesArray = new ArrayList<>();
            for(int i=0;i<grades.size();i++){
                double grade = round(grades.get(i),2);
                Artist artist=new Artist(poets.get(i),grade+"%");
                artistList.add(artist);
                gradesArray.add(grade+"%");
            }
            HelperLists helperLists=new HelperLists();
            boolean sol=helperLists.checkSizeOfListResults(this,artistList,3);
            if(sol){
                gradesArray = gradesArray.subList(0,10);
                resultArray=poets.subList(0,10);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        R.layout.activity_listview, resultArray);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                        R.layout.activity_listview, gradesArray);
                ListView listView = findViewById(R.id.listView);
                ListView listView2 = findViewById(R.id.listView2);
                listView.setAdapter(adapter);
                listView2.setAdapter(adapter2);
            }else if(counter==0){
                counter++;
                doQueryAndUpdate(true);

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
        List<String> resultArray = poets;
        List<Double> gradesArray = new ArrayList<>(); /*= grades*/;
        for(int i=0;i<grades.size();i++){
            double grade = round(grades.get(i),2);
            gradesArray.add(grade);
        }
        ///ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main3, resultArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, resultArray);
        ArrayAdapter<Double> adapter2 = new ArrayAdapter<Double>(this,
                R.layout.activity_listview, gradesArray);
        ListView listView = findViewById(R.id.listView);
        ListView listView2 = findViewById(R.id.listView2);
        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);

        Button allSolButton = (Button) findViewById(R.id.btAllSolPoets);
        allSolButton.setVisibility(View.GONE);
    }

    public void back_click(View view){
        Intent intent = new Intent(SolutionPoets.this, PoetsActivity.class);
        startActivity(intent);
    }
}

