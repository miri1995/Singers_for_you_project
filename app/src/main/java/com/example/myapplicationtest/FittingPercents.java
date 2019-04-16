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
        //double gradeElse;
        double gradeGenres=0;
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

    public List<Double> percentElement(String which){
        List<Double> gradesElement = new ArrayList<>();
        double ElementGrade=0;
        double percentElement=0;
        double maxGrade=0;
        double step=0;
        double gradeOtherElement;
        if(which.equals("genre")){
            percentElement = Maps.getInstance().PutInPercents(prioirtyPoets.getPrioGenre());
            maxGrade = 100-(100/(double)Maps.getInstance().getSecondGenre().size());
            step = maxGrade/(Maps.getInstance().getSecondGenre().size()-1);
            List<Double> otherGenresGrade = new ArrayList<>();
            for(int i=0; i<Maps.getInstance().getSecondGenre().size();i++){
                gradeOtherElement = maxGrade -step * i;
                otherGenresGrade.add(gradeOtherElement);
            }
            for(int i=0;i<SolutionPoets.genres.size();i++){
                if(SolutionPoets.genres.get(i).equals(prioirtyPoets.getFilters().getGenre())){
                    ElementGrade = 100 * percentElement;
                }
                else{
                    for(int j=0;j<Maps.getInstance().getSecondGenre().size();j++){
                        if(SolutionPoets.genres.get(i).equals(Maps.getInstance().getSecondGenre().get(j))){
                            ElementGrade= otherGenresGrade.get(j) * percentElement;
                        }
                    }
                }
                gradesElement.add(ElementGrade);
            }
        }
        else if(which.equals("topic")){
            percentElement = Maps.getInstance().PutInPercents(prioirtyPoets.getPrioSubject());
            maxGrade = 100-(100/(double)Maps.getInstance().getSecondTopic().size());
            step = maxGrade/(Maps.getInstance().getSecondTopic().size()-1);
            List<Double> otherTopicsGrade = new ArrayList<>();
            for(int i=0; i<Maps.getInstance().getSecondTopic().size();i++){
                gradeOtherElement = maxGrade -step * i;
                otherTopicsGrade.add(gradeOtherElement);
            }
            for(int i=0;i<SolutionPoets.subject.size();i++){
                if(SolutionPoets.subject.get(i).equals(prioirtyPoets.getFilters().getSubject())){
                    ElementGrade = 100 * percentElement;
                }
                else{
                    for(int j=0;j<Maps.getInstance().getSecondTopic().size();j++){
                        if(SolutionPoets.subject.get(i).equals(Maps.getInstance().getSecondTopic().get(j))){
                            ElementGrade= otherTopicsGrade.get(j) * percentElement;
                        }
                    }
                }
                gradesElement.add(ElementGrade);
            }
        }
        else{
            percentElement = Maps.getInstance().PutInPercents(prioirtyPoets.getPrioGoal());
            maxGrade = 100-(100/(double)Maps.getInstance().getSecondGoal().size());
            step = maxGrade/(Maps.getInstance().getSecondGoal().size()-1);
            List<Double> otherGoalsGrade = new ArrayList<>();
            for(int i=0; i<Maps.getInstance().getSecondGoal().size();i++){
                gradeOtherElement = maxGrade -step * i;
                otherGoalsGrade.add(gradeOtherElement);
            }
            for(int i=0;i<SolutionPoets.goal.size();i++){
                if(SolutionPoets.goal.get(i).equals(prioirtyPoets.getFilters().getGoal())){
                    ElementGrade = 100 * percentElement;
                }
                else{
                    for(int j=0;j<Maps.getInstance().getSecondGoal().size();j++){
                        if(SolutionPoets.goal.get(i).equals(Maps.getInstance().getSecondGoal().get(j))){
                            ElementGrade= otherGoalsGrade.get(j) * percentElement;
                        }
                    }
                }
                gradesElement.add(ElementGrade);
            }
        }

      return gradesElement;
    }

    public List<Double> uniteTwoListd(List<Double>gradesElement1,List<Double>gradesElement2){
        List<Double>gradesFinal=new ArrayList<>();
        for(int i=0;i<gradesElement1.size();i++){
            double finalGrade = gradesElement1.get(i) + gradesElement2.get(i);
            gradesFinal.add(finalGrade);
        }
        return gradesFinal;
    }
}