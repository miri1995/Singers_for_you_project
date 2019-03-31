package com.example.myapplicationtest;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreDistance {
    private static GenreDistance single_instance = null;
    Map<String, Integer> map = new HashMap<>();

    private GenreDistance(){}

    public Map<String, Integer> getMap(){
        return map;
    }


    public static GenreDistance getInstance()
    {
        if (single_instance == null)
            single_instance = new GenreDistance();

        return single_instance;
    }

    public  List<List<String>> ReadFile(String text) throws IOException {
        List<List<String>> GenreCouples=new ArrayList<>();
        BufferedReader reader = new BufferedReader(new StringReader(text));
        String line="";
            int i=0;
           try {
               while ((line = reader.readLine()) != null) {
                   String[] tokens = line.split(",");
                   GenreCouples.add(new ArrayList<>());
                   GenreCouples.get(i).add(tokens[0]);
                   GenreCouples.get(i).add(tokens[1]);
                   i++;
               }
           }
        catch (IOException e) {
            e.printStackTrace();
        }
        return GenreCouples;
    }

    public Map<String,Integer> countPairs(List<List<String>> GenreCouples){
        for(int i=0;i<GenreCouples.size();i++){
            int counter=1;
            if(i!=GenreCouples.size()-1) {
                for (int j = i + 1; j < GenreCouples.size(); j++) {
                    if (GenreCouples.get(i).get(0).equals(GenreCouples.get(j).get(0)) || GenreCouples.get(i).get(0).equals(GenreCouples.get(j).get(1))) {
                        if (GenreCouples.get(i).get(0).equals(GenreCouples.get(j).get(0)) && GenreCouples.get(i).get(1).equals(GenreCouples.get(j).get(1))) {
                            // int t=j;
                            counter++;
                            GenreCouples.remove(j);
                            j--;
                        }
                        if (GenreCouples.get(i).get(0).equals(GenreCouples.get(j).get(1)) && GenreCouples.get(i).get(1).equals(GenreCouples.get(j).get(0))) {
                            //int t=j;
                            counter++;
                            GenreCouples.remove(j);
                            j--;
                        }
                    }
                }
            }
            else{
                map.put(GenreCouples.get(i).get(0)+ "," + GenreCouples.get(i).get(1),1);
            }
            map.put(GenreCouples.get(i).get(0)+ "," + GenreCouples.get(i).get(1),counter/2);
        }

        // lines.remove(i);

        return map;
    }

}



