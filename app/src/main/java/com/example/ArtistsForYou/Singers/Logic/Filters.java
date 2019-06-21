package com.example.ArtistsForYou.Singers.Logic;

import java.io.Serializable;

public class Filters implements Serializable {

    private String genre;
    private String loudness;
    private String tempo;


    public Filters(String genre, String loudness, String tempo)
    {
        this.genre=genre;
        this.loudness = loudness;
        this.tempo = tempo;

    }

    public Filters(){

    }

    /**
     * @return genreSinger = chosen genreSinger
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