package com.example.myapplicationtest.Logic;



public class Priority {
    private String prioGenre="low";
    private String prioloudness="high";
    private String priotempo="medium";
   // private Filters filters = new Filters("genre","loudness","tempo");

    public Priority(String prioGenre, String prioloudness, String priotempo)
    {
        this.prioGenre=prioGenre;
        this.prioloudness = prioloudness;
        this.priotempo= priotempo;
        //initialize();

    }

    public Priority(){
        this.prioGenre=prioGenre;
        this.prioloudness = prioloudness;
        this.priotempo= priotempo;
    }

    /**
     * @return genre = chosen genre
     */
    public String getGenre() {
        return prioGenre;
    }
    /**
     * @return loudness = chosen loudness
     */
    public String getLoudness() {
        return prioloudness;
    }
    /**
     * @return tempo = chosen tempo
     */
    public String getTempo() {
        return priotempo;
    }
    /**
     * connect
     */
       public void getCon(Filters filters,Priority priority) {
        if (filters.getGenre() != null) {
            //StartConnector m = new StartConnector();
           // m.initialize(filters,priority);
        }
    }

        public void initialize(Filters filters){
            getCon(filters,this);
        }
    }

