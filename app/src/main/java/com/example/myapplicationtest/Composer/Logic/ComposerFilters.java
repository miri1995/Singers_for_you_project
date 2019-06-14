package com.example.myapplicationtest.Composer.Logic;

import java.io.Serializable;

public class ComposerFilters implements Serializable {

    private String genre;
    private String loudness;
    private String tempo;
    private String musical_instrument;

    public ComposerFilters(String genre, String loudness, String tempo,String musical_instrument)
    {
        this.genre=genre;
        this.loudness = loudness;
        this.tempo = tempo;
        this.musical_instrument=musical_instrument;

    }

    public ComposerFilters(){

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

    /**
     *
     * @return musical_instrument
     */
    public String getMusical_instrument(){
        return this.musical_instrument;
    }


}
