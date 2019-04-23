package com.example.myapplicationtest;

import java.util.HashMap;
import java.util.List;

public interface IQuery {

     String MapBeat(String genre, String loudness, String tempo,
                          HashMap priority, String prioLoudness, String prioTempo, String prioGenre,
                          List<String> otherGenre, boolean popular, String instrument,
                          String whichtableloudness, String whichtabletempo, String whichcolloudness, String whichcoltempo);

    String UserInput(String genre, String element2, String element3,String instrument,String prioGenre,
                     String prioElement2, String prioElement3,boolean popular);

    String GetSol(String BeatQ, String genre,String element2,String element3,String prioGenre,String prioElement2,String prioElement3,
                  List<String> otherGenre,List<String> otherTopic,List<String> otherGoal,boolean popular);
}
