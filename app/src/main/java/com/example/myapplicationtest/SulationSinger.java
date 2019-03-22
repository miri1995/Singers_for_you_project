package com.example.myapplicationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class SulationSinger  extends AppCompatActivity {
    Filters filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_singers);



        Intent intent1 = getIntent();
        if (intent1.hasExtra("com.example.myapplicationtest.Filters")) {
            filter = (Filters) intent1.getSerializableExtra("com.example.myapplicationtest.Filters");
        }

        String[] resultArray = {"result1","result2","result3","result4",
                "result5"};
        //ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main3, resultArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.activity_list_item, android.R.id.text1, resultArray);
        ListView listView = findViewById(R.id.result_list);
        listView.setAdapter(adapter);

       /* Button button = (Button) findViewById(R.id.allSol_buttom);
        button.setOnClickListener( new View.OnClickListener()
        {
            public void onClick (View v){
                setContentView(R.layout.solution_singers);
            }
        });*/

    }

    public void back_click(View view) {
        Intent intent = new Intent(SulationSinger.this, ParioritySingers.class);
        startActivity(intent);
    }
}
