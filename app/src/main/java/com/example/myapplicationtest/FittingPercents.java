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

    public List<Double> percentTempoLoudness(String flag) {
        List<Double> gradesLoudness = new ArrayList<>();
        List<Double> gradesTempo = new ArrayList<>();
        List<Double> gradesFinal = new ArrayList<>();
        double gradeTempo;
        double gradeLoudness;
        float numLoudness[] = Maps.getInstance().PutInloudness(priority.getFilters().getLoudness());
        HashMap<String, Float> priorityMap = Maps.getInstance().PutInPriority(priority.getPrioLoudness(), priority.getPrioTempo());
        double percentLoudness = Maps.getInstance().PutInPercents(priority.getPrioLoudness());
        float numTempo[] = Maps.getInstance().PutInTempo(priority.getFilters().getTempo());
        double percentTempo = Maps.getInstance().PutInPercents(priority.getPrioTempo());
        if (priority.getFilters().getTempo().equals("Fast") || priority.getFilters().getTempo().equals("Slow")) {
            if(priority.getPrioTempo().equals("medium") || priority.getPrioTempo().equals("low")){
                for (int i = 0; i < SulationSinger.tempo.size(); i++) {
                    float step = (Math.abs(Integer.parseInt(SulationSinger.tempo.get(i)) - numTempo[0])) / priorityMap.get(priority.getPrioTempo());
                    gradeTempo = 100 * (1 - step) * percentTempo;
                    gradesTempo.add(gradeTempo);
                }
            }
            if (priority.getFilters().getLoudness().equals("Weak") || priority.getFilters().getTempo().equals("Strong")) {
                if(priority.getPrioLoudness().equals("medium") || priority.getPrioLoudness().equals("low")){
                    for (int i = 0; i < SulationSinger.loudness.size(); i++) {
                        float step = (Math.abs(Integer.parseInt(SulationSinger.loudness.get(i)) - numLoudness[0])) / priorityMap.get(priority.getPrioLoudness());
                        gradeLoudness = 100 * (1 - step) * percentLoudness;
                        gradesLoudness.add(gradeLoudness);
                    }
                }
            } else {
                if(priority.getPrioLoudness().equals("medium") || priority.getPrioLoudness().equals("low")){
                    for (int i = 0; i < SulationSinger.loudness.size(); i++) {
                        float step;
                        if (Math.abs(Integer.parseInt(SulationSinger.loudness.get(i)) - numLoudness[0]) < Math.abs(Integer.parseInt(SulationSinger.loudness.get(i)) - numLoudness[1])) {
                            step = (Math.abs(Integer.parseInt(SulationSinger.loudness.get(i)) - numLoudness[0])) / priorityMap.get(priority.getPrioLoudness());
                        } else {
                            step = (Math.abs(Integer.parseInt(SulationSinger.loudness.get(i)) - numLoudness[1])) / priorityMap.get(priority.getPrioLoudness());
                        }

                        gradeLoudness = 100 * (1 - step) * percentLoudness;
                        gradesLoudness.add(gradeLoudness);
                    }
                }
            }
        }
        else {
            if (priority.getFilters().getTempo().equals("Fast") || priority.getFilters().getTempo().equals("Slow")){
                for (int i = 0; i < SulationSinger.tempo.size(); i++) {
                    float step;
                    if (Math.abs(Integer.parseInt(SulationSinger.tempo.get(i)) - numTempo[0]) < Math.abs(Integer.parseInt(SulationSinger.tempo.get(i)) - numTempo[1])) {
                        step = (Math.abs(Integer.parseInt(SulationSinger.tempo.get(i)) - numTempo[0])) / priorityMap.get(priority.getPrioTempo());
                    } else {
                        step = (Math.abs(Integer.parseInt(SulationSinger.tempo.get(i)) - numLoudness[1])) / priorityMap.get(priority.getPrioTempo());
                    }

                    gradeTempo = 100 * (1 - step) * percentTempo;
                    gradesTempo.add(gradeTempo);
                }
            }
            if (priority.getFilters().getLoudness().equals("Weak") || priority.getFilters().getTempo().equals("Strong")) {
                if(priority.getPrioLoudness().equals("medium") || priority.getPrioLoudness().equals("low")){
                    for (int i = 0; i < SulationSinger.loudness.size(); i++) {
                        float step = (Math.abs(Integer.parseInt(SulationSinger.loudness.get(i)) - numLoudness[0])) / priorityMap.get(priority.getPrioLoudness());
                        gradeLoudness = 100 * (1 - step) * percentLoudness;
                        gradesLoudness.add(gradeLoudness);
                    }
                }
            }
            else {
                if(priority.getPrioLoudness().equals("medium") || priority.getPrioLoudness().equals("low")){
                    for (int i = 0; i < SulationSinger.loudness.size(); i++) {
                        float step;
                        if (Math.abs(Integer.parseInt(SulationSinger.loudness.get(i)) - numLoudness[0]) < Math.abs(Integer.parseInt(SulationSinger.loudness.get(i)) - numLoudness[1])) {
                            step = (Math.abs(Integer.parseInt(SulationSinger.loudness.get(i)) - numLoudness[0])) / priorityMap.get(priority.getPrioLoudness());
                        } else {
                            step = (Math.abs(Integer.parseInt(SulationSinger.loudness.get(i)) - numLoudness[1])) / priorityMap.get(priority.getPrioLoudness());
                        }

                        gradeLoudness = 100 * (1 - step) * percentLoudness;
                        gradesLoudness.add(gradeLoudness);
                    }
                }
            }
        }
        if (flag.equals("both")){
            for(int i=0;i<gradesLoudness.size();i++){
                double finalGrade = gradesLoudness.get(i) + gradesTempo.get(i);
                gradesFinal.add(finalGrade);
            }
        }
        else if(flag.equals("tempo")){
            gradesFinal = gradesTempo;
        }
        else {
            gradesFinal = gradesLoudness;
        }
        return gradesFinal;
    }

    public List<Double> percentGenreElse(){
        boolean flag = false;
        List<Double> gradesElse = new ArrayList<>();
        List<Double> gradesGenre = new ArrayList<>();
        List<Double> gradesFinal = new ArrayList<>();
        double gradeElse;
        double gradeGenres=0;
       // float numLoudness[] = Maps.getInstance().PutInloudness(priority.getFilters().getLoudness());
        //HashMap<String, Float> priorityMap = Maps.getInstance().PutInPriority(priority.getPrioLoudness(), priority.getPrioTempo());
        //double percentLoudness = Maps.getInstance().PutInPercents(priority.getPrioLoudness());
        //float numTempo[] = Maps.getInstance().PutInTempo(priority.getFilters().getTempo());
        //double percentTempo = Maps.getInstance().PutInPercents(priority.getPrioTempo());
        double percentGenre = Maps.getInstance().PutInPercents(priority.getPrioGenre());
        double maxGrade = 100-(100/Maps.getInstance().getSecondGenre().size());
        double step = maxGrade/(Maps.getInstance().getSecondGenre().size()-1);
        double gradeOtherGenre;
        List<Double> otherGenresGrade = new ArrayList<>();
        for(int i=0; i<Maps.getInstance().getSecondGenre().size();i++){
            gradeOtherGenre = maxGrade -step * i;
            otherGenresGrade.add(gradeOtherGenre);
        }
        for(int i=0;i<SulationSinger.genres.size();i++){
           if(SulationSinger.genres.get(i).equals(priority.getFilters().getGenre())){
               gradeGenres = 100 * percentGenre;
           }
           else{
               for(int j=0;j<Maps.getInstance().getSecondGenre().size();j++){
                   if(SulationSinger.genres.get(i).equals(Maps.getInstance().getSecondGenre().get(j))){
                       gradeGenres = otherGenresGrade.get(j);
                   }
               }
           }
           gradesGenre.add(gradeGenres);
        }
        if(priority.getPrioLoudness().equals("medium") || priority.getPrioLoudness().equals("low")){
            gradesElse = percentTempoLoudness("loudness");
        }
        else {
            gradesElse = percentTempoLoudness("tempo");
        }
        for(int i=0; i<SulationSinger.genres.size();i++){
             double finalGrade = gradesElse.get(i)+ gradesGenre.get(i);
             gradesFinal.add(finalGrade);
        }
           return gradesFinal;
    }
}