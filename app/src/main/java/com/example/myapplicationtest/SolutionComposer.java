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
import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.Composer.ComposersPriority;
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

    protected void onCreate(Bundle savedInstanceState) {
        String str_result=null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_composer);
        Intent intent2 = getIntent();
        priority = (ComposersPriority) intent2.getSerializableExtra("com.example.myapplicationtest.Composer.ComposersPriority");
        Query query = new Query();
        String flag=EnumsSingers.composer.getEnums();
        String q3= query.UserInput(priority.getFilters().getGenre(),priority.getFilters().getLoudness(),priority.getFilters().getTempo(),priority.getFilters().getMusical_instrument(),
                priority.getPrioGenre(),priority.getPrioLoudness(),priority.getPrioTempo(),false,flag);
        /*composers.clear();
        tempo.clear();
        loudness.clear();*/
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

         /*   FittingPercents fittingPercents = new FittingPercents(null,null,priority);
            if(priority.getPrioGenre().equals(EnumsSingers.High.getEnums())){
                grades = fittingPercents.percentTempoLoudness("both");
            }
            else{
                grades = fittingPercents.percentGenreElse();
            }*/

            List<String> resultArray = new ArrayList<>();
            if(composers.size()>10){
                resultArray = composers.subList(0,10);
            }
            else{
                resultArray = composers;
            }
          /*  List<Double> gradesArray = new ArrayList<>();
            for(int i=0;i<grades.size();i++){
                double grade = round(grades.get(i),2);
                gradesArray.add(grade);
            }
            if(gradesArray.size()>10){
                gradesArray = gradesArray.subList(0,10);
            }*/
            ///ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main3, resultArray);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.activity_listview, resultArray);
          /*  ArrayAdapter<Double> adapter2 = new ArrayAdapter<Double>(this,
                    R.layout.activity_listview, gradesArray);*/


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
     //   List<Double> gradesArray = new ArrayList<>(); /*= grades*/;
       /* for(int i=0;i<grades.size();i++){
            double grade = round(grades.get(i),2);
            gradesArray.add(grade);
        }*/
        ///ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main3, resultArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, resultArray);
       /* ArrayAdapter<Double> adapter2 = new ArrayAdapter<Double>(this,
                R.layout.activity_listview, gradesArray);*/
        ListView listView = findViewById(R.id.listView);
        ListView listView2 = findViewById(R.id.listView2);
        listView.setAdapter(adapter);
      //  listView2.setAdapter(adapter2);

        Button allSolButton = (Button) findViewById(R.id.btAllSolSingers);
        allSolButton.setVisibility(View.GONE);
    }

    public void back_click(View view){
        Intent intent = new Intent(SolutionComposer.this, ComposersActivity.class);
        startActivity(intent);
    }
}
