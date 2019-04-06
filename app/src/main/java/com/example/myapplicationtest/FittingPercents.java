package com.example.myapplicationtest;

import com.example.myapplicationtest.SingersLogic.Priority;

import java.util.ArrayList;
import java.util.List;

public class FittingPercents {
    private Priority priority;

    public FittingPercents(Priority priority){
        this.priority = priority;
    }

    public List<Double> percent(){
        List<Double> gradesLoudness = new ArrayList<>();
        int gradeTempo;
        if(priority.getPrioLoudness().equals("low")){
            if(priority.getFilters().getLoudness().equals("Weak"))
            for(int i=0;i<SulationSinger.loudness.size();i++){
                float step = Math.abs(Integer.parseInt(SulationSinger.loudness.get(i))-(-16));
                double gradeLoudness = 100 * (1-step) * 0.3;
                gradesLoudness.add(gradeLoudness);
            }

        }
        return gradesLoudness;
    }
}
