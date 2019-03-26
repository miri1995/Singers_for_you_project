package com.example.myapplicationtest;

import java.util.HashMap;
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
    public String MapBeat(String genre,String loudness,String tempo,HashMap priority,String prioLoudness,String prioTempo){
        int temp=0;
        String q="";
        // the base query which will be the first part of all the quries
        String mapGenre= "SELECT distinct artists.artist_name\n" +
                "from genre\n" +
                "INNER JOIN genreartists on genre.genre_id = genreartists.genre_id\n" +
                "INNER JOIN artists on artists.artist_id = genreartists.artist_id\n" +
                "WHERE artists.artist_id IN ";
        // check that this features are not null
        String notNull=" AND songs.song_loudness IS NOT NULL AND songs.song_tempo IS NOT NULL";
        int num_tempo;
        int num_loudness;
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
        switch (loudness){
            case "Weak":
                num_loudness = -16 - (int)priority.get(prioLoudness);
                if(temp==1){
                    num_tempo = 85 + (int)priority.get(prioTempo);
                    q=  mapGenre+ "(Select song_artist_id FROM songs where song_tempo <\""+num_tempo+"\""+ "AND song_loudness >\""+num_loudness+"\""+
                            notNull+")";
                }else if(temp==2){
                    num_tempo = 85 - ((int)priority.get(prioTempo)/2);
                    int num_tempo2 = 170 + ((int)priority.get(prioTempo)/2);
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo between \""+num_tempo+"\""+ "and \""+num_tempo2+"\"" + "AND song_loudness>\""+num_loudness+"\""+
                            notNull+")";
                }else
                    num_tempo = 170 - (int)priority.get(prioTempo);
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo>\""+num_tempo+"\"" + "AND song_loudness>\""+num_loudness+"\""+
                            notNull+")";
                break;
            case "Normal":
                num_loudness = -32 - (int)priority.get(prioLoudness);
                int num_loudness2 = -16 + (int)priority.get(prioLoudness);
                if(temp==1){
                    num_tempo = 85 + (int)priority.get(prioTempo);
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo<\""+num_tempo+"\"" + " AND song_loudness BETWEEN \""+num_loudness+"\"" + "and \""+num_loudness2+"\""+
                            notNull+")";
                }else if(temp==2){
                    num_tempo = 85 - ((int)priority.get(prioTempo)/2);
                    int num_tempo2 = 170 + ((int)priority.get(prioTempo)/2);
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo between \""+num_tempo+"\"" + "and \""+num_tempo2+"\"" + "AND song_loudness BETWEEN \""+num_loudness+"\"" + "and \""+num_loudness2+"\"" +
                            notNull+")";
                }else
                    num_tempo = 170 - (int)priority.get(prioTempo);
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo>\""+num_tempo+"\"" + "AND song_loudness BETWEEN \""+num_loudness+"\"" + "and \""+num_loudness2+"\""+
                notNull+")";
                break;
            case "Strong":
                num_loudness = -32 + (int)priority.get(prioLoudness);
                if(temp==1){
                    num_tempo = 85 + (int)priority.get(prioTempo);
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo<\""+num_tempo+"\"" + " AND song_loudness<\""+num_loudness+"\"" +
                    notNull+")";
                }else if(temp==2){
                    num_tempo = 85 - ((int)priority.get(prioTempo)/2);
                    int num_tempo2 = 170 + ((int)priority.get(prioTempo)/2);
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo between \""+num_tempo+"\"" + "and \""+num_tempo2+"\"" + "AND song_loudness<\""+num_loudness+"\"" +
                    notNull+")";
                }else
                    num_tempo = 170 - (int)priority.get(prioTempo);
                    q= mapGenre+
                            "(Select song_artist_id FROM songs WHERE song_tempo>\""+num_tempo+"\"" + "AND song_loudness<\""+num_loudness+"\"" +
                notNull+")";
                break;
        }
        // sends to a function that is responsible for Concatenation of the strings into final query.
        String sol=GetSol(q,genre);
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
    public String UserInput(String genre,String loudness,String prioLoudness,String tempo,String prioTempo){
        HashMap<String,Integer> priority = new HashMap<>();
        if(prioLoudness.equals("high")){
            priority.put(prioLoudness,0);
            if(prioTempo.equals("medium")){
                priority.put(prioTempo,30);
                //priority.put(prioGenre,val);
            }
            if(prioTempo.equals("low")){
                priority.put(prioTempo,60);
                //priority.put(prioGenre,val);
            }
        }
        else if (prioLoudness.equals("medium")){
            if(prioTempo.equals("high")){
                priority.put(prioTempo,0);
                //priority.put(prioGenre,val);
            }
            if(prioTempo.equals("low")){
                priority.put(prioTempo,60);
                //priority.put(prioGenre,val);
            }
        }
        else {
            if(prioTempo.equals("high")){
                priority.put(prioTempo,0);
                //priority.put(prioGenre,val);
            }
            if(prioTempo.equals("medium")){
                priority.put(prioTempo,30);
                //priority.put(prioGenre,val);
            }

        }
        String q=MapBeat(genre,loudness,tempo,priority,prioLoudness,prioTempo);
        return q;
    }


    /**
     * composes all the query parts into one query.
     * @param BeatQ  = the combination of the tempo and loudness chosen by the user.
     * @param genre = user's choice of genre.
     * @return lastQ = the final query
     */
    public String GetSol(String BeatQ, String genre){
        String hotness=" order by artists.artist_hotness DESC";
        String lastQ=BeatQ+" AND genre.genre=\""+genre+"\""+hotness;

        return lastQ;
    }

}