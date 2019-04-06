package com.example.myapplicationtest;

import com.example.myapplicationtest.SingersLogic.Priority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FittingPercents {
    private Priority priority;

    public FittingPercents(Priority priority) {
        this.priority = priority;
    }

    public List<Double> percent() {
        List<Double> gradesLoudness = new ArrayList<>();
        List<Double> gradesTempo = new ArrayList<>();
        double gradeTempo;
        double gradeLoudness;
        if (priority.getFilters().getTempo().equals("Fast") || priority.getFilters().getTempo().equals("Slow")) {
            if (priority.getFilters().getLoudness().equals("Weak") || priority.getFilters().getTempo().equals("Strong")) {
                float numLoudness[] = Maps.getInstance().PutInloudness(priority.getFilters().getLoudness());
                HashMap<String, Float> priorityMap = Maps.getInstance().PutInPriority(priority.getPrioLoudness(), priority.getPrioTempo());
                double percentLoudness = Maps.getInstance().PutInPercents(priority.getPrioLoudness());
                float numTempo[] = Maps.getInstance().PutInTempo(priority.getFilters().getTempo());
                double percentTempo = Maps.getInstance().PutInPercents(priority.getPrioTempo());
                for (int i = 0; i < SulationSinger.loudness.size(); i++) {
                    float step = (Math.abs(Integer.parseInt(SulationSinger.loudness.get(i)) - numLoudness[0])) / priorityMap.get(priority.getPrioLoudness());
                    gradeLoudness = 100 * (1 - step) * percentLoudness;
                    gradesLoudness.add(gradeLoudness);
                }
                for (int i = 0; i < SulationSinger.loudness.size(); i++) {
                    float step = (Math.abs(Integer.parseInt(SulationSinger.tempo.get(i)) - numTempo[0])) / priorityMap.get(priority.getPrioTempo());
                    gradeTempo = 100 * (1 - step) * percentTempo;
                    gradesTempo.add(gradeTempo);
                }
            }
        }
       /* else{

        }*/





     /*   if(priority.getPrioLoudness().equals("low")){
            if(priority.getFilters().getLoudness().equals("Weak"))


        }
        return gradesLoudness;
    }*/
     return gradesLoudness;
    }
}