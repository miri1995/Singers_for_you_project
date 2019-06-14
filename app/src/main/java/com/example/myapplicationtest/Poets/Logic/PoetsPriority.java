package com.example.myapplicationtest.Poets.Logic;

import java.io.Serializable;

public class PoetsPriority implements Serializable {
    private String prioGenre;
    private String prioSubject;
    private String prioGoal;
    private PoetsFilters filters;
   // private boolean pop;


    public PoetsPriority(String prioGenre, String prioSubject, String prioGoal, PoetsFilters filters) {
        this.prioGenre = prioGenre;
        this.prioSubject = prioSubject;
        this.prioGoal = prioGoal;
        this.filters=filters;
    //    this.pop=pop;
        //initialize();

    }

    public PoetsPriority() {

    }

    public PoetsFilters getFilters(){
        return this.filters;
    }

    /**
     * @return genreSinger = chosen genreSinger
     */
    public String getPrioGenre() {
        return prioGenre;
    }

    /**
     * @return loudness = chosen loudness
     */
    public String getPrioSubject() {
        return prioSubject;
    }

    /**
     * @return tempo = chosen tempo
     */
    public String getPrioGoal() {
        return prioGoal;
    }

   /* public boolean getPopular(){
        return this.pop;
    }*/
}
