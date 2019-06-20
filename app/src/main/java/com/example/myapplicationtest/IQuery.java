package com.example.myapplicationtest;

import java.util.HashMap;
import java.util.List;

public interface IQuery {

    // builds the query according to the user choices and priorities
     String MapBeat(String genre, String loudness, String tempo,
                          HashMap priority, String prioLoudness, String prioTempo, String prioGenre,
                          List<String> otherGenre, boolean popular, String instrument,
                          String whichtableloudness, String whichtabletempo, String whichcolloudness, String whichcoltempo);
    // processing the user choices
    String UserInput(String genre, String element2, String element3,String instrument,String prioGenre,
                     String prioElement2, String prioElement3,boolean popular);

    // creates the final query - with the final elements
    String GetSol(String BeatQ, String genre,String element2,String element3,String prioGenre,String prioElement2,String prioElement3,
                  List<String> otherGenre,List<String> otherTopic,List<String> otherGoal,boolean popular);
}
