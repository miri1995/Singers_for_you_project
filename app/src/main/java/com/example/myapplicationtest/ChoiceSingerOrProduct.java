package com.example.myapplicationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ChoiceSingerOrProduct extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_singer_or_creator);
    }

    public void choiceClick(View view) {
        switch (view.getId()){
            case R.id.singers:
                Intent intent = new Intent(ChoiceSingerOrProduct.this, SingersActivity.class);
                startActivity(intent);
                break;
            case R.id.creator:
                Intent intent2 = new Intent(ChoiceSingerOrProduct.this, ProductActivity.class);
                startActivity(intent2);
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }

    }

    public void exit_click(View view) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
}
