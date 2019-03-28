package com.example.myapplicationtest.Logic;


import java.io.Serializable;

public class Priority implements Serializable {
    private String prioGenre;
    private String prioloudness;
    private String priotempo;
    // private Filters filters = new Filters("genre","loudness","tempo");

    public Priority(String prioGenre, String prioloudness, String priotempo) {
        this.prioGenre = prioGenre;
        this.prioloudness = prioloudness;
        this.priotempo = priotempo;
        //initialize();

    }

    public Priority() {
       /* this.prioGenre = prioGenre;
        this.prioloudness = prioloudness;
        this.priotempo = priotempo;*/
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
     * connect
     */
      /* public void getCon(Filters filters,Priority priority) {
        if (filters.getGenre() != null) {
            //StartConnector m = new StartConnector();
           // m.initialize(filters,priority);
        }
    }*/

     /*   public void initialize(Filters filters){
            getCon(filters,this);
        }*/

}