package com.example.myapplicationtest.SingersLogic;

import java.io.Serializable;

public class Filters implements Serializable {

    private String genre;
    private String loudness;
    private String tempo;
    //  private Priority priority = new Priority("high","medium","low");

    public Filters(String genre, String loudness, String tempo)
    {
        this.genre=genre;
        this.loudness = loudness;
        this.tempo = tempo;

    }

    public Filters(){

    }

    /**
     * @return genre = chosen genre
     */
    public String getGenre() {
        return genre;
    }
    /**
     * @return loudness = chosen loudness
     */
    public String getLoudness() {
        return loudness;
    }
    /**
     * @return tempo = chosen tempo
     */
    public String getTempo() {
        return tempo;
    }




}