package com.example.myapplicationtest.Composer.Logic;

import java.io.Serializable;

public class ComposersPriority implements Serializable {
    private String prioGenre;
    private String prioloudness;
    private String priotempo;
    private ComposerFilters filters;

    public ComposersPriority(String prioGenre, String prioloudness, String priotempo,
                             ComposerFilters filters) {
        this.prioGenre = prioGenre;
        this.prioloudness = prioloudness;
        this.priotempo = priotempo;
        this.filters=filters;
    }

    public ComposersPriority() {

    }

    public ComposerFilters getFilters(){
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

    /**
     *
     * @return prioMusical_instrument
     */
   // public String getPrioMusical_instrument(){return  this.prioMusical_instrument;}

 /*   public boolean getPopular(){
        return this.pop;
    }*/
}
