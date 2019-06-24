package com.example.ArtistsForYou.Singers.Logic;



import android.util.Log;

import com.example.ArtistsForYou.CoupleDistance;
import com.example.ArtistsForYou.Enums.EnumsSingers;
import com.example.ArtistsForYou.IQuery;
import com.example.ArtistsForYou.Maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Query class - responsible for making the queries.
 */
public class Query_Singer implements IQuery {

    /**
     * creates the queries according to the user's choices.
     * @param genre = user's choice of genreSinger
     * @param loudness = user's choice of loudness.
     * @param tempo = user's choice of tempo.
     * @return sol = the final query
     */
    public String MapBeat(String genre,String loudness,String tempo,
                          HashMap priority,String prioLoudness,String prioTempo,String prioGenre,
                          List<String> otherGenre,boolean needToIncreaseSol,String instrument,
                          String whichtableloudness,String whichtabletempo,String whichcolloudness,String whichcoltempo){
        int temp=0;
        String q="";
        String mapGenre="";
        // the base query which will be the first part of all the quries

        mapGenre = "#standardSQL\n" + "SELECT distinct artists.artist_name,songs.song_loudness,songs.song_tempo," +
                "genre.genre\n" +
                "from genre\n" +
                "INNER JOIN genreartists on genre.genre_id = genreartists.genre_id\n" +
                "INNER JOIN artists on artists.artist_id = genreartists.artist_id\n" +
                "INNER JOIN songs on songs.song_artist_id = artists.artist_id\n"; //+

        //"WHERE artists.artist_id IN ";
        // check that this features are not null
        String notNull = " AND "+whichtableloudness + " IS NOT NULL AND "+whichtabletempo+ " IS NOT NULL";
        double num_tempo;
        double num_loudness;
        switch (EnumsSingers.valueOf(tempo)) {
            case Slow:
                temp = 1;
                break;
            case Medium:
                temp = 2;
                break;
            case Fast:
                temp = 3;
                break;
        }
        // according to the loudness and tempo chosen by the user creates the continuation of the query.
        double numLoud[] = Maps.getInstance().PutInloudness(loudness,needToIncreaseSol);
        double numTempo[] = Maps.getInstance().PutInTempo(tempo,needToIncreaseSol);
        switch (EnumsSingers.valueOf(loudness)) {
            case Weak:
                num_loudness = numLoud[0] - (double) priority.get(prioLoudness);
                if (temp == 1) {
                    num_tempo = numTempo[0] + (double) priority.get(prioTempo);
                    q = mapGenre + "WHERE "+whichcoltempo  + "<=\"" + num_tempo + "\"" + "AND "+whichcolloudness+ ">=\"" + num_loudness + "\"" +
                            notNull;
                } else if (temp == 2) {
                    num_tempo = numTempo[0] - ((double) priority.get(prioTempo) / 2);
                    double num_tempo2 = numTempo[1] + ((double) priority.get(prioTempo) / 2);
                    q = mapGenre +
                            "WHERE "+whichcoltempo + " between \"" + num_tempo + "\"" + "and \"" + num_tempo2 + "\"" + "AND "+whichcolloudness+">=\"" + num_loudness + "\"" +
                            notNull;
                } else {
                    num_tempo = numTempo[0] - (double) priority.get(prioTempo);
                    q = mapGenre +
                            "WHERE "+whichcoltempo+">=\"" + num_tempo + "\"" + "AND "+whichcolloudness+">=\"" + num_loudness + "\"" +
                            notNull;
                }
                break;
            case Normal:
                num_loudness = (double) numLoud[0] - ((double) priority.get(prioLoudness) / 2);
                double num_loudness2 = (double) numLoud[1] + ((double) priority.get(prioLoudness) / 2);
                if (temp == 1) {
                    num_tempo = numTempo[0] + (double) priority.get(prioTempo);
                    q = mapGenre +
                            "WHERE "+whichcoltempo  +"<=\"" + num_tempo + "\"" + " AND "+whichcolloudness+" BETWEEN \"" + num_loudness + "\"" + "and \"" + num_loudness2 + "\"" +
                            notNull;
                } else if (temp == 2) {
                    num_tempo = numTempo[0] - ((double) priority.get(prioTempo) / 2);
                    double num_tempo2 = numTempo[1] + ((double) priority.get(prioTempo) / 2);
                    q = mapGenre +
                            "WHERE "+whichcoltempo  +" between \"" + num_tempo + "\"" + "and \"" + num_tempo2 + "\"" + "AND "+whichcolloudness+ " BETWEEN \"" + num_loudness + "\"" + "and \"" + num_loudness2 + "\"" +
                            notNull;
                } else {
                    num_tempo = numTempo[0] - (double) priority.get(prioTempo);
                    q = mapGenre +
                            "WHERE "+whichcoltempo  +">=\"" + num_tempo + "\"" + "AND "+whichcolloudness+ " BETWEEN \"" + num_loudness + "\"" + "and \"" + num_loudness2 + "\"" +
                            notNull;
                }
                break;
            case Strong:
                num_loudness = numLoud[0] + (double) priority.get(prioLoudness);
                if (temp == 1) {
                    num_tempo = numTempo[0] + (double) priority.get(prioTempo);
                    q = mapGenre +
                            "WHERE "+whichcoltempo  +"<=\"" + num_tempo + "\"" + " AND "+whichcolloudness+ "<=\"" + num_loudness + "\"" +
                            notNull;
                } else if (temp == 2) {
                    num_tempo = numTempo[0] - ((double) priority.get(prioTempo) / 2);
                    double num_tempo2 = numTempo[1] + ((double) priority.get(prioTempo) / 2);
                    q = mapGenre +
                            "WHERE "+whichcoltempo  + " between \"" + num_tempo + "\"" + "and \"" + num_tempo2 + "\"" + "AND "+whichcolloudness+ "<=\"" + num_loudness + "\"" +
                            notNull;
                } else {
                    num_tempo = numTempo[0] - (double) priority.get(prioTempo);
                    q = mapGenre +
                            "WHERE "+whichcoltempo  +">=\"" + num_tempo + "\"" + "AND "+whichcolloudness+"<=\"" + num_loudness + "\"" +
                            notNull;
                }
                break;
        }

        // sends to a function that is responsible for Concatenation of the strings into final query.
        String sol=GetSol(q,genre,null,null,prioGenre,null,null,otherGenre,null,null,needToIncreaseSol);
        //returns the final query
        return sol;
    }


