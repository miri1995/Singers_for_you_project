package com.example.myapplicationtest;

import com.example.myapplicationtest.SingersLogic.Priority;

import java.util.HashMap;
import java.util.Map;

public class Maps {

    private static Maps single_instance = null;
    HashMap<String, Integer> priority = new HashMap<>();
    HashMap<String, Integer> percents = new HashMap<>();


    private Maps(){

    }

    public Map<String, Integer> getMapPrio(){

        return priority;
    }

    public Map<String, Integer> getMapPercents(){

        return percents;
    }

    public static Maps getInstance()
    {
        if (single_instance == null)
            single_instance = new Maps();

        return single_instance;
    }

    public HashMap<String,Integer> PutInPriority (String prioLoudness, String prioTempo){
        switch(prioLoudness){
            case "high":
                priority.put(prioLoudness,0);
                if(prioTempo.equals("medium")){
                    priority.put(prioTempo,25);
                }
                if(prioTempo.equals("low")){
                    priority.put(prioTempo,50);
                }
                break;
            case "medium":
                priority.put(prioLoudness,5);
                if(prioTempo.equals("high")){
                    priority.put(prioTempo,0);
                }
                if(prioTempo.equals("low")){
                    priority.put(prioTempo,50);
                }
                break;
            case "low":
                priority.put(prioLoudness,10);
                if(prioTempo.equals("high")){
                    priority.put(prioTempo,0);
                }
                if(prioTempo.equals("medium")){
                    priority.put(prioTempo,25);
                    //probably something else
                }
                break;

        }
        return priority;

    }

}
