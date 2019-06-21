package com.example.ArtistsForYou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ArtistsForYou.Composer.Activity.ComposersActivity;
import com.example.ArtistsForYou.Poets.Activity.PoetsActivity;
import com.example.ArtistsForYou.Singers.Activity.SingersActivity;

public class WhichArtist extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_singer_or_composer_or_songwriter);
    }

    public void choiceClick(View view) {
        switch (view.getId()){
            case R.id.singers:
                Intent intent = new Intent(WhichArtist.this, SingersActivity.class);
                startActivity(intent);
                break;
            case R.id.poets:
                Intent intent2 = new Intent(WhichArtist.this, PoetsActivity.class);
                startActivity(intent2);
                break;
            case R.id.composer:
                Intent intent3 = new Intent(WhichArtist.this, ComposersActivity.class);
                startActivity(intent3);
                break;
            case R.id.btregistration:
                Intent intent4 = new Intent(WhichArtist.this, Registration.class);
                startActivity(intent4);
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }

    }

}