    /**
     * receives the user's choices and compose from them the matching query.
     * @param genre = user's choice of genreSinger
     * @param element2 = user's choice of element2.
     * @param element3 = user's choice of element3.
     * @return q = the matching query
     */

    public String UserInput(String genre, String element2, String element3,String instrument,String prioGenre, String prioElement2, String prioElement3,boolean needToIncreaseSol){
        HashMap<String,Double> priority = new HashMap<>();
        priority = Maps.getInstance().PutInPriority(prioElement2,prioElement3,needToIncreaseSol);
        List<String> couples=new ArrayList<>();
        List<String> otherGenre=new ArrayList<>();
        if (prioGenre.equals(EnumsSingers.Medium.getEnums()) || prioGenre.equals(EnumsSingers.Low.getEnums())){
            couples = orderCouples(genre,prioGenre,"genreSinger",20,needToIncreaseSol);
            otherGenre = getOtherElement(couples,genre);
            Maps.getInstance().getFromQuery(otherGenre,"genreSinger");
        }
        String q="";
        String whichtableloudness = "songs.song_loudness";
        String whichtabletempo = "songs.song_tempo";
        String whichcolloudness = "song_loudness";
        String whichcoltempo = "song_tempo";
        q=MapBeat(genre,element2,element3,priority,prioElement2,prioElement3,prioGenre,otherGenre,needToIncreaseSol,
                null,whichtableloudness,whichtabletempo,whichcolloudness,whichcoltempo);

        Log.d("D","queryComposer "+q);
        return q;

    }

      /**
     * composes all the query parts into one query.
     * @param BeatQ  = the combination of the tempo and loudness chosen by the user.
     * @param genre = user's choice of genreSinger.
     * @return lastQ = the final query
     */


    public String GetSol(String BeatQ, String genre,String element2,String element3,String prioGenre,String prioElement2,String prioElement3,
                         List<String> otherGenre,List<String> otherTopic,List<String> otherGoal,boolean needToIncreaseSol){
        String hotness=null;
        //if(needToIncreaseSol){
             hotness=" order by artists.artist_hotness DESC";
     //   }else{

                hotness=" order by artists.artist_hotness DESC";

      //  }


        StringBuilder quGenre = new StringBuilder();
        if(prioGenre.equals(EnumsSingers.Medium.getEnums()) || prioGenre.equals(EnumsSingers.Low.getEnums())) {
            for (int i = 0; i < otherGenre.size(); i++) {
                    quGenre.append(" OR genre.genre=\"" + otherGenre.get(i) + "\"");
            }
        }

        String lastQ="";
        lastQ=BeatQ+" AND (genre.genre=\""+genre+"\""+quGenre+")"+hotness;
        return lastQ;
    }



    public List<String> orderCouples(String genre, String prioGenre,String which,int threshold,boolean needsToIncrease){
        if(needsToIncrease){
            threshold/=2;
        }
        Map<String, Integer> map = new HashMap<>();
        CoupleDistance coupleDistance = CoupleDistance.getInstance();
        if(which.equals("genreSinger")){
            map = coupleDistance.getGenreSingerMap();
        }
        else if(which.equals("topic")){
            map = coupleDistance.getTopicMap();
        }
        else if (which.equals("goal")){
            map = coupleDistance.getGoalMap();
        }
        else if (which.equals("genrePoet")){
            map=coupleDistance.getGenrePoetMap();
        }

        Map<String, Integer> miniMap = new HashMap<>();
        List<String> couples=new ArrayList<>();
        List<Integer> vals = new ArrayList<>();
        for (Map.Entry<String, Integer> entry :map.entrySet()) {
            if (entry.getKey().contains(genre)) {
                switch (EnumsSingers.valueOf(prioGenre)) {
                    case Medium:
                        if (entry.getValue() > threshold) {
                            miniMap.put(entry.getKey(), entry.getValue());
                            vals.add(entry.getValue());
                        }
                        break;
                    case Low:
                        if (entry.getValue() <= threshold) {
                            miniMap.put(entry.getKey(), entry.getValue());
                            vals.add(entry.getValue());
                        }
                        break;

                }
            }
        }
        Collections.sort(vals);
        Collections.reverse(vals);
        //int i=0;
        for(int i=0;i<vals.size();i++) {
            for (Map.Entry<String, Integer> entry : miniMap.entrySet()) {

                if (entry.getValue() == vals.get(i)) {
                    if(!couples.contains(entry.getKey())){
                        couples.add(entry.getKey());
                    }

                }

            }
        }

        return couples;

    }

    public List getOtherElement(List<String> couples, String element){
        List <String> secondElement = new ArrayList<>();
        for(int i=0;i<couples.size();i++){
            String[] tokens = couples.get(i).split(",");
            if(!tokens[0].equals(element)){
                secondElement.add(tokens[0]);
            }
            else{
                secondElement.add(tokens[1]);
            }

        }
        return secondElement;
    }


}