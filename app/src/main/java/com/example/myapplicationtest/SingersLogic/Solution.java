package com.example.myapplicationtest.SingersLogic;

import java.util.List;

/**
 * Solution class - holds the singer's list.
 * a singleton class.
 */
public class Solution {
    private static Solution single_instance = null;
    private final  List<String> allArtists;
    private static int counter=0;

    /**
     * constructor
     */
    private Solution(List<String> allArtists)
    {
        this.allArtists=allArtists;
    }

    /**
     * get instance of the solution.
     * @param allArtists = list of artists
     */
    public synchronized static Solution getInstance(List<String> allArtists)
    {
        if (single_instance == null || counter>0) {
            single_instance = new Solution(allArtists);
        }
        return single_instance;
    }

    /**
     * get the list of artists.
     * @return  allArtists = list of artists
     */
    public List<String> getallArtists(){
        return this.allArtists;
    }

    public void clearallArtists(){
        this.allArtists.clear();
        counter++;
        System.out.print(counter);
    }




}