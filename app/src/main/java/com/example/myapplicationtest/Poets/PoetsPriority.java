package com.example.myapplicationtest.Poets;

import com.example.myapplicationtest.Logic.Filters;

import java.io.Serializable;

public class PoetsPriority implements Serializable {
    private String prioGenre;
    private String prioloudness;
    private String priotempo;
    private Filters filters;
    private boolean pop;
    // private Filters filters = new Filters("genre","loudness","tempo");

    public PoetsPriority(String prioGenre, String prioloudness, String priotempo,Filters filters, boolean pop) {
        this.prioGenre = prioGenre;
        this.prioloudness = prioloudness;
        this.priotempo = priotempo;
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
    public String getPrioLoudness() {
        return prioloudness;
    }

    /**
     * @return tempo = chosen tempo
     */
    public String getPrioTempo() {
        return priotempo;
    }

    public boolean getPopular(){
        return this.pop;
    }
}
