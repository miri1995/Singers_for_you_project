package com.example.myapplicationtest;

import java.io.Serializable;

public class Filters implements Serializable {

    private String genre;
    private String target_Audience;
    private String beat;
    private String location;

    public Filters(String Genre1, String Target_Audience1, String Beat1, String Location1) {
        this.genre = Genre1;
        this.target_Audience = Target_Audience1;
        this.beat = Beat1;
        this.location = Location1;
    }

    public Filters() {
    }

    public String getBeat() {
        return beat;
    }

    public String getGenre() {
        return genre;
    }

    public String getLocation() {
        return location;
    }

    public String getTarget_Audience() {
        return target_Audience;
    }

    public void setBeat(String beat) {
        this.beat = beat;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTarget_Audience(String target_Audience) {
        this.target_Audience = target_Audience;
    }
}