package com.example.ArtistsForYou;

import com.example.ArtistsForYou.Enums.EnumsSingers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maps {

    private static Maps single_instance = null;
    HashMap<String, Double> priority = new HashMap<>();
    List<String> otherGenreSinger = new ArrayList<>();
    List<String> otherGenrePoet = new ArrayList<>();
    List<String> otherGenreComposer = new ArrayList<>();
    List<String>otherTopic = new ArrayList<>();
    List<String>otherGoal = new ArrayList<>();
    public static int middleLoudness=0;
    public static int middleTempo=0;

     public Maps() {

    }

    public Map<String, Double> getMapPrio() {

        return priority;
    }

    public List<String> getSecondGenreSinger() {

        return otherGenreSinger;
    }

    public List<String> getSecondGenrePoet() {

        return otherGenrePoet;
    }

    public List<String> getSecondGenreComposer() {

        return otherGenreComposer;
    }


    public List<String> getSecondTopic() {

        return otherTopic;
    }

    public List<String> getSecondGoal() {

        return otherGoal;
    }

    public static Maps getInstance() {
        if (single_instance == null)
            single_instance = new Maps();

        return single_instance;
    }

    public HashMap<String, Double> PutInPriority(String prioLoudness, String prioTempo,boolean needToIncreaseSol) {
         int tempoMed;
         int tempoHigh=0;
         int tempoLow;
         int loudnessLow;
         int loudnessMed;
         int loudnessHigh=0;
         if(needToIncreaseSol){
             tempoMed=50;
             tempoLow=100;
             loudnessLow = 20;
             loudnessMed = 10;
         }else{
             tempoMed=25;
             tempoLow=50;
             loudnessLow =10;
             loudnessMed =5;
         }

        switch (EnumsSingers.valueOf(prioLoudness)) {
            case High:
                priority.put(prioLoudness, (double) loudnessHigh);
                if (prioTempo.equals(EnumsSingers.Medium.getEnums())) {
                    priority.put(prioTempo, (double) tempoMed);
                }
                if (prioTempo.equals(EnumsSingers.Low.getEnums())) {
                    priority.put(prioTempo, (double) tempoLow);
                }
                break;
            case Medium:
                priority.put(prioLoudness, (double) loudnessMed);
                if (prioTempo.equals(EnumsSingers.High.getEnums())) {
                    priority.put(prioTempo, (double) tempoHigh);
                }
                if (prioTempo.equals(EnumsSingers.Low.getEnums())) {
                    priority.put(prioTempo, (double) tempoLow);
                }
                break;
            case Low:
                priority.put(prioLoudness, (double) loudnessLow);
                if (prioTempo.equals(EnumsSingers.High.getEnums())) {
                    priority.put(prioTempo, (double) tempoHigh);
                }
                if (prioTempo.equals(EnumsSingers.Medium.getEnums())) {
                    priority.put(prioTempo, (double) tempoMed);
                    //probably something else
                }
                break;

        }
        return priority;

    }


    public double[] PutInloudness(String loudness,boolean needToIncreaseSol) {
        double numLoud[] = new double[2];

        switch (EnumsSingers.valueOf(loudness)) {
            case Weak:
                    numLoud[0] = -16;


                break;
            case Normal:
                    numLoud[0] = -32;
                    numLoud[1] = -16;


                break;
            case Strong:
                    numLoud[0] = -32;

        }
        return numLoud;
    }


    public void middleLoudness(String loudness) {

        switch (EnumsSingers.valueOf(loudness)) {
            case Weak:
                middleLoudness = -8;
                break;
            case Normal:
                middleLoudness=-24;
                break;
            case Strong:
                middleLoudness = -45;
                break;
        }

    }

    public void middleTempo(String tempo) {

        switch (EnumsSingers.valueOf(tempo)) {
            case Slow:
                middleTempo = 42;
                break;
            case Medium:
                middleTempo=128;
                break;
            case Fast:
                middleTempo=300;
                break;
        }

    }

    public double[] PutInTempo(String tempo,boolean needToIncreaseSol) {
        double numTempo[] = new double[2];
        switch (EnumsSingers.valueOf(tempo)) {
            case Slow:
                    numTempo[0] = 85;

                break;
            case Medium:
                    numTempo[0] = 85;
                    numTempo[1] = 170;


                break;
            case Fast:
                    numTempo[0] = 170;


        }
        return numTempo;
    }

    public double PutInPercents(String whichPrio){
        double whichPercent = 0;
        switch (EnumsSingers.valueOf(whichPrio)){
            case Low:
                whichPercent = 0.3;
                break;
            case Medium:
                whichPercent = 0.7;

        }
        return whichPercent;
    }

    public void getFromQuery(List<String>coupleGenre,String whichList){
     if(whichList.equals("genreSinger")){
         otherGenreSinger.addAll(coupleGenre);
     }
     else if (whichList.equals("topic")){
         otherTopic.addAll(coupleGenre);
     }
     else if (whichList.equals("goal")){
         otherGoal.addAll(coupleGenre);
     }
     else if (whichList.equals("genrePoet")){
         otherGenrePoet.addAll(coupleGenre);
     }
     else{
         otherGenreComposer.addAll(coupleGenre);
     }

    }
}
