/*
package com.example.myapplicationtest;



import com.example.myapplicationtest.Enums.EnumsSingers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * Query class - responsible for making the queries.
 *//*

public class Query {

    */
/**
     * creates the queries according to the user's choices.
     * @param genreSinger = user's choice of genreSinger
     * @param loudness = user's choice of loudness.
     * @param tempo = user's choice of tempo.
     * @return sol = the final query
     *//*

    public String MapBeat(String genreSinger,String loudness,String tempo,
                          HashMap priority,String prioLoudness,String prioTempo,String prioGenre,
                          List<String> otherGenreSinger,boolean popular,String flag,String instrument,
                          String whichtableloudness,String whichtabletempo,String whichcolloudness,String whichcoltempo){
        int temp=0;
        String q="";
        String mapGenre="";
      //  if (flag.equals("singer")) {
            // the base query which will be the first part of all the quries
            if(flag.equals(EnumsSingers.singer.getEnums())) {
                mapGenre = "#standardSQL\n" + "SELECT distinct artists.artist_name,songs.song_loudness,songs.song_tempo," +
                        "genreSinger.genreSinger\n" +
                        "from genreSinger\n" +
                        "INNER JOIN genreartists on genreSinger.genre_id = genreartists.genre_id\n" +
                        "INNER JOIN artists on artists.artist_id = genreartists.artist_id\n" +
                        "INNER JOIN songs on songs.song_artist_id = artists.artist_id\n"; //+
            }
            else if(flag.equals(EnumsSingers.composer.getEnums())){
                mapGenre="Select distinct composers.composer_name,composers.composers_genre,composers.composers_loudness,composers.composers_tempo\n" +
                        "FROM composers ";
            }
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
            double numLoud[] = Maps.getInstance().PutInloudness(loudness);
            double numTempo[] = Maps.getInstance().PutInTempo(tempo);
            switch (EnumsSingers.valueOf(loudness)) {
                case Weak:
                    num_loudness = numLoud[0] - (double) priority.get(prioLoudness);
                    if (temp == 1) {
                        num_tempo = numTempo[0] + (double) priority.get(prioTempo);
                        q = mapGenre + "WHERE "+whichcoltempo  + "<\"" + num_tempo + "\"" + "AND "+whichcolloudness+ ">\"" + num_loudness + "\"" +
                                notNull;
                    } else if (temp == 2) {
                        num_tempo = numTempo[0] - ((double) priority.get(prioTempo) / 2);
                        double num_tempo2 = numTempo[1] + ((double) priority.get(prioTempo) / 2);
                        q = mapGenre +
                                "WHERE "+whichcoltempo + " between \"" + num_tempo + "\"" + "and \"" + num_tempo2 + "\"" + "AND "+whichcolloudness+">\"" + num_loudness + "\"" +
                                notNull;
                    } else {
                        num_tempo = numTempo[0] - (double) priority.get(prioTempo);
                        q = mapGenre +
                                "WHERE "+whichcoltempo+">\"" + num_tempo + "\"" + "AND "+whichcolloudness+">\"" + num_loudness + "\"" +
                                notNull;
                    }
                    break;
                case Normal:
                    num_loudness = (double) numLoud[0] - ((double) priority.get(prioLoudness) / 2);
                    double num_loudness2 = (double) numLoud[1] + ((double) priority.get(prioLoudness) / 2);
                    if (temp == 1) {
                        num_tempo = numTempo[0] + (double) priority.get(prioTempo);
                        q = mapGenre +
                                "WHERE "+whichcoltempo  +"<\"" + num_tempo + "\"" + " AND "+whichcolloudness+" BETWEEN \"" + num_loudness + "\"" + "and \"" + num_loudness2 + "\"" +
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
                                "WHERE "+whichcoltempo  +">\"" + num_tempo + "\"" + "AND "+whichcolloudness+ " BETWEEN \"" + num_loudness + "\"" + "and \"" + num_loudness2 + "\"" +
                                notNull;
                    }
                    break;
                case Strong:
                    num_loudness = numLoud[0] + (double) priority.get(prioLoudness);
                    if (temp == 1) {
                        num_tempo = numTempo[0] + (double) priority.get(prioTempo);
                        q = mapGenre +
                                "WHERE "+whichcoltempo  +"<\"" + num_tempo + "\"" + " AND "+whichcolloudness+ "<\"" + num_loudness + "\"" +
                                notNull;
                    } else if (temp == 2) {
                        num_tempo = numTempo[0] - ((double) priority.get(prioTempo) / 2);
                        double num_tempo2 = numTempo[1] + ((double) priority.get(prioTempo) / 2);
                        q = mapGenre +
                                "WHERE "+whichcoltempo  + " between \"" + num_tempo + "\"" + "and \"" + num_tempo2 + "\"" + "AND "+whichcolloudness+ "<\"" + num_loudness + "\"" +
                                notNull;
                    } else {
                        num_tempo = numTempo[0] - (double) priority.get(prioTempo);
                        q = mapGenre +
                                "WHERE "+whichcoltempo  +">\"" + num_tempo + "\"" + "AND "+whichcolloudness+"<\"" + num_loudness + "\"" +
                                notNull;
                    }
                    break;
            }
       // }
        if(flag.equals(EnumsSingers.composer.getEnums())){
            q=q+" AND (composers.musical_instrument=\""+instrument+"\""+")";
        }
        // sends to a function that is responsible for Concatenation of the strings into final query.
        String sol=GetSol(q,genreSinger,null,null,prioGenre,null,null,otherGenreSinger,null,null,popular,flag);
        //returns the final query
        return sol;
    }


    */
