package com.example.myapplicationtest;

import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.Poets.PoetsPriority;
import com.example.myapplicationtest.SingersLogic.Priority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FittingPercents {
    private Priority priority;
    private PoetsPriority prioirtyPoets;

    public FittingPercents(Priority priority,PoetsPriority prioirtyPoets) {
        this.priority = priority;
        this.prioirtyPoets =prioirtyPoets;
    }

    public List<Double> percentTempoLoudness(String flag) {
        List<Double> gradesLoudness = new ArrayList<>();
        List<Double> gradesTempo = new ArrayList<>();
        List<Double> gradesFinal = new ArrayList<>();
        double gradeTempo;
        double gradeLoudness;
        double numLoudness[] = Maps.getInstance().PutInloudness(priority.getFilters().getLoudness());
        HashMap<String, Double> priorityMap = Maps.getInstance().PutInPriority(priority.getPrioLoudness(), priority.getPrioTempo());
        double percentLoudness = Maps.getInstance().PutInPercents(priority.getPrioLoudness());
        double numTempo[] = Maps.getInstance().PutInTempo(priority.getFilters().getTempo());
        double percentTempo = Maps.getInstance().PutInPercents(priority.getPrioTempo());
        if (priority.getFilters().getTempo().equals(EnumsSingers.Fast.getEnums())
                || priority.getFilters().getTempo().equals(EnumsSingers.Slow.getEnums())) {
            if(priority.getPrioTempo().equals(EnumsSingers.Medium.getEnums()) || priority.getPrioTempo().equals(EnumsSingers.Low.getEnums())){
                for (int i = 0; i < SulationSinger.tempo.size(); i++) {
                    if(priority.getFilters().getTempo().equals(EnumsSingers.Slow.getEnums()) && SulationSinger.tempo.get(i)< numTempo[0]){
                        gradeTempo = 100 * percentTempo;
                    }
                    else if (priority.getFilters().getTempo().equals(EnumsSingers.Fast.getEnums()) && SulationSinger.tempo.get(i) > numTempo[0]){
                        gradeTempo = 100 * percentTempo;
                    }
                    else {
                        double step = (Math.abs(/*Double.parseDouble*/(SulationSinger.tempo.get(i)) - numTempo[0])) / priorityMap.get(priority.getPrioTempo());
                        gradeTempo = 100 * (1 - step) * percentTempo;
                    }
                    gradesTempo.add(gradeTempo);
                }
            }
            if (priority.getFilters().getLoudness().equals(EnumsSingers.Weak.getEnums())
                    || priority.getFilters().getLoudness().equals(EnumsSingers.Strong.getEnums())) {
                if(priority.getPrioLoudness().equals(EnumsSingers.Medium.getEnums())
                        || priority.getPrioLoudness().equals(EnumsSingers.Low.getEnums())){
                    for (int i = 0; i < SulationSinger.loudness.size(); i++) {
                        if(priority.getFilters().getLoudness().equals(EnumsSingers.Weak.getEnums()) && SulationSinger.loudness.get(i) > numLoudness[0]){
                            gradeLoudness = 100 * percentLoudness;
                        }
                        else if( priority.getFilters().getLoudness().equals(EnumsSingers.Strong.getEnums()) && SulationSinger.loudness.get(i) < numLoudness[0]){
                            gradeLoudness = 100 * percentLoudness;
                        }
                        else{
                            double step = (Math.abs(/*Double.parseDouble*/(SulationSinger.loudness.get(i)) - numLoudness[0])) / priorityMap.get(priority.getPrioLoudness());
                            gradeLoudness = 100 * (1 - step) * percentLoudness;
                        }
                        gradesLoudness.add(gradeLoudness);
                    }
                }
            } else {
                if(priority.getPrioLoudness().equals(EnumsSingers.Medium.getEnums())
                        || priority.getPrioLoudness().equals(EnumsSingers.Low.getEnums())){
                    for (int i = 0; i < SulationSinger.loudness.size(); i++) {
                        if(SulationSinger.loudness.get(i)>numLoudness[0] && SulationSinger.loudness.get(i)<numLoudness[1]){
                            gradeLoudness = 100 * percentLoudness;
                        }
                        else{
                            double step;
                            if (Math.abs(/*Double.parseDouble*/(SulationSinger.loudness.get(i)) - numLoudness[0]) < Math.abs(/*Integer.parseInt*/(SulationSinger.loudness.get(i)) - numLoudness[1])) {
                                step = 2 * (Math.abs(/*Double.parseDouble*/(SulationSinger.loudness.get(i)) - numLoudness[0])) / priorityMap.get(priority.getPrioLoudness());
                            } else {
                                step = 2* (Math.abs(/*Double.parseDouble*/(SulationSinger.loudness.get(i)) - numLoudness[1])) / priorityMap.get(priority.getPrioLoudness());
                            }

                            gradeLoudness = 100 * (1 - step) * percentLoudness;
                        }

                        gradesLoudness.add(gradeLoudness);
                    }
                }
            }
        }
        else {
            if (priority.getPrioTempo().equals(EnumsSingers.Medium.getEnums())
                    || priority.getPrioTempo().equals(EnumsSingers.Low.getEnums())){
                for (int i = 0; i < SulationSinger.tempo.size(); i++) {
                    if(SulationSinger.tempo.get(i)>numTempo[0] && SulationSinger.tempo.get(i)<numTempo[1]){
                        gradeTempo = 100 * percentTempo;
                    }
                    else{
                        double step;
                        if (Math.abs(/*Integer.parseInt*/(SulationSinger.tempo.get(i)) - numTempo[0]) < Math.abs(/*Integer.parseInt*/(SulationSinger.tempo.get(i)) - numTempo[1])) {
                            step = 2* (Math.abs(/*Double.parseDouble*/(SulationSinger.tempo.get(i)) - numTempo[0])) / priorityMap.get(priority.getPrioTempo());
                        } else {
                            step = 2* (Math.abs(/*Double.parseDouble*/(SulationSinger.tempo.get(i)) - numTempo[1])) / priorityMap.get(priority.getPrioTempo());
                        }

                        gradeTempo = 100 * (1 - step) * percentTempo;
                    }

                    gradesTempo.add(gradeTempo);
                }
            }
            if (priority.getFilters().getLoudness().equals(EnumsSingers.Weak.getEnums())
                    || priority.getFilters().getTempo().equals(EnumsSingers.Strong.getEnums())) {
                if(priority.getPrioLoudness().equals(EnumsSingers.Medium.getEnums())
                        || priority.getPrioLoudness().equals(EnumsSingers.Low.getEnums())){
                    for (int i = 0; i < SulationSinger.loudness.size(); i++) {
                        if(priority.getFilters().getLoudness().equals(EnumsSingers.Weak.getEnums()) && SulationSinger.loudness.get(i) > numLoudness[0]){
                            gradeLoudness = 100 * percentLoudness;
                        }
                        else if ( priority.getFilters().getTempo().equals(EnumsSingers.Strong.getEnums()) && SulationSinger.loudness.get(i) < numLoudness[0]){
                            gradeLoudness = 100 * percentLoudness;
                        }
                        else{
                            double step = (Math.abs(/*Double.parseDouble*/(SulationSinger.loudness.get(i)) - numLoudness[0])) / priorityMap.get(priority.getPrioLoudness());
                            gradeLoudness = 100 * (1 - step) * percentLoudness;
                        }

                        gradesLoudness.add(gradeLoudness);
                    }
                }
            }
            else {
                if(priority.getPrioLoudness().equals(EnumsSingers.Medium.getEnums())
                        || priority.getPrioLoudness().equals(EnumsSingers.Low.getEnums())){
                    for (int i = 0; i < SulationSinger.loudness.size(); i++) {
                        if(SulationSinger.loudness.get(i)>numLoudness[0] && SulationSinger.loudness.get(i)<numLoudness[1]){
                            gradeLoudness = 100 * percentLoudness;
                        }
                        else{
                            double step;
                            if (Math.abs(/*Integer.parseInt*/(SulationSinger.loudness.get(i)) - numLoudness[0]) < Math.abs(/*Integer.parseInt*/(SulationSinger.loudness.get(i)) - numLoudness[1])) {
                                step = 2* (Math.abs(/*Double.parseDouble*/(SulationSinger.loudness.get(i)) - numLoudness[0])) / priorityMap.get(priority.getPrioLoudness());
                            } else {
                                step = 2* (Math.abs(/*Double.parseDouble*/(SulationSinger.loudness.get(i)) - numLoudness[1])) / priorityMap.get(priority.getPrioLoudness());
                            }

                            gradeLoudness = 100 * (1 - step) * percentLoudness;
                        }

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
        double maxGrade = 100-(100/(double)Maps.getInstance().getSecondGenre().size());
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
                       gradeGenres = otherGenresGrade.get(j) * percentGenre;
                   }
               }
           }
           gradesGenre.add(gradeGenres);
        }
        if(priority.getPrioLoudness().equals(EnumsSingers.Medium.getEnums())
                || priority.getPrioLoudness().equals(EnumsSingers.Low.getEnums())){
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