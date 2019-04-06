package com.example.myapplicationtest.RegistrationP;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplicationtest.AsyncHelper;
import com.example.myapplicationtest.R;
import com.example.myapplicationtest.SingersLogic.Filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingersRegistration extends AppCompatActivity {
    Filters filters;
    Spinner spinner1, spinner2, spinner3;
    EditText name_txt;
    public static List<String> geners=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singers_registration);


        String q3="select genre from genre";
        //new AsyncHelper(SingersRegistration.this,q3,"genre","singer").execute(); //async task for getting data from db

        System.out.println(geners);

        //categorization
        geners.add(0,"select");
        spinner1 = findViewById(R.id.register_what_you);

        ArrayAdapter<String> generesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,geners);
        spinner1.setAdapter(generesAdapter);

        //spinner2
        List<String> loudness = new ArrayList<String>(Arrays.asList("select","Weak","Normal","Strong"));
        spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<String> LoudnessAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,loudness);
        spinner2.setAdapter(LoudnessAdapter);

        //spinner3
        List<String> beat = new ArrayList<String>(Arrays.asList("select","Slow","Medium","Fast"));
        spinner3 = findViewById(R.id.spinner3);

        ArrayAdapter<String> beatAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,beat);
        spinner3.setAdapter(beatAdapter);

        //spinner4

           name_txt = findViewById(R.id.nameAdd);


    }

    public void registration_singers_click(View view) {

        String genre2 =null;
        String loudness2 = null;
        String beat2=null;
        // String location2=null;

        String name = name_txt.getText().toString();

        if(spinner1.getSelectedItem()!=null){
            genre2 =spinner1.getSelectedItem().toString();
        }
        if(spinner2.getSelectedItem()!=null){
            loudness2 =spinner2.getSelectedItem().toString();
        }
        if(spinner3.getSelectedItem()!=null){
            beat2 =spinner3.getSelectedItem().toString();
        }


        if(genre2==null || loudness2==null || beat2==null ||
                genre2.equals("select") || loudness2.equals("select") ||
                beat2.equals("select") ){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Error Choose");
            builder.setMessage("Please select all filters");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }else { //only if all filter selected

            String q="";
            new AsyncHelperRegistration(SingersRegistration.this,q,"register").execute(); //async task for getting data from db

           // filters = new Filters(genre2, loudness2, beat2);

            //blabkajj
           /* Intent intent1 = new Intent(SingersActivity.this, ParioritySingers.class);
            intent1.putExtra("com.example.myapplicationtest.SingersLogic.Filters", filters);
            setResult(Activity.RESULT_OK, intent1);
            startActivity(intent1);*/

            finish();
        }
    }


}
