package com.example.myapplicationtest;



import com.example.myapplicationtest.Enums.EnumsSingers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Query class - responsible for making the queries.
 */
public class Query {

    /**
     * creates the queries according to the user's choices.
     * @param genre = user's choice of genre
     * @param loudness = user's choice of loudness.
     * @param tempo = user's choice of tempo.
     * @return sol = the final query
     */
    public String MapBeat(String genre,String loudness,String tempo,
                          HashMap priority,String prioLoudness,String prioTempo,String prioGenre,
                          List<String> otherGenre,boolean popular,String flag,String instrument,
                          String whichtableloudness,String whichtabletempo,String whichcolloudness,String whichcoltempo){
        int temp=0;
        String q="";
        String mapGenre="";
        if (flag.equals("singer")) {
            // the base query which will be the first part of all the quries
            if(flag.equals("singer")) {
                mapGenre = "#standardSQL\n" + "SELECT distinct artists.artist_name,songs.song_loudness,songs.song_tempo," +
                        "genre.genre\n" +
                        "from genre\n" +
                        "INNER JOIN genreartists on genre.genre_id = genreartists.genre_id\n" +
                        "INNER JOIN artists on artists.artist_id = genreartists.artist_id\n" +
                        "INNER JOIN songs on songs.song_artist_id = artists.artist_id\n"; //+
            }
            else{
                mapGenre="Select distinct composers.composer_name,composers.composers_genre,composers.composers_loudness,composers.composers_tempo\n" +
                        "FROM composers WHERE (composers.musical_instrument=\""+instrument+"\""+")";
            }
            //"WHERE artists.artist_id IN ";
            // check that this features are not null
            String notNull = " AND \""+whichtableloudness+"\"" + " IS NOT NULL AND \""+whichtabletempo+"\"" + " IS NOT NULL";
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
                        q = mapGenre + "WHERE \""+whichcoltempo+"\"" + "<\"" + num_tempo + "\"" + "AND \""+whichcolloudness+"\""+ ">\"" + num_loudness + "\"" +
                                notNull;
                    } else if (temp == 2) {
                        num_tempo = numTempo[0] - ((double) priority.get(prioTempo) / 2);
                        double num_tempo2 = numTempo[1] + ((double) priority.get(prioTempo) / 2);
                        q = mapGenre +
                                "WHERE \""+whichcoltempo+"\"" + " between \"" + num_tempo + "\"" + "and \"" + num_tempo2 + "\"" + "AND \""+whichcolloudness+"\""+">\"" + num_loudness + "\"" +
                                notNull;
                    } else {
                        num_tempo = numTempo[0] - (double) priority.get(prioTempo);
                        q = mapGenre +
                                "WHERE \""+whichcoltempo+"\""+">\"" + num_tempo + "\"" + "AND \""+whichcolloudness+"\""+">\"" + num_loudness + "\"" +
                                notNull;
                    }
                    break;
                case Normal:
                    num_loudness = (double) numLoud[0] - ((double) priority.get(prioLoudness) / 2);
                    double num_loudness2 = (double) numLoud[1] + ((double) priority.get(prioLoudness) / 2);
                    if (temp == 1) {
                        num_tempo = numTempo[0] + (double) priority.get(prioTempo);
                        q = mapGenre +
                                "WHERE \""+whichcoltempo+"\""+"<\"" + num_tempo + "\"" + " AND \""+whichcolloudness+"\"" +" BETWEEN \"" + num_loudness + "\"" + "and \"" + num_loudness2 + "\"" +
                                notNull;
                    } else if (temp == 2) {
                        num_tempo = numTempo[0] - ((double) priority.get(prioTempo) / 2);
                        double num_tempo2 = numTempo[1] + ((double) priority.get(prioTempo) / 2);
                        q = mapGenre +
                                "WHERE \""+whichcoltempo+"\"" +" between \"" + num_tempo + "\"" + "and \"" + num_tempo2 + "\"" + "AND \""+whichcolloudness+"\""+ "BETWEEN \"" + num_loudness + "\"" + "and \"" + num_loudness2 + "\"" +
                                notNull;
                    } else {
                        num_tempo = numTempo[0] - (double) priority.get(prioTempo);
                        q = mapGenre +
                                "WHERE \""+whichcoltempo+"\""+">\"" + num_tempo + "\"" + "AND \""+whichcolloudness+"\""+ " BETWEEN \"" + num_loudness + "\"" + "and \"" + num_loudness2 + "\"" +
                                notNull;
                    }
                    break;
                case Strong:
                    num_loudness = numLoud[0] + (double) priority.get(prioLoudness);
                    if (temp == 1) {
                        num_tempo = numTempo[0] + (double) priority.get(prioTempo);
                        q = mapGenre +
                                "WHERE \""+whichcoltempo+"\""+"<\"" + num_tempo + "\"" + " AND \""+whichcolloudness+"\""+"<\"" + num_loudness + "\"" +
                                notNull;
                    } else if (temp == 2) {
                        num_tempo = numTempo[0] - ((double) priority.get(prioTempo) / 2);
                        double num_tempo2 = numTempo[1] + ((double) priority.get(prioTempo) / 2);
                        q = mapGenre +
                                "WHERE \""+whichcoltempo+"\""+ " between \"" + num_tempo + "\"" + "and \"" + num_tempo2 + "\"" + "AND \""+whichcolloudness+"\""+"<\"" + num_loudness + "\"" +
                                notNull;
                    } else {
                        num_tempo = numTempo[0] - (double) priority.get(prioTempo);
                        q = mapGenre +
                                "WHERE \""+whichcoltempo+"\""+">\"" + num_tempo + "\"" + "AND \""+whichcolloudness+"\""+"<\"" + num_loudness + "\"" +
                                notNull;
                    }
                    break;
            }
        }
        // sends to a function that is responsible for Concatenation of the strings into final query.
        String sol=GetSol(q,genre,null,null,prioGenre,null,null,otherGenre,null,null,popular,flag);
        //returns the final query
        return sol;
    }


    /**
     * receives the user's choices and compose from them the matching query.
     * @param genre = user's choice of genre
     * @param element2 = user's choice of element2.
     * @param element3 = user's choice of element3.
     * @return q = the matching query
     */

    public String UserInput(String genre, String element2, String element3,String instrument,String prioGenre, String prioElement2, String prioElement3,boolean popular,String flag){
        HashMap<String,Double> priority = new HashMap<>();
        priority = Maps.getInstance().PutInPriority(prioElement2,prioElement3);
        List<String> couples=new ArrayList<>();
        List<String> couples2=new ArrayList<>();
        List<String> couples3=new ArrayList<>();
        List<String> otherGenre=new ArrayList<>();
        List<String> otherElement2=new ArrayList<>();
        List<String> otherElement3=new ArrayList<>();
        if (prioGenre.equals(EnumsSingers.Medium.getEnums()) || prioGenre.equals(EnumsSingers.Low.getEnums())){
            couples = orderCouples(genre,prioGenre,"genre",20);
            otherGenre = getOtherElement(couples,genre);
            Maps.getInstance().getFromQuery(otherGenre,"genre");
        }
        if(prioElement2.equals(EnumsSingers.Medium.getEnums()) || prioElement2.equals(EnumsSingers.Low.getEnums())) {
            if (flag.equals("poets")) {
                couples2 = orderCouples(element2, prioElement2,"topic",3);
                otherElement2 = getOtherElement(couples2, element2);
                Maps.getInstance().getFromQuery(otherElement2, "topic");
            }
        }
            if(prioElement3.equals(EnumsSingers.Medium.getEnums()) || prioElement3.equals(EnumsSingers.Low.getEnums())){
                if(flag.equals("poets")){
                    couples3 = orderCouples(element3,prioElement3,"goal",3);
                    otherElement3 = getOtherElement(couples3,element3);
                    Maps.getInstance().getFromQuery(otherElement3,"goal");
                }
        }
        String q="";
        if (flag.equals("singer")){
            String whichtableloudness = "songs.song_loudness";
            String whichtabletempo = "songs.song_tempo";
            String whichcolloudness = "song_loudness";
            String whichcoltempo = "song_tempo";
            q=MapBeat(genre,element2,element3,priority,prioElement2,prioElement3,prioGenre,otherGenre,popular,flag,null,whichtableloudness,whichtabletempo,whichcolloudness,whichcoltempo);

        }
        else if (flag.equals("poets")){
            String choose = "SELECT distinct poets.poet_name,poets.song_topic,poets.goal,poets.genre\n" +
                    " FROM poets";
            q= GetSol(choose,genre,element2,element3,prioGenre,prioElement2,prioElement3,otherGenre,otherElement2,otherElement3,popular,flag);
        }
        else{
            String whichtableloudness = "composers.composers_loudness";
            String whichtabletempo = "composers.composers_tempo";
            String whichcolloudness = "composers_loudness";
            String whichcoltempo = "composers_tempo";
            q=MapBeat(genre,element2,element3,priority,prioElement2,prioElement3,prioGenre,otherGenre,popular,flag,instrument,whichtableloudness,whichtabletempo,whichcolloudness,whichcoltempo);
           /* String choose = "Select distinct composers.composer_name,composers.composers_genre,composers.composers_loudness,composers.composers_tempo\n" +
                    "FROM composers WHERE (composers.musical_instrument=\""+instrument+"\""+")";
            q=GetSol(choose,genre,element2,element3,prioGenre,prioElement2,prioElement3,otherGenre,otherElement2,otherElement3,popular,flag);*/

        }
        return q;

    }

      /**
     * composes all the query parts into one query.
     * @param BeatQ  = the combination of the tempo and loudness chosen by the user.
     * @param genre = user's choice of genre.
     * @return lastQ = the final query
     */


    public String GetSol(String BeatQ, String genre,String element2,String element3,String prioGenre,String prioElement2,String prioElement3,
                         List<String> otherGenre,List<String> otherTopic,List<String> otherGoal,boolean popular,String flag){
        String hotness=null;
        if(popular){
             hotness=" order by artists.artist_hotness DESC";
        }else{
            if(flag.equals("singer")){
                hotness=" order by artists.artist_hotness ASC";
            }
        }

        StringBuilder quGenre = new StringBuilder();
        StringBuilder quTopic = new StringBuilder();
        StringBuilder quGoal = new StringBuilder();
        if(prioGenre.equals(EnumsSingers.Medium.getEnums()) || prioGenre.equals(EnumsSingers.Low.getEnums())) {
            for (int i = 0; i < otherGenre.size(); i++) {
                if(flag.equals("singer")){
                    quGenre.append(" OR genre.genre=\"" + otherGenre.get(i) + "\"");
                }
                else if (flag.equals("poets")){
                    quGenre.append(" OR poets.genre=\"" + otherGenre.get(i) + "\"");
                }
                else{
                    quGenre.append(" OR composers.composers_genre=\"" + otherGenre.get(i) + "\"");
                }
            }
        }
        if(flag.equals("poets")){
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
        if(flag.equals("singer")){
            lastQ=BeatQ+" AND (genre.genre=\""+genre+"\""+quGenre+")"+hotness;
        }
        else if (flag.equals("poets")){
            String concat = " WHERE (poets.genre=\""+genre+"\""+quGenre+")"+" AND (poets.song_topic=\""+element2+"\""+quTopic+")"+
                    "And (poets.goal=\""+element3+"\""+quGoal+")";
            lastQ=BeatQ+concat;
        }
        else{
            lastQ=BeatQ+" AND (composers.composers_genre=\""+genre+"\""+quGenre+")";
        }

        return lastQ;
    }



    public List<String> orderCouples(String genre, String prioGenre,String which,int threshold){
        Map<String, Integer> map = new HashMap<>();
        CoupleDistance coupleDistance = CoupleDistance.getInstance();
        if(which.equals("genre")){
            map = coupleDistance.getGenreMap();
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