package com.example.myapplicationtest;

import com.example.myapplicationtest.SingersLogic.Priority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maps {

    private static Maps single_instance = null;
    HashMap<String, Float> priority = new HashMap<>();
    List<String> otherGenre = new ArrayList<>();


    private Maps() {

    }

    public Map<String, Float> getMapPrio() {

        return priority;
    }

    public List<String> getSecondGenre() {

        return otherGenre;
    }

    public static Maps getInstance() {
        if (single_instance == null)
            single_instance = new Maps();

        return single_instance;
    }

    public HashMap<String, Float> PutInPriority(String prioLoudness, String prioTempo) {
        switch (prioLoudness) {
            case "high":
                priority.put(prioLoudness, (float) 0);
                if (prioTempo.equals("medium")) {
                    priority.put(prioTempo, (float) 25);
                }
                if (prioTempo.equals("low")) {
                    priority.put(prioTempo, (float) 50);
                }
                break;
            case "medium":
                priority.put(prioLoudness, (float) 5);
                if (prioTempo.equals("high")) {
                    priority.put(prioTempo, (float) 0);
                }
                if (prioTempo.equals("low")) {
                    priority.put(prioTempo, (float) 50);
                }
                break;
            case "low":
                priority.put(prioLoudness, (float) 10);
                if (prioTempo.equals("high")) {
                    priority.put(prioTempo, (float) 0);
                }
                if (prioTempo.equals("medium")) {
                    priority.put(prioTempo, (float) 25);
                    //probably something else
                }
                break;

        }
        return priority;

    }

    public float[] PutInloudness(String loudness) {
        float numLoud[] = new float[2];
        switch (loudness) {
            case "Weak":
                numLoud[0] = -16;
                break;
            case "Normal":
                numLoud[0] = -32;
                numLoud[1] = -16;
                break;
            case "Strong":
                numLoud[0] = -32;
        }
        return numLoud;
    }

    public float[] PutInTempo(String tempo) {
        float numTempo[] = new float[2];
        switch (tempo) {
            case "Slow":
                numTempo[0] = 85;
                break;
            case "Medium":
                numTempo[0] = 85;
                numTempo[1] = 170;
                break;
            case "Fast":
                numTempo[0] = 170;
        }
        return numTempo;
    }

    public double PutInPercents(String whichPrio){
        double whichPercent = 0;
        switch (whichPrio){
            case "low":
                whichPercent = 0.3;
                break;
            case "medium":
                whichPercent = 0.7;

        }
        return whichPercent;
    }

    public void getFromQuery(List<String>coupleGenre){
        otherGenre = coupleGenre;

    }
}
