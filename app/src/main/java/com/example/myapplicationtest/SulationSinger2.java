package com.example.myapplicationtest;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.myapplicationtest.Enums.EnumAsync;
import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.SingersLogic.Priority;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//import assets.pair3.txt;

public class SulationSinger2 extends TabActivity implements TabHost.OnTabChangeListener {


    Priority priority = new Priority();
   public static List<String> artists=new ArrayList<>();
    //public static List<String> artists_id=new ArrayList<>();
   public static List<String> genres=new ArrayList<>();
    public static List<Double> tempo=new ArrayList<>();
    public static List<Double> loudness=new ArrayList<>();
    List<Double>grades = new ArrayList<>();
    private ListView listView1;
    private ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_singers2);
        TabHost tabHost = getTabHost();
        tabHost.setOnTabChangedListener(this);


        // setup list view 1
        listView1 = (ListView) findViewById(R.id.listView);

        // create some dummy strings to add to the list
        List<String> list1Strings = new ArrayList<String>();
        list1Strings.add("Item 1");
        list1Strings.add("Item 2");
        list1Strings.add("Item 3");
        list1Strings.add("Item 4");
        listView1.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list1Strings));

        // setup list view 2
        listView2 = (ListView) findViewById(R.id.listView2);

        // create some dummy strings to add to the list (make it empty initially)
        List<String> list2Strings = new ArrayList<String>();
        listView2.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list2Strings));

        // add an onclicklistener to add an item from the first list to the second list
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String item = (String) listView1.getAdapter().getItem(position);
                if(item != null) {
                    ((ArrayAdapter) listView2.getAdapter()).add(item);
                    Toast.makeText(SulationSinger2.this, item + " added to list 2", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // add views to tab host
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("tab 1").setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String arg0) {
                return listView1;
            }
        }));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("tab 2").setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String arg0) {
                return listView2;
            }
        }));


    }

    /**
     * Implement logic here when a tab is selected
     */
    public void onTabChanged(String tabName) {
        if(tabName.equals("tab2")) {
            //do something
        }
        else if(tabName.equals("tab1")) {
            //do something
        }
    }

    public void mostOrLessPop(boolean popular){
        String str_result=null;
        Intent intent2 = getIntent();
        priority = (Priority) intent2.getSerializableExtra(Priority.class.getName());
        Query query = new Query();
        String flag=EnumsSingers.singer.getEnums();
        String q3= query.UserInput(priority.getFilters().getGenre(),priority.getFilters().getLoudness(),priority.getFilters().getTempo(),null,
                priority.getPrioGenre(),priority.getPrioLoudness(),priority.getPrioTempo(),popular,flag);
        artists.clear();
        tempo.clear();
        loudness.clear();
        try {
            str_result=new AsyncHelper(SulationSinger2.this,q3,"artist_name","song_tempo","song_loudness","genre",
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
                double grade = round(grades.get(i),0);
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
          double grade = round(grades.get(i),2);
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
        Intent intent = new Intent(SulationSinger2.this, SingersActivity.class);
        startActivity(intent);
    }
}