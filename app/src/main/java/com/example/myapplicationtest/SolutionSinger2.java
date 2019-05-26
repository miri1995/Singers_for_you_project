package com.example.myapplicationtest;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


import com.example.myapplicationtest.SingersLogic.Priority;
//import assets.pair.txt;

import static java.lang.StrictMath.round;

public class SolutionSinger2 extends TabActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);

        TabHost mTabHost = getTabHost();


        Intent intent = getIntent();
        Priority priority = (Priority) intent.getSerializableExtra(Priority.class.getName());


        Intent intent1 = new Intent(SolutionSinger2.this, SolutionSinger_Tab1.class);
        intent1.putExtra(Priority.class.getName(), priority);
        setResult(Activity.RESULT_OK, intent1);

        Intent intent2 = new Intent(SolutionSinger2.this, SolutionSinger_Tab2.class);
        intent2.putExtra(Priority.class.getName(), priority);
        setResult(Activity.RESULT_OK, intent2);

        Intent intent3 = new Intent(SolutionSinger2.this, SulationSinger_Tab3.class);
       // intent2.putExtra(Priority.class.getName(), priority);
        setResult(Activity.RESULT_OK, intent3);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Most Popular").setContent(intent1));
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Less Popular").setContent(intent2));
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("The Best For You").setContent(intent3));
        mTabHost.setCurrentTab(0);


        // Intent intent2 = new Intent(PrioritySingers.this, SolutionSinger2.class);
       // startActivity(intent1);

       // finish();

    }
}