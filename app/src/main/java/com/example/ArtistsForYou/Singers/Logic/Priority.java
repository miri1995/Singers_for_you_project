package com.example.ArtistsForYou.Singers.Logic;


import java.io.Serializable;

public class Priority implements Serializable {
    private String prioGenre;
    private String prioloudness;
    private String priotempo;
    private Filters filters;
    private boolean pop;


    public Priority(String prioGenre, String prioloudness, String priotempo,Filters filters, boolean pop) {
        this.prioGenre = prioGenre;
        this.prioloudness = prioloudness;
        this.priotempo = priotempo;
        this.filters=filters;
        this.pop=pop;

    }

    public Priority() {

    }

    public Filters getFilters(){
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