/**
     * receives the user's choices and compose from them the matching query.
     * @param genreSinger = user's choice of genreSinger
     * @param element2 = user's choice of element2.
     * @param element3 = user's choice of element3.
     * @return q = the matching query
     *//*


    public String UserInput(String genreSinger, String element2, String element3,String instrument,String prioGenre, String prioElement2, String prioElement3,boolean popular,String flag){
        HashMap<String,Double> priority = new HashMap<>();
        priority = Maps.getInstance().PutInPriority(prioElement2,prioElement3);
        List<String> couples=new ArrayList<>();
        List<String> couples2=new ArrayList<>();
        List<String> couples3=new ArrayList<>();
        List<String> otherGenreSinger=new ArrayList<>();
        List<String> otherElement2=new ArrayList<>();
        List<String> otherElement3=new ArrayList<>();
        if (prioGenre.equals(EnumsSingers.Medium.getEnums()) || prioGenre.equals(EnumsSingers.Low.getEnums())){
            couples = orderCouples(genreSinger,prioGenre,"genreSinger",20);
            otherGenreSinger = getOtherElement(couples,genreSinger);
            Maps.getInstance().getFromQuery(otherGenreSinger,"genreSinger");
        }
        if(prioElement2.equals(EnumsSingers.Medium.getEnums()) || prioElement2.equals(EnumsSingers.Low.getEnums())) {
            if (flag.equals(EnumsSingers.poets.getEnums())) {
                couples2 = orderCouples(element2, prioElement2,"topic",3);
                otherElement2 = getOtherElement(couples2, element2);
                Maps.getInstance().getFromQuery(otherElement2, "topic");
            }
        }
            if(prioElement3.equals(EnumsSingers.Medium.getEnums()) || prioElement3.equals(EnumsSingers.Low.getEnums())){
                if(flag.equals(EnumsSingers.poets.getEnums())){
                    couples3 = orderCouples(element3,prioElement3,"goal",3);
                    otherElement3 = getOtherElement(couples3,element3);
                    Maps.getInstance().getFromQuery(otherElement3,"goal");
                }
        }
        String q="";
        if (flag.equals(EnumsSingers.singer.getEnums())){
            String whichtableloudness = "songs.song_loudness";
            String whichtabletempo = "songs.song_tempo";
            String whichcolloudness = "song_loudness";
            String whichcoltempo = "song_tempo";
            q=MapBeat(genreSinger,element2,element3,priority,prioElement2,prioElement3,prioGenre,otherGenreSinger,popular,flag,null,whichtableloudness,whichtabletempo,whichcolloudness,whichcoltempo);

        }
        else if (flag.equals(EnumsSingers.poets.getEnums())){
            String choose = "SELECT distinct poets.poet_name,poets.song_topic,poets.goal,poets.genreSinger\n" +
                    " FROM poets";
            q= GetSol(choose,genreSinger,element2,element3,prioGenre,prioElement2,prioElement3,otherGenreSinger,otherElement2,otherElement3,popular,flag);
        }
        else{
            String whichtableloudness = "composers.composers_loudness";
            String whichtabletempo = "composers.composers_tempo";
            String whichcolloudness = "composers_loudness";
            String whichcoltempo = "composers_tempo";
            q=MapBeat(genreSinger,element2,element3,priority,prioElement2,prioElement3,prioGenre,otherGenreSinger,popular,flag,instrument,whichtableloudness,whichtabletempo,whichcolloudness,whichcoltempo);
           */
