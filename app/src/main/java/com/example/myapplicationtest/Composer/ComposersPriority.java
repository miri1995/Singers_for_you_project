package com.example.myapplicationtest.Composer;

import com.example.myapplicationtest.SingersLogic.Filters;

import java.io.Serializable;

public class ComposersPriority implements Serializable {
    private String prioGenre;
    private String prioloudness;
    private String priotempo;
    private Filters filters;
    private String prioMusical_instrument;
    private boolean pop;
    // private Filters filters = new Filters("genre","loudness","tempo");

    public ComposersPriority(String prioGenre, String prioloudness, String priotempo,String prioMusical_instrument,
                             Filters filters, boolean pop) {
        this.prioGenre = prioGenre;
        this.prioloudness = prioloudness;
        this.priotempo = priotempo;
        this.prioMusical_instrument=prioMusical_instrument;
        this.filters=filters;
        this.pop=pop;
        //initialize();

    }

    public ComposersPriority() {

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

    /**
     *
     * @return prioMusical_instrument
     */
    public String getPrioMusical_instrument(){return  this.prioMusical_instrument;}

    public boolean getPopular(){
        return this.pop;
    }
}
