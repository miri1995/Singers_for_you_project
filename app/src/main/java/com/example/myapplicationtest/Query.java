package com.example.myapplicationtest;



import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                          List<String> otherGenre,boolean popular){
        int temp=0;
        String q="";
        // the base query which will be the first part of all the quries
        String mapGenre= "#standardSQL\n" + "SELECT distinct artists.artist_name,songs.song_loudness,songs.song_tempo," +
                "genre.genre\n" +
                "from genre\n" +
                "INNER JOIN genreartists on genre.genre_id = genreartists.genre_id\n" +
                "INNER JOIN artists on artists.artist_id = genreartists.artist_id\n" +
                "INNER JOIN songs on songs.song_artist_id = artists.artist_id\n" +
                "WHERE artists.artist_id IN ";
        // check that this features are not null
        String notNull=" AND songs.song_loudness IS NOT NULL AND songs.song_tempo IS NOT NULL";
        float num_tempo;
        float num_loudness;
        switch (tempo){
            case "Slow":
                temp=1;
                break;
            case "Medium":
                temp=2;
                break;
            case "Fast":
                temp=3;
                break;
        }
        // according to the loudness and tempo chosen by the user creates the continuation of the query.
        float numLoud[] = Maps.getInstance().PutInloudness(loudness);
        float numTempo[] = Maps.getInstance().PutInTempo(tempo);
        switch (loudness){
            case "Weak":
                num_loudness = numLoud[0] - (float)priority.get(prioLoudness);
                if(temp==1){
                    num_tempo = numTempo[0] + (float)priority.get(prioTempo);
                    q=  mapGenre+ "(Select song_artist_id FROM songs where song_tempo <\""+num_tempo+"\""+ "AND song_loudness >\""+num_loudness+"\""+
                            notNull+")";
                }else if(temp==2){
                    num_tempo = numTempo[0] - ((float)priority.get(prioTempo)/2);
                    float num_tempo2 = numTempo[1] + ((float)priority.get(prioTempo)/2);
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo between \""+num_tempo+"\""+ "and \""+num_tempo2+"\"" + "AND song_loudness>\""+num_loudness+"\""+
                            notNull+")";
                }else {
                    num_tempo = numTempo[0] - (float) priority.get(prioTempo);
                    q = mapGenre +
                            "(Select song_artist_id FROM songs WHERE song_tempo>\"" + num_tempo + "\"" + "AND song_loudness>\"" + num_loudness + "\"" +
                            notNull + ")";
                }
                break;
            case "Normal":
                num_loudness = (float)numLoud[0] - ((float)priority.get(prioLoudness)/2);
                float num_loudness2 = (float)numLoud[1] + ((float)priority.get(prioLoudness)/2);
                if(temp==1){
                    num_tempo = numTempo[0] + (float)priority.get(prioTempo);
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo<\""+num_tempo+"\"" + " AND song_loudness BETWEEN \""+num_loudness+"\"" + "and \""+num_loudness2+"\""+
                            notNull+")";
                }else if(temp==2){
                    num_tempo = numTempo[0] - ((float)priority.get(prioTempo)/2);
                    float num_tempo2 = numTempo[1] + ((float)priority.get(prioTempo)/2);
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo between \""+num_tempo+"\"" + "and \""+num_tempo2+"\"" + "AND song_loudness BETWEEN \""+num_loudness+"\"" + "and \""+num_loudness2+"\"" +
                            notNull+")";
                }else {
                    num_tempo = numTempo[0] - (float) priority.get(prioTempo);
                    q = mapGenre +
                            "(Select song_artist_id FROM songs WHERE song_tempo>\"" + num_tempo + "\"" + "AND song_loudness BETWEEN \"" + num_loudness + "\"" + "and \"" + num_loudness2 + "\"" +
                            notNull + ")";
                }
                break;
            case "Strong":
                num_loudness = numLoud[0] + (float)priority.get(prioLoudness);
                if(temp==1){
                    num_tempo = numTempo[0] + (float)priority.get(prioTempo);
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo<\""+num_tempo+"\"" + " AND song_loudness<\""+num_loudness+"\"" +
                            notNull+")";
                }else if(temp==2){
                    num_tempo = numTempo[0] - ((float)priority.get(prioTempo)/2);
                    float num_tempo2 = numTempo[1] + ((float)priority.get(prioTempo)/2);
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo between \""+num_tempo+"\"" + "and \""+num_tempo2+"\"" + "AND song_loudness<\""+num_loudness+"\"" +
                            notNull+")";
                }else {
                    num_tempo = numTempo[0] - (float) priority.get(prioTempo);
                    q = mapGenre +
                            "(Select song_artist_id FROM songs WHERE song_tempo>\"" + num_tempo + "\"" + "AND song_loudness<\"" + num_loudness + "\"" +
                            notNull + ")";
                }
                break;
        }
        // sends to a function that is responsible for Concatenation of the strings into final query.
        String sol=GetSol(q,genre,prioGenre,otherGenre,popular);
        //returns the final query
        return sol;
    }


    /**
     * receives the user's choices and compose from them the matching query.
     * @param genre = user's choice of genre
     * @param loudness = user's choice of loudness.
     * @param tempo = user's choice of tempo.
     * @return q = the matching query
     */

    public String UserInput(String genre, String loudness, String tempo,String prioGenre, String prioLoudness, String prioTempo,boolean popular){
        HashMap<String,Float> priority = Maps.getInstance().PutInPriority(prioLoudness,prioTempo);
        List<String> couples=new ArrayList<>();
        List<String> otherGenre=new ArrayList<>();
        if (prioGenre.equals("medium") || prioGenre.equals("low")){
            couples = orderGenre(genre,prioGenre);
            otherGenre = getOtherGenre(couples,genre);
        }
        String q=MapBeat(genre,loudness,tempo,priority,prioLoudness,prioTempo,prioGenre,otherGenre,popular);
        return q;
    }


    /**
     * composes all the query parts into one query.
     * @param BeatQ  = the combination of the tempo and loudness chosen by the user.
     * @param genre = user's choice of genre.
     * @return lastQ = the final query
     */
    public String GetSol(String BeatQ, String genre,String prioGenre,List<String> otherGenre,boolean popular){
        String hotness=null;
        if(popular){
             hotness=" order by artists.artist_hotness DESC";
        }else{
            hotness=" order by artists.artist_hotness ASC";
        }

        StringBuilder quGenre = new StringBuilder();
        if(prioGenre.equals("medium") || prioGenre.equals("low")) {
            for (int i = 0; i < otherGenre.size(); i++) {
               quGenre.append(" OR genre.genre=\"" + otherGenre.get(i) + "\"");
            }

        }
        String lastQ=BeatQ+" AND (genre.genre=\""+genre+"\""+quGenre+")"+hotness;

        return lastQ;
    }



    public List<String> orderGenre(String genre, String prioGenre){
        int threshold =20;
        GenreDistance genreDistance = GenreDistance.getInstance();
        Map<String, Integer> map = genreDistance.getMap();
        Map<String, Integer> miniMap = new HashMap<>();
        List<String> couples=new ArrayList<>();
        List<Integer> vals = new ArrayList<>();
        for (Map.Entry<String, Integer> entry :map.entrySet()) {
            if (entry.getKey().contains(genre)) {
                switch (prioGenre) {
                    case "medium":
                        if (entry.getValue() > threshold) {
                            miniMap.put(entry.getKey(), entry.getValue());
                            vals.add(entry.getValue());
                        }
                        break;
                    case "low":
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

    public List getOtherGenre(List<String> couples,String genre){
        List <String> secondGenre = new ArrayList<>();
        for(int i=0;i<couples.size();i++){
            String[] tokens = couples.get(i).split(",");
            if(!tokens[0].equals(genre)){
                secondGenre.add(tokens[0]);
            }
            else{
                secondGenre.add(tokens[1]);
            }

        }
        return secondGenre;
    }


}