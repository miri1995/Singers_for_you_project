package com.example.myapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;


import com.example.myapplicationtest.Enums.EnumAsync;
import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.SingersLogic.Priority;
import com.example.myapplicationtest.YouTube.YouTubeActivity;
//import assets.pair3.txt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.lang.StrictMath.round;

public class SulationSinger  extends Activity {


    Priority priority = new Priority();
   public static List<String> artists=new ArrayList<>();
    //public static List<String> artists_id=new ArrayList<>();
   public static List<String> genres=new ArrayList<>();
    public static List<Double> tempo=new ArrayList<>();
    public static List<Double> loudness=new ArrayList<>();
    public static String whichArtist;
    List<Double>grades = new ArrayList<>();
    String items[] =new  String[]{};

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_singers2);
        TabHost tabhost = findViewById(R.id.tabHost);
        tabhost.setup();
        TabHost.TabSpec ts = tabhost.newTabSpec("tag1");
        //tab1
        ts.setContent(R.id.tab1);
        ts.setIndicator("Most Popular");
        mostOrLessPop(true);
        tabhost.addTab(ts);
        //tab2
        /*ts = tabhost.newTabSpec("tag2");
        ts.setContent(R.id.tab2);
        ts.setIndicator("Less Popular");*/
    //    mostOrLessPop(false);
        tabhost.addTab(tabhost.newTabSpec("tab_inser").setIndicator("Less Popular",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(R.id.lessPopular));
        //mostOrLessPop(false);
        tabhost.addTab(ts);
        ts= tabhost.newTabSpec("tag3");
        ts.setContent(R.id.tab3);
        ts.setIndicator("The Best For You");
        tabhost.addTab(ts);



    }

    public void mostOrLessPop(boolean popular){
        String str_result=null;
        Intent intent2 = getIntent();
        priority = (Priority) intent2.getSerializableExtra(Priority.class.getName());
        Query_Singer query = new Query_Singer();
       // String flag=EnumsSingers.singer.getEnums();
        String q3= query.UserInput(priority.getFilters().getGenre(),priority.getFilters().getLoudness(),priority.getFilters().getTempo(),null,
                priority.getPrioGenre(),priority.getPrioLoudness(),priority.getPrioTempo(),popular);
        artists.clear();
        tempo.clear();
        loudness.clear();
        try {
            str_result=new AsyncHelper(SulationSinger.this,q3,"artist_name","song_tempo","song_loudness","genre",
                    EnumAsync.Sol.getEnumAsync()).execute().get();
            // Log.d("D","sol re "+str_result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        if(str_result!=null) {

            FittingPercents fittingPercents = new FittingPercents(priority,null,null);
            if(priority.getPrioGenre().equals(EnumsSingers.High.getEnums())){
                grades = fittingPercents.percentTempoLoudness("both",priority.getFilters().getLoudness(),priority.getFilters().getTempo(),priority.getPrioLoudness(),priority.getPrioTempo(),tempo,loudness);
            }
            else{
                grades = fittingPercents.percentGenreElse(priority.getPrioGenre(),genres,priority.getFilters().getGenre(),priority.getPrioLoudness(),priority.getFilters().getLoudness(),priority.getFilters().getTempo(),priority.getPrioTempo(),tempo,loudness);
            }

            List<String> resultArray = artists.subList(0,10);
            List<String> gradesArray = new ArrayList<>();
            for(int i=0;i<grades.size();i++){
                double grade = round(grades.get(i),1);
                gradesArray.add(grade+"%");
            }
            gradesArray = gradesArray.subList(0,10);
            ///ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main3, resultArray);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.activity_listview, resultArray);
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                    R.layout.activity_listview, gradesArray);

            if(popular) {
                ListView listView = findViewById(R.id.listView);
                ListView listView2 = findViewById(R.id.listView2);
                listView.setAdapter(adapter);
                listView2.setAdapter(adapter2);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView parent, View view, int position, long id) {

                        Intent intent = new Intent(SulationSinger.this, YouTubeActivity.class);
                        startActivity(intent);
                        whichArtist = listView.getItemAtPosition(position).toString();
                           // Toast.makeText(SulationSinger.this, artists+ " added to list 2", Toast.LENGTH_SHORT).show();

                    }
                });
            }else{
                ListView listView = findViewById(R.id.listViewLess);
                ListView listView2 = findViewById(R.id.listViewLess2);
                listView.setAdapter(adapter);
                listView2.setAdapter(adapter2);
            }
          //  listView.setAdapter(adapter);
         //   listView2.setAdapter(adapter2);
            // Log.d("D", "ll" + listView.toString());
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
        List<String> resultArray = artists;
        List<String> gradesArray = new ArrayList<>(); /*= grades*/;
        for(int i=0;i<grades.size();i++){
          double grade = round(grades.get(i),1);
          gradesArray.add(grade+"%");
        }
        ///ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main3, resultArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, resultArray);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                R.layout.activity_listview, gradesArray);
        ListView listView = findViewById(R.id.listView);
        ListView listView2 = findViewById(R.id.listView2);
        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);

        Button allSolButton = (Button) findViewById(R.id.btAllSolSingers);
        allSolButton.setVisibility(View.GONE);
    }

    public void back_click(View view){
        Intent intent = new Intent(SulationSinger.this, SingersActivity.class);
        startActivity(intent);
    }
}