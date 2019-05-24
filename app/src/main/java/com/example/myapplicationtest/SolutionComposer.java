package com.example.myapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.myapplicationtest.Enums.EnumAsync;
import com.example.myapplicationtest.Enums.EnumTables;
import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.Composer.ComposersPriority;
import com.example.myapplicationtest.SingersLogic.Helper;
import com.example.myapplicationtest.SingersLogic.Priority;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class SolutionComposer extends Activity {
    ComposersPriority priority = new ComposersPriority();
    public static List<String> composers=new ArrayList<>();
    //public static List<String> artists_id=new ArrayList<>();
    public static List<String> genres=new ArrayList<>();
    public static List<Double> tempo=new ArrayList<>();
    public static List<Double> loudness=new ArrayList<>();
    List<Double>grades = new ArrayList<>();
    private int counter=0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_composer);
        doQueryAndUpdate(false);
    }

     public void doQueryAndUpdate(boolean needToIncrease){
         String str_result = null;
        Intent intent2 = getIntent();
        priority = (ComposersPriority) intent2.getSerializableExtra(ComposersPriority.class.getName());
        Query_Composser query = new Query_Composser();
       // String flag=EnumsSingers.composer.getEnums();

         HelperLists helperLists = new HelperLists();
         helperLists.updateComposersMap(this);
         CoupleDistance coupleDistance = CoupleDistance.getInstance();
         List<List<String>> genreCouplesComposers = new ArrayList<>();
         genreCouplesComposers=coupleDistance.CreatePairFromMap(HelperLists.composerIdGenre,genreCouplesComposers);

         //List<List<String>> genreCouplesComposers = coupleDistance.CreatePairFromMap(HelperLists.poetIdGenre,genre);
         coupleDistance.countPairs(genreCouplesComposers, EnumTables.genreComposer.getEnums());


        String q3= query.UserInput(priority.getFilters().getGenre(),priority.getFilters().getLoudness(),priority.getFilters().getTempo(),priority.getFilters().getMusical_instrument(),
                priority.getPrioGenre(),priority.getPrioLoudness(),priority.getPrioTempo(),needToIncrease);

        try {
            str_result=new AsyncHelper(SolutionComposer.this,q3,"composer_name","composers_tempo","composers_loudness","composers_genre",
                    EnumAsync.Composer.getEnumAsync()).execute().get();
            // Log.d("D","sol re "+str_result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        if(str_result!=null) {

            FittingPercents fittingPercents = new FittingPercents(null, null, priority);
            if (priority.getPrioGenre().equals(EnumsSingers.High.getEnums())) {
                grades = fittingPercents.percentTempoLoudness("both",
                        priority.getFilters().getLoudness(),
                        priority.getFilters().getTempo(),
                        priority.getPrioLoudness(), priority.getPrioTempo(), tempo, loudness, needToIncrease);
            } else {
                grades = fittingPercents.percentGenreElse(priority.getPrioGenre(),
                        genres, priority.getFilters().getGenre(), priority.getPrioLoudness(),
                        priority.getFilters().getLoudness(), priority.getFilters().getTempo(),
                        priority.getPrioTempo(), tempo, loudness, needToIncrease);
            }

            List<String> resultArray = new ArrayList<>();
            // HelperLists helperLists =new HelperLists();

           /* if(composers.size()>10){
                resultArray = composers.subList(0,10);
            }
            else{
                resultArray = composers;
            }*/
            List<Artist> artistList = new ArrayList<>();
            List<Double> gradesArray = new ArrayList<>();
            for (int i = 0; i < grades.size(); i++) {
                double grade = round(grades.get(i), 2);
                Artist artist = new Artist(composers.get(i), grade + "%");
                artistList.add(artist);
                gradesArray.add(grade);
            }
            boolean sol = helperLists.checkSizeOfListResults(this, artistList, 3,counter);
            // if(sol){
            if (sol && composers.size()>=10) {
            resultArray = composers.subList(0, 10);
            gradesArray = gradesArray.subList(0, 10);
            }else{
                resultArray = composers;
               //gradesArray = gradesArray;
            }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        R.layout.activity_listview, resultArray);
                ListView listView = findViewById(R.id.listView);
                listView.setAdapter(adapter);
                ArrayAdapter<Double> adapter2 = new ArrayAdapter<Double>(this,
                        R.layout.activity_listview, gradesArray);
                ListView listView2 = findViewById(R.id.listView2);
                listView2.setAdapter(adapter2);
           // }else if(counter==0){
            if(!sol && counter==0){
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
        List<String> resultArray = composers;
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

        Button allSolButton = (Button) findViewById(R.id.btAllSolSingers);
        allSolButton.setVisibility(View.GONE);
    }

    public void back_click(View view){
        Intent intent = new Intent(SolutionComposer.this, ComposersActivity.class);
        startActivity(intent);
    }
}
