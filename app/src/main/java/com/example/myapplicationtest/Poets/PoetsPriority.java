package com.example.myapplicationtest.Poets;

import com.example.myapplicationtest.SingersLogic.Filters;

import java.io.Serializable;

public class PoetsPriority implements Serializable {
    private String prioGenre;
    private String prioSubject;
    private String prioGoal;
    private Filters filters;
    private boolean pop;


    public PoetsPriority(String prioGenre, String prioSubject, String prioGoal, Filters filters, boolean pop) {
        this.prioGenre = prioGenre;
        this.prioSubject = prioSubject;
        this.prioGoal = prioGoal;
        this.filters=filters;
        this.pop=pop;
        //initialize();

    }

    public PoetsPriority() {

    }

    public Filters getFilters(){
        return this.filters;
    }

    /**
     * @return genre = chosen genre
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

    public boolean getPopular(){
        return this.pop;
    }
}