/* String choose = "Select distinct composers.composer_name,composers.composers_genre,composers.composers_loudness,composers.composers_tempo\n" +
                    "FROM composers WHERE (composers.musical_instrument=\""+instrument+"\""+")";
            q=GetSol(choose,genreSinger,element2,element3,prioGenre,prioElement2,prioElement3,otherGenreSinger,otherElement2,otherElement3,popular,flag);*//*


        }
        return q;

    }

      */
/**
     * composes all the query parts into one query.
     * @param BeatQ  = the combination of the tempo and loudness chosen by the user.
     * @param genreSinger = user's choice of genreSinger.
     * @return lastQ = the final query
     *//*



    public String GetSol(String BeatQ, String genreSinger,String element2,String element3,String prioGenre,String prioElement2,String prioElement3,
                         List<String> otherGenreSinger,List<String> otherTopic,List<String> otherGoal,boolean popular,String flag){
        String hotness=null;
        if(popular){
             hotness=" order by artists.artist_hotness DESC";
        }else{
            if(flag.equals(EnumsSingers.singer.getEnums())){
                hotness=" order by artists.artist_hotness ASC";
            }
        }


        StringBuilder quGenre = new StringBuilder();
        StringBuilder quTopic = new StringBuilder();
        StringBuilder quGoal = new StringBuilder();
        if(prioGenre.equals(EnumsSingers.Medium.getEnums()) || prioGenre.equals(EnumsSingers.Low.getEnums())) {
            for (int i = 0; i < otherGenreSinger.size(); i++) {
                if(flag.equals(EnumsSingers.singer.getEnums())){
                    quGenre.append(" OR genreSinger.genreSinger=\"" + otherGenreSinger.get(i) + "\"");
                }
                else if (flag.equals(EnumsSingers.poets.getEnums())){
                    quGenre.append(" OR poets.genreSinger=\"" + otherGenreSinger.get(i) + "\"");
                }
                else{
                    quGenre.append(" OR composers.composers_genre=\"" + otherGenreSinger.get(i) + "\"");
                }
            }
        }
        if(flag.equals(EnumsSingers.poets.getEnums())){
            if(prioElement2.equals(EnumsSingers.Medium.getEnums()) || prioElement2.equals(EnumsSingers.Low.getEnums())) {
                for (int i = 0; i < otherTopic.size(); i++) {
                    quTopic.append(" OR poets.song_topic=\"" + otherTopic.get(i) + "\"");
                }
            }
            if(prioElement3.equals(EnumsSingers.Medium.getEnums()) || prioElement3.equals(EnumsSingers.Low.getEnums())) {
                for (int i = 0; i < otherGoal.size(); i++) {
                    quGoal.append(" OR poets.goal=\"" + otherGoal.get(i) + "\"");
                }
            }
        }

        String lastQ="";
        if(flag.equals(EnumsSingers.singer.getEnums())){
            lastQ=BeatQ+" AND (genreSinger.genreSinger=\""+genreSinger+"\""+quGenre+")"+hotness;
        }
        else if (flag.equals(EnumsSingers.poets.getEnums())){
            String concat = " WHERE (poets.genreSinger=\""+genreSinger+"\""+quGenre+")"+" AND (poets.song_topic=\""+element2+"\""+quTopic+")"+
                    "And (poets.goal=\""+element3+"\""+quGoal+")";
            lastQ=BeatQ+concat;
        }
        else{
            lastQ=BeatQ+" AND (composers.composers_genre=\""+genreSinger+"\""+quGenre+")";
        }

        return lastQ;
    }



    public List<String> orderCouples(String genreSinger, String prioGenre,String which,int threshold){
        Map<String, Integer> map = new HashMap<>();
        CoupleDistance coupleDistance = CoupleDistance.getInstance();
        if(which.equals("genreSinger")){
            map = coupleDistance.getGenreSingerMap();
        }
        else if(which.equals("topic")){
            map = coupleDistance.getTopicMap();
        }
        else{
            map = coupleDistance.getGoalMap();
        }

        Map<String, Integer> miniMap = new HashMap<>();
        List<String> couples=new ArrayList<>();
        List<Integer> vals = new ArrayList<>();
        for (Map.Entry<String, Integer> entry :map.entrySet()) {
            if (entry.getKey().contains(genreSinger)) {
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


}*/
