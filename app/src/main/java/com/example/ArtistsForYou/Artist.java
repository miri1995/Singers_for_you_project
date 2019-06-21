package com.example.ArtistsForYou;

public class Artist {
    private String name;

    private String precent;

    public Artist(String name, String precent) {

        this.name = name;
        this.precent = precent;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrecent() {
        return precent;
    }

    public void setPrecent(String precent) {
        this.precent = precent;
    }
}
