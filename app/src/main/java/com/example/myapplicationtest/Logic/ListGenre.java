package com.example.myapplicationtest.Logic;

import java.io.*;
import java.util.*;

public class ListGenre {
   /* public static void main(String[] args){
        java.util.List<java.util.List<String>> lines = new ArrayList<>();
        java.util.List<String>l1 = new ArrayList<>();
        l1.add("hip hop");
        l1.add("jazz");
        lines.add(l1);
        java.util.List<String>l2 = new ArrayList<>();
        l2.add("hip hop");
        l2.add("balade");
        lines.add(l2);
        java.util.List<String>l3 = new ArrayList<>();
        l3.add("balade");
        l3.add("jazz");
        lines.add(l3);
        java.util.List<String>l11 = new ArrayList<>();
        l11.add("hip hop");
        l11.add("jazz");
        lines.add(l11);
        java.util.List<String>l4 = new ArrayList<>();
        l4.add("salsa");
        l4.add("rock");
        lines.add(l4);
        java.util.List<String>l5 = new ArrayList<>();
        l5.add("jazz");
        l5.add("balade");
        lines.add(l5);
        java.util.List<String>l12 = new ArrayList<>();
        l12.add("hip hop");
        l12.add("jazz");
        lines.add(l12);
        java.util.List<String>l6 = new ArrayList<>();
        l6.add("hip hop");
        l6.add("jazz");
        lines.add(l6);
        java.util.List<String>l7 = new ArrayList<>();
        l7.add("hip hop");
        l7.add("balade");
        lines.add(l7);
        java.util.List<String>l8 = new ArrayList<>();
        l8.add("balade");
        l8.add("jazz");
        lines.add(l8);
        java.util.List<String>l9 = new ArrayList<>();
        l9.add("jazz");
        l9.add("balade");
        lines.add(l9);
        java.util.List<String>l10 = new ArrayList<>();
        l10.add("salsa");
        l10.add("rock");
        lines.add(l10);
        System.out.println(countPairs(lines));
    }*/
    public  List<List<String>> ReadPairFile(){
       List<List<String>> lines = new ArrayList<>();

        try {
            File file = new File("src/pair.txt");
            if (!file.exists()) {
                Error();
            }
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String line = br.readLine();
            List<String> tokens = Arrays.asList(line.split("\\s+"));
            lines.add(tokens);
            while ((line = br.readLine()) != null){
                List<String> tokens2 = Arrays.asList(line.split("\\s+"));
                lines.add(tokens2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public  Map<String,Integer> countPairs(List<List<String>> lines){
        Map<String, Integer> map = new HashMap<>();
        for(int i=0;i<lines.size();i++){
            int counter=1;
            if(i!=lines.size()-1) {
                for (int j = i + 1; j < lines.size(); j++) {
                    if (lines.get(i).get(0).equals(lines.get(j).get(0)) || lines.get(i).get(0).equals(lines.get(j).get(1))) {
                        if (lines.get(i).get(0).equals(lines.get(j).get(0)) && lines.get(i).get(1).equals(lines.get(j).get(1))) {
                            // int t=j;
                            counter++;
                            lines.remove(j);
                            j--;
                        }
                        if (lines.get(i).get(0).equals(lines.get(j).get(1)) && lines.get(i).get(1).equals(lines.get(j).get(0))) {
                            //int t=j;
                            counter++;
                            lines.remove(j);
                            j--;
                        }
                    }
                }
            }
            else{
                map.put(lines.get(i).get(0)+lines.get(i).get(1),1);
            }
            map.put(lines.get(i).get(0)+lines.get(i).get(1),counter/2);
        }


        return map;
    }

    public void Error(){
        System.out.println("Couldn't found input file");
        System.exit(1);
    }

}
