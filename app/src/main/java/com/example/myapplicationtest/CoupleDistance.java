package com.example.myapplicationtest;

import com.example.myapplicationtest.Enums.EnumTables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoupleDistance {
    private static CoupleDistance single_instance = null;
    Map<String, Integer> genreSingerMap = new HashMap<>();
    Map<String, Integer> genrePoetMap = new HashMap<>();
    Map<String, Integer> genreComposerMap = new HashMap<>();
    Map<String, Integer> topicMap = new HashMap<>();
    Map<String, Integer> goalMap = new HashMap<>();


    private CoupleDistance(){}

    public Map<String, Integer> getGenreSingerMap(){
        return genreSingerMap;
    }

    public Map<String, Integer> getTopicMap(){
        return topicMap;
    }

    public Map<String, Integer> getGoalMap(){
        return goalMap;
    }

    public Map<String, Integer> getGenrePoetMap(){
        return genrePoetMap;
    }

    public Map<String, Integer> getGenreComposerMap(){
        return genreComposerMap;
    }

    public static CoupleDistance getInstance()
    {
        if (single_instance == null)
            single_instance = new CoupleDistance();

        return single_instance;
    }

    public  List<List<String>> ReadFile(String text) throws IOException {
        List<List<String>> couples=new ArrayList<>();
        BufferedReader reader = new BufferedReader(new StringReader(text));
        String line="";
            int i=0;
           try {
               while ((line = reader.readLine()) != null) {
                   String[] tokens = line.split(",");
                   couples.add(new ArrayList<>());
                   couples.get(i).add(tokens[0]);
                   couples.get(i).add(tokens[1]);
                   i++;
               }
           }
        catch (IOException e) {
            e.printStackTrace();
        }
        return couples;
    }

    public List<List<String>> CreatePairFromMap(Map<String,List<String>> map,List<List<String>> pairs) {
        //List<List<String>> pairs2 = pairs;

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            if (values.size() > 1) {

                for (int i = 0; i < values.size(); i++) {
                    for (int j = i + 1; j < values.size(); j++) {
                        {
//                        // finding the index with different
//                        // value and different index.
                            if (!values.get(i).equals(values.get(j))) {
                                List<String> genres = new ArrayList<>();
                                genres.add(values.get(i));
                                genres.add(values.get(j));
                                pairs.add(genres);
                            }
                        }
                    }
                }
            }
        }
        return pairs;
    }

    public Map<String,Integer> countPairs(List<List<String>> couples,String flag){
        for(int i=0;i<couples.size();i++){
            int counter=1;
            if(i!=couples.size()-1) {
                for (int j = i + 1; j < couples.size(); j++) {
                    if (couples.get(i).get(0).equals(couples.get(j).get(0)) || couples.get(i).get(0).equals(couples.get(j).get(1))) {
                        if (couples.get(i).get(0).equals(couples.get(j).get(0)) && couples.get(i).get(1).equals(couples.get(j).get(1))) {
                            // int t=j;
                            counter++;
                            couples.remove(j);
                            j--;
                        }
                        if (couples.get(i).get(0).equals(couples.get(j).get(1)) && couples.get(i).get(1).equals(couples.get(j).get(0))) {
                            //int t=j;
                            counter++;
                            couples.remove(j);
                            j--;
                        }
                    }
                }
            }
            else{
                if(flag.equals(EnumTables.genreSinger.getEnums())){
                    genreSingerMap.put(couples.get(i).get(0)+ "," + couples.get(i).get(1),1);
                }
                else if (flag.equals(EnumTables.topic.getEnums())){
                    topicMap.put(couples.get(i).get(0)+ "," + couples.get(i).get(1),1);
                }
                else if (flag.equals(EnumTables.goal.getEnums())){
                    goalMap.put(couples.get(i).get(0)+ "," + couples.get(i).get(1),1);
                }
                else if (flag.equals(EnumTables.genrePoet.getEnums())){
                    genrePoetMap.put(couples.get(i).get(0)+ "," + couples.get(i).get(1),1);
                }
            }
            if(flag.equals(EnumTables.genreSinger.getEnums())){
                genreSingerMap.put(couples.get(i).get(0)+ "," + couples.get(i).get(1),counter/2);
            }
            else if (flag.equals(EnumTables.topic.getEnums())){
                topicMap.put(couples.get(i).get(0)+ "," + couples.get(i).get(1),counter);
            }
            else if (flag.equals(EnumTables.goal.getEnums())){
                goalMap.put(couples.get(i).get(0)+ "," + couples.get(i).get(1),counter);
            }
            else if (flag.equals(EnumTables.genrePoet.getEnums())){
                genrePoetMap.put(couples.get(i).get(0)+ "," + couples.get(i).get(1),counter);
            }

        }

        // lines.remove(i);
    if(flag.equals(EnumTables.genreSinger.getEnums())){
        return genreSingerMap;
    }
    else if (flag.equals(EnumTables.topic.getEnums())){
        return topicMap;
    }
    else if (flag.equals(EnumTables.goal.getEnums())){
        return goalMap;
    }
    else if (flag.equals(EnumTables.genrePoet.getEnums())){
        return genrePoetMap;
    }

    return topicMap;
    }

}



