package com.example.ArtistsForYou.Composer.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

import com.example.ArtistsForYou.Composer.Logic.ComposerFilters;
import com.example.ArtistsForYou.Composer.Logic.ComposersPriority;
import com.example.ArtistsForYou.HelperLists;
import com.example.ArtistsForYou.R;

public class PriorityComposers extends AppCompatActivity {
    ComposersPriority priority;
    Spinner spinner1, spinner2, spinner3, spinner4;
    HelperLists helperLists=new HelperLists();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.priority_composers);

        //categorization
        spinner1 = findViewById(R.id.register_what_you);

        //spinner2
        spinner2 = findViewById(R.id.spinner2);


        //spinner3 b
        spinner3 = findViewById(R.id.spinner3);

        helperLists.initPariority(this,spinner1,spinner2,spinner3);


    }

    public void second_click(View view) {

        String genreP2 =null;
        String loudnessP2 = null;
        String beatP2=null;


        if(helperLists.checkSelectedItem(spinner1,this)&& helperLists.checkSelectedItem(spinner2,this)&&
                helperLists.checkSelectedItem(spinner3,this)){
            genreP2 =spinner1.getSelectedItem().toString();
            loudnessP2 =spinner2.getSelectedItem().toString();
            beatP2 =spinner3.getSelectedItem().toString();
        }

        boolean allChoose=helperLists.checkChoice(genreP2,loudnessP2,beatP2);

        boolean diffrentPriority=helperLists.checkPriority(genreP2,loudnessP2,beatP2);
        if(allChoose && diffrentPriority) {
            Intent intent = getIntent();
            ComposerFilters filters = (ComposerFilters) intent.getSerializableExtra(ComposerFilters.class.getName());
            priority = new ComposersPriority(genreP2, loudnessP2, beatP2,filters);
            Intent intent1 = new Intent(PriorityComposers.this, SolutionComposer.class);
            intent1.putExtra(ComposersPriority.class.getName(), priority);
            setResult(Activity.RESULT_OK, intent1);
            startActivity(intent1);
            finish();
        }else{
            if(!diffrentPriority) {
                helperLists.ErrorChoice(this,R.string.errorPriority);
            }else{
                helperLists.ErrorChoice(this,R.string.errorFilters);
            }
        }
    }
    public void explanationClick(View view){
        helperLists.openExplationDialog(this);

    }

}

