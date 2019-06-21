package com.example.ArtistsForYou.Poets.Logic;

import java.io.Serializable;

public class PoetsFilters implements Serializable {

    private String genre;
    private String subject;
    private String goal;


    public PoetsFilters(String genre, String subject, String goal)
    {
        this.genre=genre;
        this.subject = subject;
        this.goal = goal;

    }

    public PoetsFilters(){

    }

    /**
     * @return genreSinger = chosen genreSinger
     */
    public String getGenre() {
        return genre;
    }
    /**
     * @return subject = chosen subject
     */
    public String getSubject() {
        return subject;
    }
    /**
     * @return goal = chosen goal
     */
    public String getGoal() {
        return goal;
    }



}
