package com.example.myapplicationtest;

import com.example.myapplicationtest.Composer.Logic.ComposersPriority;
import com.example.myapplicationtest.Enums.EnumsSingers;
import com.example.myapplicationtest.Poets.Activity.SolutionPoets;
import com.example.myapplicationtest.Poets.Logic.PoetsPriority;
import com.example.myapplicationtest.Singers.Logic.Priority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FittingPercents {
    private Priority priority;
    private PoetsPriority prioirtyPoets;
    private ComposersPriority composersPriority;


    public FittingPercents(Priority priority,PoetsPriority prioirtyPoets,
                           ComposersPriority composersPriority) {
        this.priority = priority;
        this.prioirtyPoets =prioirtyPoets;
        this.composersPriority=composersPriority;

    }

    public List<Double> percentTempoLoudness(String flag,String loudness,String tempo,
                                             String prioLoudness,String prioTempo,
                                             List<Double> tempoList,List<Double> loudnessList,boolean needToIncreece) {
        List<Double> gradesLoudness = new ArrayList<>();
        List<Double> gradesTempo = new ArrayList<>();
        List<Double> gradesFinal = new ArrayList<>();
        double gradeTempo;
        double gradeLoudness;
        double numLoudness[] = Maps.getInstance().PutInloudness(loudness,needToIncreece);
        HashMap<String, Double> priorityMap = Maps.getInstance().PutInPriority(prioLoudness, prioTempo,needToIncreece);
        double percentLoudness = Maps.getInstance().PutInPercents(prioLoudness);
        double numTempo[] = Maps.getInstance().PutInTempo(tempo,needToIncreece);
        double percentTempo = Maps.getInstance().PutInPercents(prioTempo);
        if (tempo.equals(EnumsSingers.Fast.getEnums())
                || tempo.equals(EnumsSingers.Slow.getEnums())) {
            if(prioTempo.equals(EnumsSingers.Medium.getEnums()) || prioTempo.equals(EnumsSingers.Low.getEnums())){
                for (int i = 0; i < tempoList.size(); i++) {
                    if(tempo.equals(EnumsSingers.Slow.getEnums()) && tempoList.get(i)< numTempo[0]){
                        gradeTempo = 100 * percentTempo;
                    }
                    else if (tempo.equals(EnumsSingers.Fast.getEnums()) && tempoList.get(i) > numTempo[0]){
                        gradeTempo = 100 * percentTempo;
                    }
                    else {
                        double step = (Math.abs((tempoList.get(i)) - numTempo[0])) / priorityMap.get(prioTempo);
                        gradeTempo = 100 * (1 - step) * percentTempo;
                    }
                    gradesTempo.add(gradeTempo);
                }
            }
            if (loudness.equals(EnumsSingers.Weak.getEnums())
                    || loudness.equals(EnumsSingers.Strong.getEnums())) {
                if(prioLoudness.equals(EnumsSingers.Medium.getEnums())
                        || prioLoudness.equals(EnumsSingers.Low.getEnums())){
                    for (int i = 0; i < loudnessList.size(); i++) {
                        if(loudness.equals(EnumsSingers.Weak.getEnums()) && loudnessList.get(i) > numLoudness[0]){
                            gradeLoudness = 100 * percentLoudness;
                        }
                        else if( loudness.equals(EnumsSingers.Strong.getEnums()) && loudnessList.get(i) < numLoudness[0]){
                            gradeLoudness = 100 * percentLoudness;
                        }
                        else{
                            double step = (Math.abs((loudnessList.get(i)) - numLoudness[0])) / priorityMap.get(prioLoudness);
                            gradeLoudness = 100 * (1 - step) * percentLoudness;
                        }
                        gradesLoudness.add(gradeLoudness);
                    }
                }
            } else {
                if(prioLoudness.equals(EnumsSingers.Medium.getEnums())
                        || prioLoudness.equals(EnumsSingers.Low.getEnums())){
                    for (int i = 0; i < loudnessList.size(); i++) {
                        if(loudnessList.get(i)>numLoudness[0] && loudnessList.get(i)<numLoudness[1]){
                            gradeLoudness = 100 * percentLoudness;
                        }
                        else{
                            double step;
                            if (Math.abs((loudnessList.get(i)) - numLoudness[0]) < Math.abs((loudnessList.get(i)) - numLoudness[1])) {
                                step = 2 * (Math.abs((loudnessList.get(i)) - numLoudness[0])) / priorityMap.get(prioLoudness);
                            } else {
                                step = 2* (Math.abs((loudnessList.get(i)) - numLoudness[1])) / priorityMap.get(prioLoudness);
                            }

                            gradeLoudness = 100 * (1 - step) * percentLoudness;
                        }

                        gradesLoudness.add(gradeLoudness);
                    }
                }
            }
        }
        else {
            if (prioTempo.equals(EnumsSingers.Medium.getEnums())
                    || prioTempo.equals(EnumsSingers.Low.getEnums())){
                for (int i = 0; i < tempoList.size(); i++) {
                    if(tempoList.get(i)>numTempo[0] && tempoList.get(i)<numTempo[1]){
                        gradeTempo = 100 * percentTempo;
                    }
                    else{
                        double step;
                        if (Math.abs((tempoList.get(i)) - numTempo[0]) < Math.abs((tempoList.get(i)) - numTempo[1])) {
                            step = 2* (Math.abs((tempoList.get(i)) - numTempo[0])) / priorityMap.get(prioTempo);
                        } else {
                            step = 2* (Math.abs((tempoList.get(i)) - numTempo[1])) / priorityMap.get(prioTempo);
                        }

                        gradeTempo = 100 * (1 - step) * percentTempo;
                    }

                    gradesTempo.add(gradeTempo);
                }
            }
            if (loudness.equals(EnumsSingers.Weak.getEnums())
                    || loudness.equals(EnumsSingers.Strong.getEnums())) {
                if(prioLoudness.equals(EnumsSingers.Medium.getEnums())
                        || prioLoudness.equals(EnumsSingers.Low.getEnums())){
                    for (int i = 0; i < loudnessList.size(); i++) {
                        if(loudness.equals(EnumsSingers.Weak.getEnums()) && loudnessList.get(i) > numLoudness[0]){
                            gradeLoudness = 100 * percentLoudness;
                        }
                        else if ( tempo.equals(EnumsSingers.Strong.getEnums()) && loudnessList.get(i) < numLoudness[0]){
                            gradeLoudness = 100 * percentLoudness;
                        }
                        else{
                            double step = (Math.abs((loudnessList.get(i)) - numLoudness[0])) / priorityMap.get(prioLoudness);
                            gradeLoudness = 100 * (1 - step) * percentLoudness;
                        }

                        gradesLoudness.add(gradeLoudness);
                    }
                }
            }
            else {
                if(prioLoudness.equals(EnumsSingers.Medium.getEnums())
                        || prioLoudness.equals(EnumsSingers.Low.getEnums())){
                    for (int i = 0; i < loudnessList.size(); i++) {
                        if(loudnessList.get(i)>numLoudness[0] && loudnessList.get(i)<numLoudness[1]){
                            gradeLoudness = 100 * percentLoudness;
                        }
                        else{
                            double step;
                            if (Math.abs((loudnessList.get(i)) - numLoudness[0]) < Math.abs((loudnessList.get(i)) - numLoudness[1])) {
                                step = 2* (Math.abs((loudnessList.get(i)) - numLoudness[0])) / priorityMap.get(prioLoudness);
                            } else {
                                step = 2* (Math.abs((loudnessList.get(i)) - numLoudness[1])) / priorityMap.get(prioLoudness);
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

    public List<Double> percentGenreElse(String prioGenre,List<String>genresList,String genre,
                                         String prioLoudness,String loudness,String tempo,
                                            String prioTempo,List<Double> tempoList,
                                         List<Double> loudnessList,boolean needToIncreece,String which){
        boolean flag = false;
        List<Double> gradesElse = new ArrayList<>();
        List<Double> gradesGenre = new ArrayList<>();
        List<Double> gradesFinal = new ArrayList<>();
        //double gradeElse;
        double gradeGenres=0;
        double gradeOtherGenre;
        double percentGenre = Maps.getInstance().PutInPercents(prioGenre);
        List<Double> otherGenresGrade = new ArrayList<>();
        if(which.equals("genreSinger")){
            double maxGrade = 100-(100/(double)Maps.getInstance().getSecondGenreSinger().size()+1);
            double step = maxGrade/(Maps.getInstance().getSecondGenreSinger().size());
            for(int i = 0; i<Maps.getInstance().getSecondGenreSinger().size(); i++){
                gradeOtherGenre = maxGrade -step * i;
                otherGenresGrade.add(gradeOtherGenre);
            }
            for(int i=0;i<genresList.size();i++){
                if(genresList.get(i).equals(genre)){
                    gradeGenres = 100 * percentGenre;
                }
                else{
                    for(int j = 0; j<Maps.getInstance().getSecondGenreSinger().size(); j++){
                        if(genresList.get(i).equals(Maps.getInstance().getSecondGenreSinger().get(j))){
                            gradeGenres = otherGenresGrade.get(j) * percentGenre;
                        }
                    }
                }
                gradesGenre.add(gradeGenres);
            }
        }
        else {
            double maxGrade = 100-(100/(double)Maps.getInstance().getSecondGenreComposer().size()+1);
            double step = maxGrade/(Maps.getInstance().getSecondGenreComposer().size());
            for(int i = 0; i<Maps.getInstance().getSecondGenreComposer().size(); i++){
                gradeOtherGenre = maxGrade -step * i;
                otherGenresGrade.add(gradeOtherGenre);
            }
            for(int i=0;i<genresList.size();i++){
                if(genresList.get(i).equals(genre)){
                    gradeGenres = 100 * percentGenre;
                }
                else{
                    for(int j = 0; j<Maps.getInstance().getSecondGenreComposer().size(); j++){
                        if(genresList.get(i).equals(Maps.getInstance().getSecondGenreComposer().get(j))){
                            gradeGenres = otherGenresGrade.get(j) * percentGenre;
                        }
                    }
                }
                gradesGenre.add(gradeGenres);
            }
        }

        if(prioLoudness.equals(EnumsSingers.Medium.getEnums())
                || prioLoudness.equals(EnumsSingers.Low.getEnums())){
            gradesElse = percentTempoLoudness("loudness",loudness,tempo,prioLoudness,prioTempo,
                    tempoList,loudnessList,needToIncreece);
        }
        else {
            gradesElse = percentTempoLoudness("tempo",loudness,tempo,prioLoudness,
                    prioTempo,tempoList,loudnessList,needToIncreece);
        }
        for(int i=0; i<genresList.size();i++){
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

        if(which.equals("genrePoet")){
            percentElement = Maps.getInstance().PutInPercents(prioirtyPoets.getPrioGenre());
            maxGrade = 100-(100/(double)Maps.getInstance().getSecondGenrePoet().size()+1);
            step = maxGrade/(Maps.getInstance().getSecondGenrePoet().size());
            List<Double> otherGenresGrade = new ArrayList<>();
            for(int i = 0; i<Maps.getInstance().getSecondGenrePoet().size(); i++){
                gradeOtherElement = maxGrade -step * i;
                otherGenresGrade.add(gradeOtherElement);
            }
            for(int i = 0; i< SolutionPoets.genres.size(); i++){
                if(SolutionPoets.genres.get(i).equals(prioirtyPoets.getFilters().getGenre())){
                    ElementGrade = 100 * percentElement;
                }
                else{
                    for(int j = 0; j<Maps.getInstance().getSecondGenrePoet().size(); j++){
                        if(SolutionPoets.genres.get(i).equals(Maps.getInstance().getSecondGenrePoet().get(j))){
                            ElementGrade= otherGenresGrade.get(j) * percentElement;
                        }
                    }
                }
                gradesElement.add(ElementGrade);
            }
        }
        else if(which.equals("topic")) {
            percentElement = Maps.getInstance().PutInPercents(prioirtyPoets.getPrioSubject());
            if (Maps.getInstance().getSecondTopic().size() != 0) {
                maxGrade = 100 - (100 / (double) Maps.getInstance().getSecondTopic().size()+1);
                step = maxGrade / (Maps.getInstance().getSecondTopic().size());
                List<Double> otherTopicsGrade = new ArrayList<>();
                for (int i = 0; i < Maps.getInstance().getSecondTopic().size(); i++) {
                    gradeOtherElement = maxGrade - step * i;
                    otherTopicsGrade.add(gradeOtherElement);
                }
                for (int i = 0; i < SolutionPoets.subject.size(); i++) {
                    if (SolutionPoets.subject.get(i).equals(prioirtyPoets.getFilters().getSubject())) {
                        ElementGrade = 100 * percentElement;
                    } else {
                        for (int j = 0; j < Maps.getInstance().getSecondTopic().size(); j++) {
                            if (SolutionPoets.subject.get(i).equals(Maps.getInstance().getSecondTopic().get(j))) {
                                ElementGrade = otherTopicsGrade.get(j) * percentElement;
                            }
                        }
                    }
                    gradesElement.add(ElementGrade);
                }
            } else {
                for (int i = 0; i < SolutionPoets.subject.size(); i++) {
                    ElementGrade=100 * percentElement;
                    gradesElement.add(ElementGrade);
                }
            }
        }
        else if (which.equals("goal")){
            percentElement = Maps.getInstance().PutInPercents(prioirtyPoets.getPrioGoal());
            if(Maps.getInstance().getSecondGoal().size()!=0) {
                maxGrade = 100 - (100 / (double) Maps.getInstance().getSecondGoal().size()+1);
                step = maxGrade / (Maps.getInstance().getSecondGoal().size());
                List<Double> otherGoalsGrade = new ArrayList<>();
                for (int i = 0; i < Maps.getInstance().getSecondGoal().size(); i++) {
                    gradeOtherElement = maxGrade - step * i;
                    otherGoalsGrade.add(gradeOtherElement);
                }

                for (int i = 0; i < SolutionPoets.goal.size(); i++) {
                    if (SolutionPoets.goal.get(i).equals(prioirtyPoets.getFilters().getGoal())) {
                        ElementGrade = 100 * percentElement;
                    } else {
                        for (int j = 0; j < Maps.getInstance().getSecondGoal().size(); j++) {
                            if (SolutionPoets.goal.get(i).equals(Maps.getInstance().getSecondGoal().get(j))) {
                                ElementGrade = otherGoalsGrade.get(j) * percentElement;
                            }
                        }
                    }
                    gradesElement.add(ElementGrade);
                }
            }
            else{
                for (int i = 0; i < SolutionPoets.goal.size(); i++) {
                    ElementGrade=100 * percentElement;
                    gradesElement.add(ElementGrade);
                }
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